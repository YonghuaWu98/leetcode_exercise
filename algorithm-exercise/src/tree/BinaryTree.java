package tree;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName:BinaryTree
 * @Description TODO 二叉树的定义与基本操作
 * @Author 86156
 * @Version 1.0
 **/
public class BinaryTree {
    class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * @Description //TODO  递归实现二叉树的三种遍历
     **/
    @Test
    public void test() {
        List<Integer> res = new ArrayList<Integer>();
//        List<List<Integer>> lists = new List<List<Integer>>;
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.left.left = new Node(0);
        head.left.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.left.left = new Node(8);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(10);
        System.out.println("pre order traversal-recursion");
        BinaryTree.preOrderTraversal(head);
        System.out.println();
//        for (int i = 0; i < res.size(); i++) {
//            System.out.print(res.get(i) + " ");
//        }
        System.out.println("in order traversal-recursion");
        BinaryTree.inOrderTraversal(head, res);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
        System.out.println();
//
//        BinaryTree.postOrderTraversal(head, res);
//        for (int i = 0; i < res.size(); i++) {
//            System.out.print(res.get(i) + " ");
//        }
//
        BinaryTree.preOrderUnRecur(head);
        BinaryTree.inOrderUnRecur(head);
        BinaryTree.postOrderUnRecur(head);
        BinaryTree.LayerOrderTraversal(head);
        int maxWidth = BinaryTree.getMaxWidth(head);
        System.out.println(maxWidth);
        int maxWidth1 = BinaryTree.getMaxWidthOther(head);
        System.out.println(maxWidth1);
        System.out.println();
        List<Integer> result = new ArrayList<>();
        BinaryTree.dfs(head,result);
        System.out.println(result);

    }
    //TODO 递归先序遍历
    public static void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
//        res.add(root.value);

        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }

    //TODO 递归中序遍历
    public static void inOrderTraversal(Node root, List<Integer> res) {
        if(root == null)
            return;
        inOrderTraversal(root.left, res);
        res.add(root.value);
//        System.out.print(root.value + " ");
        inOrderTraversal(root.right, res);
    }

    //TODO 递归后序遍历
    public static void postOrderTraversal(Node root){
        if(root == null)
            return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
//        res.add(root.value);
    }


    /**
     * @Description //TODO (非递归)先序遍历
     * 先序遍历的步骤:
     * ①从栈中弹出一个结点cur
     * ②打印（处理）cur
     * ③先入右结点再入左结点
     * ④周而复始
     **/
    public static void preOrderUnRecur(Node head) {
        System.out.println("pre-order");
        if (head == null) return;
        Stack<Node> stack = new Stack<Node>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
        System.out.println();
    }


    /**
     * TODO （非递归）中序遍历
     * 每棵子树左边界进栈，
     * 依次弹的过程中打印，
     * 对弹出的节点的右树进行同样的操作（左头结点分解）**/
    public static void inOrderUnRecur(Node head) {
        System.out.println("in-order");
        if (head == null) return;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

 
    /**
     * @Description //TODO （非递归）后序遍历
     * 弹出一个结点cur
     * cur放入辅助栈
     * 先入左结点再入右结点（暂时不打印，放入普通栈）
     **/
    public static void postOrderUnRecur(Node head) {
        System.out.println("post-order");
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        Stack<Node> collectStack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            collectStack.push(head);
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
        while (!collectStack.isEmpty()) {
            System.out.print(collectStack.pop().value + " ");
        }
        System.out.println();

    }
    // 先序遍历
    public static void preOrderUnRecursion(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    //中序遍历
    public static void InOrderUnRecursion(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    //后序遍历
    public static void posOrderUnRecursion(Node head) {
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.add(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                head = s2.pop();
                System.out.println(head.value + " ");
            }
        }
    }
    //TODO 层序遍历  || 广度度优先搜索
    public static void LayerOrderTraversal(Node head) {
        System.out.println("layerOrderTraversal");
        if (head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
             Node cur = queue.poll();
            System.out.print(cur.value + " ");
             if (cur.left != null) {
                 queue.add(cur.left);
             }
             if (cur.right != null) {
                 queue.add(cur.right);
             }
        }
        System.out.println();
    }
    //TODO 广度优先搜索(BFS：Breadth First Search)

    //TODO 深度优先搜索（DFS：Depth First Search) || 先序遍历
    public static void dfs(Node head, List<Integer> res) {
        if (head == null) {
            return;
        }
        res.add(head.value);
        dfs(head.left, res);
        dfs(head.right, res);

    }


    //TODO 求一棵二叉树的宽度 (有Map）
    public static int getMaxWidth(Node head) {
        if (head == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>(); //创建一个Map，key存放结点，value记录结点在第几层
        levelMap.put(head, 1); //将头结点加入到levelMap
        int curLevel = 1; //记录当前结点在第几层
        int curLevelNodes = 0;//记录当前层结点的个数
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur); //得到出队列结点的层数
            if (curNodeLevel == curLevel) { //判断是否在同一层
                curLevelNodes++; //在同一层，则该层结点数加1
            } else { //不在同一层
                max = Math.max(max, curLevelNodes);  //结算，更新max
                curLevel++; //层数加1
                curLevelNodes = 1;
            }
            if (cur.left != null) { //当前结点的左结点不为空，则加入到levelMap，并记录其层数，然后加入队列
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) { //当前结点的右结点不为空，则加入到levelMap，并记录其层数，然后加入队列
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
        return max = max > curLevelNodes? max : curLevelNodes; //与最后一次进行结算，返回最大值。
    }
    // TODO 无Map
    public static int getMaxWidthOther(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int curNodeNum = 0;
        Node curEnd = head;
        Node nextEnd = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                nextEnd = cur.left;
                queue.add(cur.left);
            }
            if (cur.right != null) {
                nextEnd = cur.right;
                queue.add(cur.right);
            }
            if (cur == curEnd) {
                curNodeNum++;
                max = Math.max(max, curNodeNum);
                curEnd = nextEnd;
                nextEnd = null;
                curNodeNum = 0;
            } else {
                curNodeNum++;
            }
        }
        return max;
    }
    public static class TreeNode {
        public int value;
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) {
            value = val;
        }
    }
    //寻找某个结点的后继结点
    //方法一：
    //q结点可以根据有无右子树分为两种情况：1）q结点有右孩子，此时q结点的后继结点为右孩子的最左边的孩子结点  2）q结点无右结点
    // 判断q结点是否是它父节点的左孩子，不是的话，继续判断q的父节点，一直下去，直到找到某个祖先结点为其父节点的左孩子，这时的
    //父节点为q结点的后继结点，如果这个祖先结点的父节点为空，则q结点的后继结点为空

    public static TreeNode getSuccessorNode(TreeNode node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftChild(node.right);
        }else { // 无右子树
            while (node.parent != null || node != node.parent.left) { // 当前结点是其父结点右孩子
                node = node.parent;
                node.parent = node.parent.parent;
            }
        }
        return node.parent;
    }
    public static TreeNode getLeftChild(TreeNode node) {

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    //方法二：
    // 中序遍历找到后续结点



}

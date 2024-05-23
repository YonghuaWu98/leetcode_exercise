package tree;/*
 *@title LeetcodeBinaryTree
 *@author 86156
 *@description
 *@create 2023/4/22 21:46
 */

import org.junit.Test;

import java.util.*;

/**
 * @ClassName:LeetcodeBinaryTree
 * @Description TODO
 * @Author 86156
 * @Date 2023/4/22 21:46
 * @Version 1.0
 **/
public class LeetCodeBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    //打印二叉树
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len = lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static List<List<Integer>> resList = new ArrayList<List<Integer>>();

    @Test
    public void test() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
//        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(8);
//        root.right.right = new TreeNode(9);
//        root.right.right.right = new TreeNode(10);
//        TreeNode treeNode = deleteNode(root, 2);
//        TreeNode treeNode = convertBST1(root);
        TreeNode treeNode = trimBST(root, 2, 7);
        printTree(treeNode);
//        List<List<Integer>> lists = LeetCodeBinaryTree.levelOrder2(root);
//        System.out.println();
//        for (int i = 0;i < lists.size(); i++) {
//            System.out.print(lists.get(i) + " ");
//        }
//        System.out.println();
//        List<Integer> res = LeetCodeBinaryTree.rightSideView(root);
//        System.out.println(res);
//        System.out.println();
//        List<Double> result = LeetCodeBinaryTree.averageOfLevels(root);
//        System.out.println(result);
//        System.out.println();
//        LeetCodeBinaryTree.averageOfLevels1(root);
//        TreeNode root1 = new TreeNode(-2147483648);
//        root1.right = new TreeNode(2147483647);
//        boolean validBST = LeetCodeBinaryTree.isValidBST(root1);
//        System.out.println(validBST);

    }

    // TODO 学会层序遍历秒杀这些题
    //TODO leetcode 第102题 二叉数的层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            int curLevelSize = queue.size();
            for (int i = 1; i <= curLevelSize; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    //TODO DFS--递归方式
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        levelOrderRecur(root, 0);
        return resList;
    }

    public static void levelOrderRecur(TreeNode root, Integer level) {
        if (root == null) return;
        level++;
        if (resList.size() < level) {
            //当层级增加时，list的item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resList.add(item);
        }
        resList.get(level - 1).add(root.val);
        System.out.print(resList.get(level - 1) + " ");
        levelOrderRecur(root.left, level);
        levelOrderRecur(root.right, level);
    }


    //TODO leetcode 第107题 二叉数的层序遍历II  解题思路：结合队列和栈
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>(); //建立二维动态数组，每层元素作为子数组
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); //自顶向下，从右到左存放结点
        List<Integer> list = new ArrayList<Integer>();//记录每一层元素的个数
        Stack<TreeNode> s = new Stack<TreeNode>();//利用栈的特点，先进后出，自底向上，从左到右存放结点
        queue.add(root);
        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();
            list.add(curLevelSize);
            for (int i = 1; i <= curLevelSize; i++) {
                TreeNode cur = queue.poll();
                s.push(cur);
                if (cur.right != null) {  //右边先入队列
                    queue.add(cur.right);
                }
                if (cur.left != null) { //左边后入队列
                    queue.add(cur.left);
                }
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) { //从数组list最后一个元素开始遍历，最后一个元素代表最底层结点数
            List<Integer> l = new ArrayList<Integer>();
            int size = list.get(i);
            for (int j = 0; j < size; j++) {
                TreeNode cur1 = s.pop();  //栈中的结点为二叉树自底向上，从左到右存放的顺序
                l.add(cur1.val);
            }
            res.add(l);
        }
        return res;
    }

    // TODO 官方题解
    class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
            if (root == null) {
                return levelOrder;
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<Integer>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    TreeNode left = node.left, right = node.right;
                    if (left != null) {
                        queue.offer(left);
                    }
                    if (right != null) {
                        queue.offer(right);
                    }
                }
                levelOrder.add(0, level);  //每次在levelOrder第一个位子插入level，实现自底向上
            }
            return levelOrder;
        }
    }

    // TODO LeetCode第199题 二叉树的右视图
    public static List<Integer> rightSideView(TreeNode root) {
        TreeNode cur;
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) { //root为空，则返回空的res
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); //利用队列对二叉树进行广度优先搜索
        queue.add(root);
        while (!queue.isEmpty()) {
            int curSize = queue.size(); //curSize记录本次外循环queue的大小
            while (curSize > 1) {
                cur = queue.poll();//如果queue的大小大于1，则将queue第一个结点出队
                curSize--; //curSize减一
                //分别将当前结点的左右孩子入队，但curSize不变
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            //当queue的大小不大于1时，即等于1时
            cur = queue.poll();  //队列第一个节点出队，即每一层最右边的结点
            res.add(cur.val); //res保存这个节点的val值
            //分别将当前结点的左右孩子入队，但curSize不变
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return res;
    }
    // TODO  官方题解


    // TODO Leetcode第637题  二叉树的层平均值
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<Double>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }

            }
            res.add(sum / curSize);
        }
        return res;
    }
    // TODO 官方题解(dfs递归）
    //  使用深度优先搜索计算二叉树的层平均值，需要维护两个数组，counts用于存储二叉树的每一层的节点数，sums 用于存储二叉树的每一层的节点值之和。
    //  搜索过程中需要记录当前节点所在层，如果访问到的节点在第i 层，则将counts[i] 的值加1，并将该节点的值加到sums[i]。
    //  遍历结束之后，第i 层的平均值即为sums[i]/counts[i].


    public static List<Double> averageOfLevels1(TreeNode root) {
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<Double>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }

    public static void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }

    // TODO bfs


    // TODO leetcode第104题 二叉树的最大深度
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> mapLevel = new HashMap<>();
        int max = Integer.MIN_VALUE;
        mapLevel.put(root, 1);
        int curLength = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int level = mapLevel.get(cur);
            if (level == curLength) {
                max = max > curLength ? max : curLength;
            } else {
                curLength++;
            }
            if (cur.left != null) {
                mapLevel.put(cur.left, curLength + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                mapLevel.put(cur.right, curLength + 1);
                queue.add(cur.right);
            }
        }
        return max = max > curLength ? max : curLength; //加上最后一层
    }
    //TODO 官方题解


    //TODO Leetcode第515题  在每个树行中找最大值
    // 解法一：广度优先搜索
    public static List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        List<Integer> largestRes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        largestRes.add(root.val);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode cur = queue.poll();
                max = max > cur.val ? max : cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            largestRes.add(max);
        }
        return largestRes;
    }

    // TODO 解法二：深度优先搜索(递归）
    public List<Integer> largestVal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode cur, List<Integer> res, int level) {
        if (cur == null) return;
        if (level < res.size()) {
            //用level与res索引对应，进行比较大小，maxValue保存大的那一个
            int maxValue = cur.val > res.get(level) ? cur.val : res.get(level);
            res.set(level, maxValue);
        } else {
            res.add(cur.val);
        }
        // 要有递归栈在脑子里，要有递归序在脑子里
        dfs(cur.left, res, level + 1);
        dfs(cur.right, res, level + 1);

    }

    // TODO Leetcode第429题 N叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                Node cur = queue.poll();
                list.add(cur.val);
                for (Node node : cur.children) {
                    if (node != null) queue.add(node);
                }
            }
            res.add(list);
        }
        return res;
    }

    // TODO Leetcode第226题 翻转二叉树  思路：深度优先搜索（dfs）递归
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode cur = root;
        dfs(cur);
        return root;

    }

    public static void dfs(TreeNode cur) {
        if (cur == null) return;
        if (cur != null) {
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
        }
        dfs(cur.left);
        dfs(cur.right);
    }

    // TODO 递归遍历反转
    public static TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    // TODO Leetcode第101题 对称二叉树  递归
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        //调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right);
    }

    public static boolean dfs(TreeNode left, TreeNode right) {
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        //再递归的比较 左节点的左孩子 和 右节点的右孩子
        //以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
    //TODO 迭代

    public static boolean isSymmetric1(TreeNode root) {
        return check(root, root);
    }

    public static boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

    // TODO leetcode第98题 验证二叉搜索树  中序遍历（非递归）
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
//        if (root.left == null && root.right == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        long preValue = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= preValue) {
                    return false;
                } else {
                    preValue = root.val;
                }
                root = root.right;
            }
        }
        return true;
    }

    public static long preValue = Long.MIN_VALUE;

    // TODO 递归中序遍历
    public static boolean isValdBState(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean b = isValdBState(root.left);
        if (!b) return false;
        if (root.val <= preValue) {
            return false;
        } else {
            preValue = root.val;
        }
        return isValdBState(root.right);

    }

    // TODO leetcode第222 完全二叉树的节点个数  思路：广度优先搜索实现层序遍历，记录每一层得结点数量。
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int nodeNum = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                root = queue.poll();
                nodeNum++;
                levelSize--;
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
        }
        return nodeNum;
    }

    // TODO leetcode第111题  二叉树的最小深度
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }

    // TODO 257二叉树的所有路径
    @Test
    public void test1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        List<String> res = LeetCodeBinaryTree.binaryTreePaths(root);
        System.out.println(res);

    }


    public static List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        String s = "";
        dfs(res, root, s);
        return res;
    }

    public static void dfs(List<String> path, TreeNode cur, String s) {
        if (cur == null) return;// 当前节点为空
        if (cur.right == null && cur.left == null) {
            path.add(s += cur.val); //最后一个节点不需要加“->”
            return;
        }
        s += cur.val + "->";
        dfs(path, cur.left, s); //遍历左孩子节点
        dfs(path, cur.right, s);//遍历右孩子节点
    }


    //LeetCode第404题 左叶子之和
    //思路  广度优先搜索 遍历每个节点node，node的是左子节点是叶结点时，就将node的左子节点对应的值累加入答案

    /**
     * @description: 计算所有左叶子节点对应值的总和
     * @param: root
     * @return: int
     * @author 86156
     * @date: 2023/5/13 20:12
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (isLeafNode(cur.left)) { //左节点如果为叶子节点，即加该节点对应的值进行累加
                    sum += cur.left.val;
                } else {
                    queue.offer(cur.left);//不是叶子节点时，入队
                }

            }
            if (cur.right != null) {
                if (!isLeafNode(cur.right)) { //右节点如果为叶子节点，入队
                    queue.offer(cur.right);
                }
            }
        }
        return sum;
    }

    /**
     * @description: 判断当前节点是否是叶子节点
     * @param: node
     * @return: boolean
     * @author 86156
     * @date: 2023/5/13 20:11
     */
    public static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
    //深度优先搜索


    //leetcode第513题 找树左下角的值
    //假设二叉树中至少有一个节点。
    // 思路：广度优先搜索

    /**
     * @description: 给定一个二叉树的 根节点 root，请找出该二叉树的最底层最左边节点的值。
     * @param: root
     * @return: int
     * @author 86156
     * @date: 2023/5/13 21:08
     */
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode leftNode = null; //leftNode记录当前层最左边的节点
        int size = 0; //当前队列的大小
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    leftNode = cur; //第一次循环的节点就是每一层最左边的节点，每到一层就更新leftNode
                }
                if (cur.left != null) {  //左节点入队
                    queue.offer(cur.left);
                }
                if (cur.right != null) { //右节点入队
                    queue.offer(cur.right);
                }
            }
        }
        return leftNode.val; //返回最底层最左边节点的值
    }
    //深度优先搜索
    //leetcode第112题 路经总和：
    //思路：广度优先搜索 将父节点加到所有子节点上，每遍历到一层时，判断当前节点是不是叶子节点并且它的值是否与targetSum相等。

    /**
     * @description: 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * 如果存在，返回 true ；否则，返回 false 。
     * @param: root，targetSum
     * @return: boolean
     * @author 86156
     * @date: 2023/5/13 21:09
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null && cur.right == null && cur.val == targetSum) { //判断当前节点是不是叶子节点并且它的值是否与targetSum相等
                return true;
            }
            if (cur.left != null) {
                cur.left.val += cur.val; //将父节点的值累加到叶子节点
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                cur.right.val += cur.val; //将父节点的值累加到叶子节点
                queue.offer(cur.right);
            }
        }
        return false;
    }
    //思路：深度优先搜索
    //leetCode第106题  从中序与后序遍历序列构造二叉树
// 树还原过程描述
    //思路： 1首先在后序遍历序列中找到根节点(最后一个元素)
    //      2根据根节点在中序遍历序列中找到根节点的位置
    //      3根据根节点的位置将中序遍历序列分为左子树和右子树
    //      4根据根节点的位置确定左子树和右子树在中序数组和后续数组中的左右边界位置
    //      5递归构造左子树和右子树
    //      6返回根节点结束
//树的还原过程变量定义
    //    HashMap memo 需要一个哈希表来保存中序遍历序列中,元素和索引的位置关系.因为从后序序列中拿到根节点后，要在中序序列中查找对应的位置,从而将数组分为左子树和右子树
    //    int ri 根节点在中序遍历数组中的索引位置
    //    中序遍历数组的两个位置标记 [is, ie]，is 是起始位置，ie 是结束位置
    //    后序遍历数组的两个位置标记 [ps, pe] ps 是起始位置，pe 是结束位置
//位置关系计算
    //在找到根节点位置以后，需要确定下一轮中，左右子树在中序数组和后续数组中的左右边界的位置
    //     中序-左子树：is = is，ie = ri - 1
    //     中序-右子树：is = ri + 1，ie = ie
    //     后序-左子树：ps = ps，pe = ps + ri - is -1
    //     后序-右子树：ps = ps + ri - is,pe = pe - 1

    public HashMap<Integer, Integer> map = new HashMap<>();
    public int[] post;

    /**
     * @description: 后序+中序构建二叉树
     * @param: postorder:后序序列 inorder：中序序列
     * @return: BinaryTree.LeetCodeBinaryTree.TreeNode 根节点
     */
    public TreeNode buildTree(int[] postorder, int[] inorder) {
        post = postorder;
        for (int i = 0; i < post.length; i++) map.put(inorder[i], i);
        TreeNode root = buildTree(0, post.length - 1, 0, inorder.length - 1);
        return root;
    }

    /**
     * @description: 根据后序序列中根节点位置分割中序序列
     * @param:is： 中序开始位置
     * @param:ie： 中序结束位置
     * @param:ps： 后序开始位置
     * @param:pe： 后序结束位置
     * @return: BinaryTree.LeetCodeBinaryTree.TreeNode
     * @author 86156
     * @date: 2023/5/26 10:33
     */
    public TreeNode buildTree(int is, int ie, int ps, int pe) {
        if (ps > pe || is > ie) {
            return null;
        }
        int root = post[pe];
        int ri = map.get(root);

        TreeNode node = new TreeNode(root);
        node.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
        node.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
        return node;
    }
    //第二种方法：

    //测试由中序和后序进行建树
    @Test
    public void test3() {
        int[] inorder = {4, 2, 8, 5, 9, 1, 6, 10, 3, 7};
        int[] postorder = {4, 8, 9, 5, 2, 10, 6, 7, 3, 1};
        TreeNode root = buildTree(postorder, inorder);
        printTree(root);
    }

    //LeetCode第105题 从前序与中序遍历序列构造二叉树
    HashMap<Integer, Integer> memo = new HashMap<>();
    int[] pre;

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        pre = preorder;
        for (int i = 0; i < pre.length; i++) memo.put(inorder[i], i);
        TreeNode root = buildTree(0, preorder.length - 1, 0, inorder.length - 1);
        return root;
    }

    public TreeNode buildTree1(int is, int ie, int ps, int pe) {
        if (is > ie || ps > pe) {
            return null;
        }
        int root = pre[ps];
        int ri = memo.get(root);
        TreeNode node = new TreeNode(root);
        node.left = buildTree(is, ri - 1, ps + 1, ps + ri - is);
        node.right = buildTree(ri + 1, ie, ps + ri - is + 1, pe);
        return node;
    }

    //测试先序和中序建树
    @Test
    public void test4() {
        int[] inorder = {4, 2, 8, 5, 9, 1, 6, 10, 3, 7};
        int[] preorder = {4, 8, 9, 5, 2, 10, 6, 7, 3, 1};
        TreeNode root = buildTree(preorder, inorder);
        printTree(root);
    }


    //LeetCode第654题：最大二叉树
    int[] a;
    HashMap<Integer, Integer> memo1 = new HashMap<>();

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        a = nums;
        for (int i = 0; i < nums.length; i++) memo1.put(nums[i], i);
        TreeNode root = help(0, nums.length - 1);
        return root;
    }

    /**
     * @description: 找到数组某个范围下的最大值
     * @param: begin 开始处
     * @param: end   结束处
     * @return: int
     */
    public int getMaxIndex(int begin, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = begin; i <= end; i++) {
            max = a[i] > max ? a[i] : max;
        }
        int ri = memo1.get(max);
        return ri;
    }

    /**
     * @description: 递归构建最大二叉树
     * @param: arr_s 数组开始索引
     * @param: arr_e 数组结束索引
     * @return: BinaryTree.LeetCodeBinaryTree.TreeNode
     */
    public TreeNode help(int arr_s, int arr_e) {
        if (arr_s > arr_e) {
            return null;
        }
        int ri = getMaxIndex(arr_s, arr_e);
        int root = a[ri];
        TreeNode node = new TreeNode(root);
        node.left = help(arr_s, ri - 1);
        node.right = help(ri + 1, arr_e);
        return node;
    }

    //官方题解：
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    public TreeNode construct(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int best = left;
        for (int i = left + 1; i <= right; ++i) {
            if (nums[i] > nums[best]) {
                best = i;
            }
        }
        TreeNode node = new TreeNode(nums[best]);
        node.left = construct(nums, left, best - 1);
        node.right = construct(nums, best + 1, right);
        return node;
    }
    //单调栈解法

    //测试最大二叉树
    @Test
    public void test5() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree(nums);
        printTree(root);
    }

    @Test
    public void test6() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        TreeNode root = mergeTrees(root1, root2);
        printTree(root);
    }

    //LeetCode第617题 合并二叉树

    /**
     * @description: 合并两个二叉树
     * @param: root1
     * @param: root2
     * @return: BinaryTree.LeetCodeBinaryTree.TreeNode
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }

    //LeetCode第700题 二叉搜索树中的搜索

    /**
     * @description: 递归解法
     * @param: root
     * @param: val
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        return root = root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    /**
     * @description: 迭代解法
     * @param: root
     * @param: val
     * @return: BinaryTree.LeetCodeBinaryTree.TreeNode
     */
    public static TreeNode searchBST1(TreeNode root, int val) {

        while (root != null) {
            if (root.val == val) {
                return root;
            }
            root = root.val > val ? root.left : root.right;
        }
        return null;
    }

    //LeetCode第530题 二叉搜索树的最小绝对差
    //思路：中序递归遍历二叉搜索树，用集合数组记录中序序列，序列为递增序列，然后比较两两元素之间的差值。

    /**
     * @description:
     * @param: root
     * @return: int
     */
    public static int getMinimumDifference(TreeNode root) {
        List<Integer> diff = new ArrayList<>();
        inOrder1(root, diff);
        int difference;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < diff.size(); i++) {
            difference = Math.abs(diff.get(i) - diff.get(i - 1));
            minDiff = difference < minDiff ? difference : minDiff;
        }
        return minDiff;
    }

    /**
     * @description: 中序递归遍历二叉搜索树，并用集合数组res记录中序序列，此时的序列为递增序列
     * @param: root
     * @param: res
     * @return: void2
     */
    public static void inOrder1(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder1(root.left, res);
        res.add(root.val);
        inOrder1(root.right, res);
    }

    //官方题解：
//    int pre;
//    int ans;
//
//    public int getMinimumDifference1(TreeNode root) {
//        ans = Integer.MAX_VALUE;
//        pre = -1;
//        dfs(root);
//        return ans;
//    }
//
//    public void dfs(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        dfs(root.left);
//        if (pre == -1) {
//            pre = root.val;
//        } else {
//            ans = Math.min(ans, root.val - pre);
//            pre = root.val;
//        }
//        dfs(root.right);
//    }
    // 练习前中后三种遍历
    //先序非递归遍历
    public List<Integer> preOrderNoRecursion(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        List<Integer> res = new ArrayList<>();
        s.push(root);
        while (!s.isEmpty()) {
            root = s.pop();
            res.add(root.val);
            if (root.right != null) {
                s.push(root.right);
            }
            if (root.left != null) {
                s.push(root.left);
            }
        }
        return res;
    }

    //
    @Test
    public void test7() {
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
//        List<Integer> res = preOrderNoRecursion(root2);
//        List<Integer> res = inOrderNoRecursion(root2);
        List<Integer> res = postOrderNoRecursion(root2);
        System.out.println(res);

    }

    //中序非递归
    public List<Integer> inOrderNoRecursion(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        Stack<TreeNode> s1 = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (!s1.isEmpty() || root != null) {
            if (root != null) {
                s1.push(root);
                root = root.left;
            } else {
                root = s1.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    //后序遍历 非递归
    public List<Integer> postOrderNoRecursion(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> ass = new Stack<>();
        List<Integer> res = new ArrayList<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            ass.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }

        }
        while (!ass.isEmpty()) {
            TreeNode head = ass.pop();
            res.add(head.val);
        }
        return res;
    }

    //leetcode 第701题 二叉搜索树中的插入操作
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode head = root;
        TreeNode temp = null;
        while (root != null) {
            if (root.val > val) { //当前节点的值大于val，则val应该插入到该节点的左孩子中
                temp = root;  //temp记录当前要插入的结点
                root = root.left;
                if (root == null) { //如果左孩子为空，则插入；否则不插入
                    temp.left = new TreeNode(val);
                    return head; //插入后，则返回根节点
                }
            } else { //当前结点的值小于val，则val应该插入到该节点的右孩子中
                temp = root;
                root = root.right;
                if (root == null) { //如果右孩子为空，则插入；否则不插入
                    temp.right = new TreeNode(val);
                    return head; //插入后，则返回根节点
                }
            }
        }
        return head;
    }

    //leetcode第450题 删除二叉搜索树中的结点
    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root, curParent = null;
        int flag = -1; //记录要删除的孩子是左孩子还是右孩子
        while (cur != null && cur.val != key) {
            curParent = cur; //记录要删除节点的父节点
            if (key < cur.val) {
                flag = 1; //需要删除结点是curParent的左孩子
                cur = cur.left;
            } else {
                cur = cur.right;
                flag = 0; //需要删除结点是curParent的右孩子
            }
        }
        if (cur == null) return root; //找不到这个节点时，返回初始树
        if (cur.left == null && cur.right == null) {
            cur = null; //目标结点是叶子节点
        } else if (cur.left == null) {
            cur = cur.right; //目标结点无左子树
        } else if (cur.right == null) {
            cur = cur.left; //目标结点无右子树
        } else { //左右孩子都存在，此时找目标结点的后继结点进行替换
            TreeNode successor = cur.right, successorParent = cur;  //successor：后继节点  successorParent：后继结点的父节点
            while (successor.left != null) { //遍历寻找目标后继结点
                successorParent = successor;
                successor = successor.left;
            }
            if (successorParent.val == cur.val) { //如果后继结点父节点没有动，也就是一开始后继结点就没有左孩子
                successorParent.right = successor.right;  //后继结点的右子树作为后继父节点的右子树，二叉搜索树的性质
            } else {
                successorParent.left = successor.right; //否则将后继结点的右子树作为后继父节点的左子树，二叉搜索树的性质
            }
            successor.right = cur.right;  //要删除结点左右孩子与找到的后继结点连接，然后将后继结点作为目标结点
            successor.left = cur.left;
            cur = successor;
        }
        if (curParent == null) {
            return cur;
        } else {  //判断目标节点是其父节点的左孩子还是右孩子，进行连接
            if (flag == 1) {
                curParent.left = cur;
            } else {
                curParent.right = cur;
            }
        }
        return root;
    }

    //leetcode第669题 修剪二叉搜索树
//    public static TreeNode trimBState(TreeNode root, int low, int high) {
//        if (root == null) return null;
//        if (root.val > low && root.val < high) {
//
//        }
//    }

    //leetcode第538题 把二叉搜索树转换为累加树
    //递归版本：
    public static int sum = 0;

    public static TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        addBST(root);
        return root;
    }

    public static TreeNode addBST(TreeNode root) {
        if (root == null) return null;
        addBST(root.right);
        root.val += sum;
        sum = root.val;
        addBST(root.left);
        return root;
    }

    //官方题解

    /**
     * @description: 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * @param: root
     * @return: BinaryTree.LeetCodeBinaryTree.TreeNode
     */
    public static TreeNode convertBST1(TreeNode root) {
        if (root == null) return null;
        convertBST1(root.right);
        root.val += sum;
        sum = root.val;
        convertBST1(root.left);
        return root;
    }

    //TODO leetcode第108题 将有序数组转换为二叉搜索树
    int[] arr; //设置一个全局数组变量

    public TreeNode sortedArrayToBST(int[] nums) {
        arr = nums;
        TreeNode root = helper(0, nums.length - 1);
        return root;
    }

    public TreeNode helper(int begin, int end) {
        if (end < begin) return null;
        int mid = (begin + end) / 2; //找到划分后数组的中间元素的下标 mid
        TreeNode root = new TreeNode(arr[mid]); //以数组中间元素建树，保证平衡
        root.left = helper(begin, mid - 1); //构建左树
        root.right = helper(mid + 1, end);  //构建右树
        return root;
    }

    //TODO 第669题 修剪二叉搜索树
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
    //TODO leetcode 1457 二叉树的伪回文路径
    /**
     * 对每一条路径上的所有节点出现的次数进行计数，如果有超过1个节点计数为奇数，则该路径上的节点无法重新排列为回文串
     * 也就是这一条路径不是伪回文串
     * 初始化一个数组counter，记录节点出现的次数，节点的值作为数组下标
     * odd_node记录该路径上计数为奇数的节点个数 odd_node > 1,则该路径不为伪回文。
     */
    int path1 = 0;

    public int pesudoPalindromicPaths(TreeNode root) {
        int[] counter = new int[10];
        dfs(root, counter, 0);
        return path1;

    }

    public void dfs(TreeNode cur, int[] counter, int odd_node) {
        if (cur == null) return;
        counter[cur.val]++; // 节点出现一次计数一次
        if (counter[cur.val] % 2 != 0) {
            odd_node++; //如果计数不为偶数，则odd_node加1p
        } else {
            odd_node--; //如果计数为偶数，则odd_node减1
        }
        if (cur.left == null && cur.right == null) { //如果这个节点为叶子节点则路径遍历结束，判断odd_node是否小于2
            if (odd_node < 2) {
                path1++;//小于2，则该路径为伪回文
            }
        }
        //深度优先搜索遍历剩下的节点
        dfs(cur.left, counter, odd_node);
        dfs(cur.right, counter, odd_node);
        counter[cur.val]--; //形参为引用数据类型或者为全局变量时，需要对其进行修改，让其回到上一层递归的状态
        return;
    }

    @Test
    public void testPesudoPalindromicPaths() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.left.right.right = new TreeNode(1);
        printTree(root);
//        int res = pesudoPalindromicPaths(root);
        List<Integer> ans = new ArrayList<>();
        dfsTreePathNodeNum(root, ans);
//        System.out.println(res);
    }
    public void dfsTreePathNodeNum(TreeNode cur, List<Integer> ans) {
        if (cur == null) return;
//        count++;
        ans.add(cur.val);
        if (cur.left == cur.right) {
            System.out.println(ans.toString());
        }
        dfsTreePathNodeNum(cur.left, ans);
        dfsTreePathNodeNum(cur.right, ans);
    }

    // 位运算
    int ans = 0;
    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    void dfs(TreeNode root, int cnt) {
        if (root.left == null && root.right == null) {
            cnt ^= 1 << root.val;//1左移i为代表，代表二进制从右往左数（i从0开始），数字i出现。
            if (cnt == (cnt & -cnt)) ans++; //cnt & -cnt的结果为cnt二进制保留最低位的1，如果cnt = (cnt & -cnt)则代表最多只有一个字符
                                            // 出现奇数次
            return;
        }
        if (root.left != null) dfs(root.left, cnt ^ (1 << root.val));
        if (root.right != null) dfs(root.right, cnt ^ (1 << root.val));
    }

    // 153. 二叉树中和为目标值的路径
    @Test
    public  void testPathTarget() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
//        printTree(root);
        List<List<Integer>> lists = pathTarget(root, 22);
        System.out.println(lists.toString());
    }
    static List<List<Integer>> res1 = new ArrayList<>();
    public static List<List<Integer>> pathTarget(TreeNode root, int target) {
        if (root == null) return res1;
        List<Integer> path = new ArrayList<>();
        dfs(root, target, 0, path);
        return res1;
    }
    public static void dfs(TreeNode cur, int target, int sum, List<Integer> path) {
        if (cur == null) return;
        sum += cur.val;
        path.add(cur.val);
        if (cur.right == cur.left) {
            if (sum == target) {
                res1.add(new ArrayList<>(path));
            }
        }
        dfs(cur.left, target, sum , path);
        dfs(cur.right, target, sum, path);
        path.remove(path.size() - 1);

    }
}


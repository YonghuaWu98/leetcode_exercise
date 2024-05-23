package tree;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName:BinarySearchTree
 * @Description TODO
 * @Author 86156
 * @Date 2023/4/25 11:25
 * @Version 1.0
 **/
public class BinarySearchTree {
    class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    public  static long preValue = Long.MIN_VALUE;

    @Test
    public void test() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(7);
//        root.right.right = new TreeNode(9);
//        boolean b = BinarySearchTree.checkBST(root);
//        System.out.println(b);
//        boolean b = BinarySearchTree.checkBST1(root);
//        System.out.println(b);
//        System.out.println();
//        List<Integer> result = BinarySearchTree.postOrderUnRecursions(root);
//        System.out.println(result);
//        System.out.println();
        boolean b = BinarySearchTree.isValidBST(root);
        System.out.println(b);

    }
    // TODO LeetCode题解
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.value <= lower || node.value >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.value) && isValidBST(node.right, node.value, upper);
    }

    // TODO 判断二叉树是否是搜索二叉树
    //  方法一
    public static boolean checkBST(TreeNode root) {
        if (root == null) return true;
        // 检查左子树是不是搜索二叉树
        boolean isLeftBst = checkBST(root.left);
        // 如果左子树不是，则返回false
        if (!isLeftBst) return false;
        // 判断当前节点的值是否小于前一个遍历节点的值
        if (root.value <= preValue) {
            // 小于等于说明不是搜索二叉树，返回false
            return false;
        } else {
            // 如果大于，则将当前结点的值赋值为preValue，继续判断
            preValue = root.value;
        }
        return checkBST(root.right);
    }
    // TODO 方法二 利用中序遍历搜索二叉树得到的序列为升序的特点
    public static boolean checkBST1(TreeNode root) {
        if (root == null) return true;
        List<Integer> inOrderList = new ArrayList<>();
        process2(root, inOrderList);
        System.out.println(inOrderList);
        for (int i : inOrderList) {
            if (i > preValue) {
                preValue = i;
            } else {
                return false;
            }
        }
        return true;
    }
    public static void process2(TreeNode root, List<Integer> inOrderList) {
        if (root == null) return;
        process2(root.left, inOrderList);
        inOrderList.add(root.value);
        process2(root.right, inOrderList);

    }
    // TODO 方式三：中序不递归
    public static boolean checkBST3(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                //当是左结点时，由于是中序，此时是比较父节点与左孩子结点比较；
                // 而是右节点时，右节点的值始终大于preValue
                if (root.value <= preValue) {
                    return false;
                }else {
                    preValue = root.value;
                }
                root = root.right;
            }
        }
        return true; //当整个while结束时，说明该树为搜索二叉树，返回true。
    }


    //TODO 方式四：
    @Test
    public void test1() {

        TreeNode root = new TreeNode(6);
//        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(4);
//        ReturnData process = process(root);
//        System.out.println(process.isSBT);
        int i = minDepth(root);
        System.out.println(i);
    }

    public static class ReturnData {
        public boolean isSBT;
        public int max;
        public int min;
        public ReturnData(boolean isS, int ma, int mi) {
            isSBT = isS;
            max = ma;
            min = mi;
        }
    }
    public static ReturnData process(TreeNode root) {
        if (root == null) {
            return null;
        }
        ReturnData leftData = process(root.left);
        ReturnData rightData = process(root.right);

        int min = root.value;
        int max = root.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(min, rightData.max);
        }
        boolean isBST = true;
        if (leftData != null && (!leftData.isSBT || leftData.max >= root.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isSBT || root.value >= rightData.min)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);

    }
    // TODO 每日一练  先中后序遍历非递归写法
    //先序遍历(非递归）
    public static void preTraverse(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.println(root.value + " ");
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }
    //中序遍历（非递归）
    public static void inTraverse(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                System.out.println(root.value + " ");
                root = root.right;
            }
        }
    }
    //后序遍历（非递归）
    public static void postTraverse(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            s2.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }
        }
        while (!s2.isEmpty()) {
            TreeNode cur = s2.pop();
            System.out.println(cur.value + " ");
        }
    }

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

}

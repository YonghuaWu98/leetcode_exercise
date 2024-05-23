package tree;

/**
 * @ClassName:BalancedBinaryTree
 * @Description TODO
 * @Author 86156
 * @Date 2023/4/27 9:13
 * @Version 1.0
 **/
public class BalancedBinaryTree {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int value) {val = value;}
    }
    //TODO 判断一棵树是否是平衡二叉树
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int hei) {
            isBalanced = isB;
            height = hei;
        }
    }
    public static ReturnType process(Node root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }






}




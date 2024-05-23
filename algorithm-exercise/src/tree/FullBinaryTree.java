package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName:FullBinaryTree
 * @Description TODO
 * @Author 86156
 * @Date 2023/4/26 16:21
 * @Version 1.0
 **/
public class FullBinaryTree {

    class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    @Test
    public void test() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        boolean fullBinaryTree = FullBinaryTree.isFullBinaryTree(root);
        System.out.println(fullBinaryTree);

        boolean f = FullBinaryTree.isF(root);
        System.out.println(f);

    }

    //TODO 判断一棵树是否是完全二叉树
    public static boolean isFullBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isFBT = false;
        while (!queue.isEmpty()) {
            root = queue.poll();
            TreeNode l = root.left;
            TreeNode r = root.right;
            //前面那个判断条件：如果已经遇到了左右孩子都没有的情况下，又发现当前节点不是叶节点 返回false。
            //后面那个判断条件：如果没有遇到左右孩子都没有的情况下，发现左孩子为空，右孩子不为空，则返回false。
            if ((isFBT && !(l == null && r == null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            if (l != null && r == null) { //如果左孩子不为空，右孩子为空，则把isFBT设置为true，此后遇到的节点都应该是叶子节点。
                isFBT = true;
            }
        }
        return true;
    }


    // TODO 判断一棵树是否是满二叉树
    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }
    public static boolean isF(TreeNode root) {
        if (root == null) {
            return true;
        }
        Info full2 = isFull2(root);
        return full2.nodes == ((1 << full2.height) - 1);
    }
    public static Info isFull2(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftData = isFull2(root.left);
        Info rightData = isFull2(root.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Info(height, nodes);

    }
    //TODO leetcode第222题 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int result = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                root = queue.poll();
                levelSize--;
                result++;
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
        }
        return result;
    }
}

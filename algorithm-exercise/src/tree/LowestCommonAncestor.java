package tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName:LowestCommonAncestor
 * @Description TODO 寻找二叉树的最近公共祖先
 * @Author 86156
 * @Date 2023/4/27 15:49
 * @Version 1.0
 **/
public class LowestCommonAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
        Node p = root.left.left;
        Node q = root.left.right.left;
        Node node = lowestCommonAncestor1(root, p, q);
        System.out.println(node.value);
    }
    //两个结点有如下可能结构：
    // 1） O1是O2的LCA，或o2是o1的LCA
    // 2)  O1与O2不互为LCA，要往上才能找到
    //方法一：
    // 给出两个二叉树的节点，找到它们最低的公共祖先节点。
    public static Node lowestCommonAncestor(Node root, Node o1, Node o2) {
        if (root == null || root == o1 || root == o2) {
            return root;
        }
        Node left = lowestCommonAncestor(root.left, o1, o2);
        Node right = lowestCommonAncestor(root.right, o1, o2);
        // 当两结点是情况二时，if才有效，此时root为两结点的LCA
        if (left != null && right != null) {
            return root;
        }
        // 左右两棵树，并不都有返回值
        return left != null ? left : right;
    }
    //方法二：
    public static Node lowestCommonAncestor1(Node root, Node p, Node q) {
        // 创建一个HashMap记录每个节点的父节点
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(root, root); //根节点的父节点为自己
        process(root, fatherMap);  //
        HashSet<Node> set1 = new HashSet<>(); // set1收集根节点root到p节点的路径
        Node cur = p;
        while (cur != fatherMap.get(cur)) { // cur不等于root
            set1.add(cur);   //一次往上加入父节点
            cur = fatherMap.get(cur); //cur的父节点，一直往上找父节点
        }
        set1.add(root);// 加入root
        Node cur1 = q;
        while (cur1 != fatherMap.get(cur1)) { //
            if (set1.contains(cur1)) {
                return cur1;
            }else {
                cur1 = fatherMap.get(cur1);
            }

        }
        return root;
    }
    public static void process(Node root, HashMap<Node, Node> fatherMap) {
        if (root == null) return;
        fatherMap.put(root.left, root);
        fatherMap.put(root.right, root);
        process(root.left, fatherMap);
        process(root.right, fatherMap);

    }
}

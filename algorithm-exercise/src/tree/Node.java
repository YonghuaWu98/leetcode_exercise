package tree;

import java.util.*;

/**
 * @Description TODO
 * @Author 86156
 * @Date 2023/11/3 16:51
 * @Version 1.0
 **/
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
//        root.left.right.right = new Node(3);
//        root.right.left = new Node(5);
        root.right.right = new Node(7);
//        root.right.right.right = new Node(8);
        Node connect = Node.connect(root);

    }

    // leetcode117题 填充每个借点的下一个右侧节点指针2
    //大致思路：先遍历节点的右孩子再遍历左孩子，用队列进行收集，每一次收集用map记录该节点在树中的深度，同时出队时，如果发现当前节点cur与
    // 上一次出现的节点的深度不相等，则说明它是树当前层最右边的节点，让它的next指针指向null，如果相等，则指向上一次出队的节点，用temp记录上一次
    //出现的节点
    public static Node connect(Node root) {
        if (root == null) return null;
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(root, 1);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int lastLevel = 1;
        Node temp = null;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur != root) {
                if (map.get(cur) == lastLevel) {
                    cur.next = temp;
                    temp = cur;
                } else {
                    cur.next = null;
                    temp = cur;
                    lastLevel = map.get(cur);
                }
            }
            if (cur.right != null) {
                map.put(cur.right, map.get(cur) + 1);
                q.add(cur.right);
            }
            if (cur.left != null) {
                map.put(cur.left, map.get(cur) + 1);
                q.add(cur.left);
            }
        }
        return root;
    }
    //使用虚拟节点，不使用队列的BFS遍历

    public static Node connect1(Node root) {
        Node cur = root;
        while (cur != null) {
            Node dummy = new Node(0);
            Node p = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    p.next = cur.left;
                    p = p.next;
                }
                if (cur.right != null) {
                    p.next = cur.right;
                    p = p.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }

    //递归解法

    static List<Node> pre = new ArrayList<>();

    public static Node connect3(Node root) {
        dfs(root, 0); // 根节点的深度为 0
        return root;
    }

    private static void dfs(Node node, int depth) {
        if (node == null) {
            return;
        }
        if (depth == pre.size()) { // node 是这一层最左边的节点
            pre.add(node);
        } else { // pre[depth] 是 node 左边的节点
            pre.get(depth).next = node; // node 左边的节点指向 node
            pre.set(depth, node);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }


}

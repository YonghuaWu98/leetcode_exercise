package tree.tree_dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 **/
public class DpTree {
    class TreeNode  {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
    @Test
    public void test() {
        int[][] edges = {{2,0},{4,1},{5,3},{4,6},{2,4},{5,2},{5,7}};
        int[] values = {12,12,7,9,2,11,12,25};
        long l = maximumScoreAfterOperations1(edges, values);
        System.out.println(l);
    }

    /**
     *  TODO leetcode 2925 在树上执行操作以后得到的最大分数
     */
    // 此做法没有考虑到这个图是一个无向图
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            list.add(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        long sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum - dfs(list, values, 0);

    }
    public long dfs(List<List<Integer>> graph, int[] values, int x) {
        if (graph.get(x).size() == 0) {
            return values[x];
        }

        long loss = values[x];
        long loss2 = 0;
        for (int i = 0; i < graph.get(x).size(); i++) {
            loss2 += dfs(graph, values, graph.get(x).get(i));
        }
        return loss > loss2 ? loss2 : loss;
    }
    // todo 难点：1. 如何从一个二维数组构造图   2. 如何从问题中分解出子问题  3.如何在解题中正确使用递归

    public static long maximumScoreAfterOperations1(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] g = new ArrayList[n]; //初始化图大小，大小为所有节点的个数
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) { //无向图构造
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        long sum = 0;
        for (int e : values) {
            sum += e;
        }
        g[0].add(-1); //防止把编号为0的节点当作叶子节点

        return sum - dfs1(g, values, 0, -1);
    }

    public static long dfs1(List<Integer>[] g, int[] values, int cur, int fa) {
        //递归结束条件
        if (g[cur].size() == 1) return values[cur];
        long loss = values[cur];//不选父节点损失，该节点的价值values[cur]
        long loss2 = 0;//选了父节点，损失的是所有子节点的价值之和
        for (int child : g[cur]) {
            if (child != fa) { // 避免往父节点递归，在图中，即避免回到出发节点
                loss2 += dfs1(g, values, child, cur);
            }
        }
        return Math.min(loss, loss2);
    }

    // todo 打家劫舍
    public static int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);

    }
    public static int[] dfs(TreeNode cur) {
        int[] res = new int[2];
        if (cur == null) return res;
        int[] left = dfs(cur.left);
        int[] right = dfs(cur.right);
        res[0] = cur.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}

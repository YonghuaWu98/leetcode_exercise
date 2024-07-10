package test.javaAPItest;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Description TODO 测试自定义排序

 **/
public class SortTest {

    public static void main(String[] args) {
//        Integer[] arr = {3, 5, 4, 6, 7, 1, 8, 9};
        int[][] arr = {{1, 2}, {2, 3}, {2, 1}, {3, 5}, {4, 4}};
        Arrays.sort(arr, (a, b) -> {
//            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];// 如果b - a大于零，就交换，降序排列
        });
//        System.out.println(Arrays.toString(arr));
        List<int []> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
           ans.add(arr[i]) ;
        }
        ans.sort((a, b) -> a[0] - b[0]);
//        System.out.println(ans.toString());
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(Arrays.toString(ans.get(i)));
        }



    }
    @Test
    public void testListSort() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(-3);
        list.add(2);
        list.add(4);
        Collections.sort(list);
        System.out.println(list);
    }
    @Test
    public void test() {
        int[][] edges = {{0,1}, {0,2},{0,3},{2,4},{4,5}};
        List<List<Integer>> lists = maximumScoreAfterOperations(edges);
//        StringBuilder builder = new StringBuilder();
        for (List<Integer> row : lists) {

                System.out.println(row + "");

        }

    }
    public List<List<Integer>> maximumScoreAfterOperations(int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        return list;

    }
    @Test
    public void testDfs() {
        int mask = dfs(0, 5, 3, 0);
        System.out.println(mask);
    }
    public int dfs(int indexStart, int m, int numSelect, int mask) {
        if (numSelect == 0) return mask;
        for (int i = indexStart; i < m; i++) {
            mask |= 1 << i;
            numSelect -= 1;
            dfs(i + 1, m, numSelect, mask);
            numSelect += 1;
            mask ^= 1 << i;

        }
        return 0;
    }

    private boolean[] visited;
    private int[][] memo;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        int[][] g = new int[n][n];
        for (int[] e: edges) {
            int x = e[0];
            int y = e[1];
            g[x][y] = e[2];
            g[y][x] = e[2];
        }
        //记录哪些节点能够在 maxTime 到达
        visited = new boolean[n];
        //记录能够到达的节点的时间和路径价值
        memo = new int[n][2];
        // 0 节点到 0 节点的时间为 0 ，路径价值为 values[0]
        memo[0][1] = values[0];
        dfs(g, values, maxTime, 0, 0, values[0]);
        return  0;
    }
    /**
     *
     *
     */
    void dfs(int[][] g, int[] values, int maxTime, int x, int time, int value) {
        if (visited[x]) return;
        if (time * 2 > maxTime) return ;

        memo[x][0] = time;
        memo[x][1] = value;
        visited[x] = true;
        for (int i = 0; i < values.length; i++) {
            if (g[x][i] == 0) continue;
            time += g[x][i];
            value += values[i];
            dfs(g, values, maxTime, i, time, value);
        }
    }
    @Test
    public void testMaximalPathQuality() {
        int[] values = {0, 32, 10, 43};
        int[][] edges = {{0,1,10},{1,2,15},{0,3,10}};
        int maxTime = 49;
    }
}

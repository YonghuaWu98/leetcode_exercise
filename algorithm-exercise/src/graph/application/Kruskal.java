package graph.application;

import java.util.Arrays;
import java.util.Scanner;
/*
 * kruskal 寻宝
 */
public class Kruskal {
    static int[] parent;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int[][] graph = new int[m][3];
        int x, y, w;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            w = in.nextInt();
            graph[i][0] = x;
            graph[i][1] = y;
            graph[i][2] = w;
        }
        //按边的权重进行排序
        Arrays.sort(graph, (a, b) -> a[2] - b[2]);
        int ans = 0;
        for (int[] g : graph) {
            int u = g[0];
            int v = g[1];
            if (!isConnect(u, v)) {
                ans += g[2];
            }
        }
        System.out.println(ans);
    }
    static boolean isConnect(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return true;

        parent[x] = y;
        return false;
    }
    static int find (int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    
    
    /**
     * 连接所有点的最小费用
     **/


}


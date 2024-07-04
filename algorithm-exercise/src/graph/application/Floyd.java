package graph.application;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 吴勇华
 * @description: Floyd算法
 */
public class Floyd {
    // 代码随想录 小明逛公园
    private static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] g = new int[n][n];

        for (int[] e : g) {
            Arrays.fill(e, INF);
        }

        for (int i = 0; i < m; i++) {
            int s = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();
            g[s - 1][e - 1] = w;// 注意有向边和无向边
            g[e - 1][s - 1] = w;

        }
        // Floyd核心
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int p = in.nextInt();
        for (int i = 0; i < p; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (g[x - 1][y - 1] == INF) System.out.println(-1);
            else System.out.println(g[x - 1][y - 1]);
        }

    }


}

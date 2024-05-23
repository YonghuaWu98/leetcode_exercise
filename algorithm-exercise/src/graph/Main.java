package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  TODO 图论
 **/
public class Main {

//    final static int N = 100000;
    static int ans = 0;
//    static List<Integer>[] g = new ArrayList[N];
//    static boolean[] st = new boolean[N];
    static int n, d;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        d = in.nextInt();
        List<Integer>[] g = new ArrayList[n];
        boolean[] st = new boolean[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            g[u - 1].add(v - 1);
            g[v - 1].add(u - 1);
        }
        dfs(0,1, g, st);
        System.out.println(ans);
    }

    public static void dfs(int x, int k, List<Integer>[] g, boolean[] st) {
        if (k > d) {
            return;
        }
        st[x] = true;
        for (int y : g[x]) {
            if (!st[y]) {
                ans += 1;
                dfs(y, k + 1, g, st);
            }
        }
    }

    //

}

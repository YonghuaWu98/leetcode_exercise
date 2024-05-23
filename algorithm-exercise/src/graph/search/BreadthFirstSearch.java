package graph.search;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Description TODO 广度优先搜索（图论）  刷题记录
 *
 **/

public class BreadthFirstSearch {
    // 547. 省份数量:
    // todo 广度优先搜索
    public boolean[] vis;//标记已被访问过的结点
    public int findCircleNum1(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans += 1;
                bfs(isConnected, i);
            }
        }
        return ans;
    }
    void bfs(int[][] isConnected, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(k);
        while(!q.isEmpty()) {
            int a = q.pop();
            vis[a] = true;
            for (int i = 0; i < isConnected[a].length; i++) {
                if (i == a || isConnected[a][i] == 0 || vis[i]) continue;
                q.add(i);
            }
        }
    }


}

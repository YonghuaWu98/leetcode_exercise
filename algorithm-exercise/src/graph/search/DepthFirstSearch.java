package graph.search;

import org.junit.Test;

import java.util.*;

/**
 * @Description TODO 深度优先搜索 图论刷题记录
 *
 **/
public class DepthFirstSearch {

    //leetcode 797.所有可能的路径
    // res收集所有满足条件的路径， path收集单个路径
    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> path = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0);
        dfs(graph, 0);
        return res;
    }
    public void dfs(int[][] graph, int e) {
        if (e == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i : graph[e]) {

            path.add(i);
            // 递归遍历当前结点的所有子节点
            dfs(graph, i);
            // 回溯，移除上一个遍历的图结点
            path.remove(path.size() - 1);
        }
    }
    @Test
    public void test() {
        int[][] graph = new int[][] {{1,2}, {3}, {3}, {}};
        List<List<Integer>> lists = allPathsSourceTarget(graph);
        System.out.println(lists.toString());
    }


    // 547. 省份数量:
    // todo
    //  思路一：采用深度优先搜索遍历，一次遍历确定一个省份（连通分量），并用visited数组标记
    //  思路二：广度优先搜索（见BreadthFirstSearch.findCircleNum1)
    //  思路三：并查集（见tree.forest.and_search_the_collection.EXERCISE.findCircleNum2)
    // 思路一实现：
    public boolean[] visited; //标记已被访问过的结点
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) { //遍历每一个结点，采用dfs遍历以该节点为起点的连通分量。
                                        // 如果标为true，表示已经访问过，不需要再次遍历
            if (!visited[i]) {
                ans += 1;
                dfs1(isConnected, i);
            }
        }
        return ans;
    }
     void dfs1(int[][] isConnected, int k) {
        for (int i = 0; i < isConnected[k].length; i++) {
            if (visited[i]) continue;
            if (isConnected[k][i] == 1) {
                visited[i] = true;
                dfs1(isConnected, i);
            }
        }
    }




    boolean[] vis;//用于标记访问过的节点
    int edge;//每个连通分量中边的数量
    public int makeConnected(int n, int[][] connections) {
        //初始化标记数组
        vis = new boolean[n];
        //邻接表法存储图
        List<Integer>[] tb = new ArrayList[n];
        //初始化邻接表
        Arrays.setAll(tb, e->new ArrayList<>());
        for(int i = 0; i < connections.length; i++) {
            int x = connections[i][0];
            int y = connections[i][1];
            tb[x].add(y);
            tb[y].add(x);
        }
        //g_cnt:连通分量的数量
        int g_cnt = 0;
        //r_e:多余的边数
        int r_e = 0;
        //遍及每个连通分量
        for (int i = 0; i < tb.length; i++) {
            //初始化每个连通分量的边数
            edge = 0;
            //n_cnt:连通分量的节点数
            int n_cnt = 0;
            //当前节点没访问过，dfs一次找到一个连通分量
            if (!vis[i]) {
                //连通分量数加1
                g_cnt += 1;
                //dfs计算一个连通分量的节点数
                n_cnt = dfs(tb, i);
                //一个连通分量所有的边减去n_cnt个节点连通最少的边数即为多余边数，累加每一个连通分量多余的边
                r_e += edge - n_cnt + 1;
            }
        }
        //剩余边足够将其他连通分量相连接
        return r_e >= g_cnt - 1 ? g_cnt - 1 : -1;
    }
    //深度优先搜索
    int dfs(List<Integer>[] a, int x) {
        vis[x] = true;//表示访问过当前节点
        int n_cnt = 1;//初始节点为1
        for (int e : a[x]) {
            //累加一个连通分量的边数
            if(e > x) {
                edge += 1;
            }
            if (!vis[e]) {
                n_cnt += dfs(a, e);
            }
        }
        return n_cnt;
    }

    @Test
    public void test1() {
        int n = 4;
        int[][] a = new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 2}};
        int res = makeConnected(n, a);
    }


}

package graph;

import java.lang.reflect.Array;
import java.util.*;

/**
 *  TODO 图的存储
 *  假设图有n个顶点，m条边
 **/
public class GraphStorage {

    // todo 直接存边法  直接存边的遍历效率低下，一般不用于遍历图
    /**
     * 复杂度分析
     *
     * 查询是否存在某条边：O(m)。
     *
     * 遍历一个点的所有出边：O(m)。
     *
     * 遍历整张图：O(nm)。
     *
     * 空间复杂度：O(m)。
     */
    static class Edge {  //边
        int u; //弧的起点
        int v; //弧的终点
        // 构造弧的起点和终点
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    static Edge[] edges; //创建一个动态数组保存边
    static boolean[] visited; // 记录访问过的结点
    /**
     * 直接存边，图的常用api设计
     */

    //查询是否存在某条边 时间复杂度分析 O(m)
    public static boolean isExistEdge(int u, int v) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].u == u && edges[i].v == v) {
                return true;
            }
        }
        return false;
    }

    //遍历某个点的所有出边 时间复杂度分析 O(m)
    public static void allOutgoingEdgesOfCertainVertex(int u) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].u == u) {
                System.out.println("遍历某一点的所有出边： " + edges[i].u + "-->" + edges[i].v);
            }
        }
    }

    // 遍历整个图  时间复杂度O(mn）
    public static void dfs(int u) {
        if (visited[u]) return;
        visited[u] = true;
        System.out.println(u);
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].u == u) {
                dfs(edges[i].v);
            }
        }
    }
    // todo 邻接矩阵
    /**
     *  使用一个二维数组odj来存边，其中odj[u][v]为 1 表示存在 u 到 v 的边，为 0 表示不存在。
     *  如果是带边权的图，可以在odj[u][v]中存储 u 到 v的边的边权。
     */


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); //图的结点数
        int m = in.nextInt(); //图的边数
        edges = new Edge[m];
        visited = new boolean[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            Edge edge = new Edge(u, v);
            edges[i] = edge;

        }
        // 查找是否存在1-2这条边
        boolean existEdge = GraphStorage.isExistEdge(1, 2);
        System.out.println("是否存在这样一条边：" + existEdge);
        GraphStorage.allOutgoingEdgesOfCertainVertex(0);
        // 遍历整个图
        dfs(0);

        // todo 邻接表 动态数组实现
        // 存储无向图
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            g[u - 1].add(v - 1);
            g[v - 1].add(u - 1);
        }


    }
}

package graph.application;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 以代码随想录的寻宝为例
 *
 **/
public class Prim {

    static int[] dis;//记录节点之间的最小距离
    static boolean[] vis;//哪些结点已经加入到最小生成树中
    public static void  main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//结点数
        int m = in.nextInt();//边数
        dis = new int[n + 1];
        vis = new boolean[n + 1];
        int[][] graph = new int[n + 1][n + 1];//邻接矩阵建图
        for (int[] g : graph) {
            Arrays.fill(g, 10001);
        }
        int u, v, w;
        for (int i = 0; i < m; i++) {
            u = in.nextInt();
            v = in.nextInt();
            w = in.nextInt();
            graph[u][v] = w;
            graph[v][u] = w;
        }
        //初始化距离
        Arrays.fill(dis, 10001);
        /* Prim三部曲
         * 第一步：选距离生成树最近节点
         * 第二步：最近节点（cur）加入到生成树
         * 第三步： 更新非生成树节点到生成树的距离（即更新dist数组）
         */

        for (int i = 1; i < n; i++) {
            int cur = -1;//当前距离生成树最近的节点，也是最后加入到生成树的节点
            int minDist = Integer.MAX_VALUE;//当前最近的距离
            //1. 选择距离生成树最近的节点
            for (int j = 1; j <= n; j++) {//顶点从 1 - n 进行编号，因此下标从 1 开始
                //选取最小生成树节点的条件
                //(1) 不在最小生成树里
                //(2) 距离最小生成树最近的节点
                if (!vis[j] && dis[j] < minDist) {
                    cur = j;
                    minDist = dis[j];
                }
            }

            //2. 将距离生成树最近的节点 cur 加入到生成树中
            vis[cur] = true;

            //3. 更新非生成树节点到生成树的距离（即更新dist数组）
            //cur 加入到生成树后，其他非生成树节点到生成树的距离都需要进行更新
            //由于 cur 节点是新加入到最小生成树的， 那么只需要关心与 cur 相连的非生成树节点的距离 是否比原来非生成树节点到生成树节点的距离更小
            for (int j = 1; j <= n; j++) {
                //更新条件：
                //（1）节点是 非生成树里的节点
                // (2) 与 cur 相连的某节点 node 的权值比 node 节点距离生成树的距离小
                if (!vis[j] && graph[cur][j] < dis[j]) {
                    dis[j] = graph[cur][j];
                }
            }

        }
        int ans = 0;
        //统计结果
        for (int i = 2; i <= n; i++) {
            ans += dis[i];
        }
        System.out.println(ans);
    }



    /**
     * 连接所有点的最小费用  prim解法
     **/

    private static int[] disMin;
    private static boolean[] vis1;
    public int minCostConnectPoints(int[][] points) {
        //
        int n = points.length;
        vis1 = new boolean[n];
        disMin = new int[n];
        Arrays.fill(disMin, 4000001);
        //初始化图
        int[][] graph = new int[n][n];
        for (int[] g : graph) {
            Arrays.fill(g, 4000001);
        }
        for (int i = 0; i < n - 1; i++) {
            int[] u = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] v = points[j];
                int disM = Math.abs(u[0] - v[0]) + Math.abs(u[1] - v[1]);
                graph[i][j] = disM;
                graph[j][i] = disM;
            }
        }

        for (int i = 0; i < n - 1; i++) {//只需要 n - 1 条边即可以构成一个最小生成树
            //1.找到与当前生成树距离最小的节点 cur
            int cur = -1;
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!vis1[j] && disMin[j] < minDist) {
                    cur = j;
                    minDist = disMin[j];
                }
            }

            //2. 将找到的节点 cur 加入到生成树中
            vis1[cur] = true;

            //3. 加入节点 cur 后，更新节点到新的生成树的最短距离
            //只更新与 cur 相关联的
            for (int j = 0; j < n; j++) {
                //更新条件：
                //（1）节点是 非生成树里的节点
                // (2) 与 cur 相连的某节点 node 的权值比 node 节点距离生成树的距离小
                if (!vis1[j] && graph[cur][j] < disMin[j]) {
                    disMin[j] = graph[cur][j];
                }
            }

        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans += disMin[i];
        }
        return ans;
    }
}

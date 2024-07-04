package graph.application;

import org.junit.Test;

import java.util.*;

/**
 * @description: TODO
 * @author wuyonghua
 */

public class Dijkstra {

    /*
     题目描述
    小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。
    小明的起点是第一个车站，终点是最后一个车站。然而，途中的各个车站之间的道路状况、交通拥堵程度以及可能的自然因素（如天气变化）等不同，这些因素都会影响每条路径的通行时间。
    小明希望能选择一条花费时间最少的路线，以确保他能够尽快到达目的地。

    输入描述
    第一行包含两个正整数，第一个正整数 N 表示一共有 N 个公共汽车站，第二个正整数 M 表示有 M 条公路。
    接下来为 M 行，每行包括三个整数，S、E 和 V，代表了从 S 车站可以单向直达 E 车站，并且需要花费 V 单位的时间。
     **/
    //朴素迪杰特斯拉
    private static int[] dis;//记录从源点 v0 到其他各节点当前的最短路径长度
    private static boolean[] vis;//记录哪些节点已经加入最短路径
    private static int[] path; // 记录每个节点的前驱节点，初始值为 -1
    public static void main(String args[]) {  //适合稠密图
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] graph = new int[n][n];

        for (int[] g : graph) {
            Arrays.fill(g, Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            int s = in.nextInt();
            int e = in.nextInt();
            int v = in.nextInt();
            graph[s - 1][e - 1] = v;
        }

        // 初始化
        path = new int[n];
        Arrays.fill(path, -1);
        dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        vis = new boolean[n];
        //暴力解法
        for (int i = 0; i < n; i++) {
            int minTime = Integer.MAX_VALUE;
            int u = 0;
            for (int j = 0; j < n; j++) {//遍历所有节点距离节点 u 的距离
                if (!vis[j] && dis[j] < minTime) { //该节点没有加入到最短路径中并且距离 u 最近
                    u = j;
                    minTime = dis[j];
                }
            }
            vis[u] = true; //加入当前节点到最短路径中
            for (int j = 0; j < n; j++) {
                if (graph[u][j] == Integer.MAX_VALUE) continue;//最大值不进行判断  Integer.MAX_VALUE + 一个正整数 = 溢出
                if (dis[j] > dis[u] + graph[u][j]) {
                    dis[j] = dis[u] + graph[u][j]; // 如果新加入的节点使得 dis[j] 的值越小则更新
                    path[j] = u;
                }

            }
        }

        System.out.println(dis[n - 1]);
        print(n - 1);
    }
    public static void print(int x) {
        if (path[x] == -1) return;
        print(path[x]);
        System.out.print(path[x] + "->" + x);
//        for (int i = 0; i < path.length; i++) {
//            if (path[i] == -1) continue;
//            else System.out.println(path[i] + "-->" + i);
//        }
    }
    // leetcode743 网络延迟时间  此做法适合稠密图
    private int[] dis1;
    private boolean[] vis1;
    public int networkDelayTime(int[][] times, int n, int k) {
        dis1 = new int[n];
        vis1 = new boolean[n];
        int[][] graph = new int[n][n];

        //初始化
        Arrays.fill(dis1, Integer.MAX_VALUE);
        dis1[k - 1] = 0;

        for (int[] g : graph) {
            Arrays.fill(g, Integer.MAX_VALUE);
        }
        for (int[] t : times) {
            int s = t[0];
            int e = t[1];
            int w = t[2];
            graph[s - 1][e - 1] = w;
        }
        simpleDijkstra(graph, n);
        int ans = 0;
        for (int e : dis1) {
            if (e == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, e);
        }
        return ans;
    }

    public void simpleDijkstra(int[][] graph, int n) {
        for (int i = 0; i < n; i++) {
            int minTime = Integer.MAX_VALUE;
            int u = 0;
            for (int j = 0; j < n; j++) {
                if (!vis1[j] && dis1[j] < minTime) {
                    u = j;
                    minTime = dis1[j];
                }

            }
            vis1[u] = true;//加入 u 作为最短路径中的一个节点

            for (int j = 0; j < n; j++) {//更新 dis
                if (graph[u][j] == Integer.MAX_VALUE) continue;
                if (dis1[j] > dis1[u] + graph[u][j]) dis1[j] = dis1[u] + graph[u][j];
            }
        }
    }

    //堆优化
    // Java 中的 PriorityQueue 是基于堆（heap）数据结构实现的，它通常使用的是二叉堆（binary heap），以数组形式实现。
    // 这种数据结构保证每次访问或移除元素时，能够以O(logn) 的时间复杂度找到最小元素（或最大元素，取决于比较器）。
    /*
     * 插入操作：offer/add
     * 出队操作： poll/remove
     *
     * @return: void
     **/
    @Test
    public void testPriorityQueue() {
        PriorityQueue<int[]> smallPQ = new PriorityQueue<>((a, b) -> (a[0] - b[0]));//小根堆
        PriorityQueue<int[]> bigPQ = new PriorityQueue<>((a, b) -> (b[0] - a[0]));//大根堆
//        pq.offer(new int[]{5, 0});
//        pq.offer(new int[]{6, 1});
//        pq.offer(new int[]{0, 3});
//        pq.offer(new int[]{1, 2});
//        pq.offer(new int[]{9, 4});
        bigPQ.add(new int[]{5, 0});
        bigPQ.add(new int[]{6, 1});
        bigPQ.add(new int[]{0, 3});
        bigPQ.add(new int[]{1, 2});
        bigPQ.add(new int[]{9, 4});
        while (!bigPQ.isEmpty()) {
//            int[] poll = pq.poll();
            int[] poll = bigPQ.remove();
            System.out.println(poll[0]);
        }
    }
    //堆优化的 Dijkstra
    //leetcode 网络延迟时间
    public int networkDelayTimeWithHeap(int[][] times, int n, int k) {
        int[] dis = new int[n];
        boolean[] vis = new boolean[n];

        //邻接表(适用于稀疏图）
        List<int[]>[] g = new ArrayList[n];

        // 初始化
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] t : times) {// 建图
            g[t[0]].add(new int[] {t[1] - 1, t[2]});
        }
        //初始化
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k - 1] = 0;

        // 维护小根堆获取距离最小的结点
        PriorityQueue<int[]> smallPQ = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        // 初始化
        smallPQ.offer(new int[] {0, k - 1}); // 第一个数为距离 第二个数为结点，k 源节点自己到自己距离为 0
        while (!smallPQ.isEmpty()) {
            int[] p = smallPQ.poll();// 出队，距离为最小的值
            int x = p[1];
            if (vis[x]) continue; // 入过堆
            vis[x] = true; // 未入过则标记入过堆

            for (int[] e : g[x]) { // 更新 dis
                if (dis[e[0]] > dis[x] + e[1]) dis[e[0]] = dis[x] + e[1];
                smallPQ.offer(new int[]{dis[e[0]], e[0]});
            }
        }
        // 有一个结点无法到达，则返回 -1
        int ans = 0;
        for (int e : dis) {
            if (e == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, e);
        }
        return ans;
    }
}
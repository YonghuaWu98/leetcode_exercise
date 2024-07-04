package graph.application;

/**
 *  TODO 最短路径 （Dijkstra， Floyd）
 **/
public class TheShortestPath {
    private static int n; // 假设图中有n个结点
    static int[][] matrix; // 采用邻接矩阵存储图的边权值 其中matrix[i][j]：代表i到j的最短路长度
    static int[][] path; // 记录i到j的最短路要经过的中间结点k，初始都为-1
    // todo Floyd算法 ：用来求任意两个结点之间的路径
    public static void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
//                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }
    /**
     * 递归打印最短路径
     */
    public void pathPrint(int u, int v) {
        if (path[u][v] == -1) System.out.println(u + "——>" + v);
        pathPrint(u, path[u][v]);
        pathPrint(path[u][v], v);
    }


    // todo Dijkstra算法


}

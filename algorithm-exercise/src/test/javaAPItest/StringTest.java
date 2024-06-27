package test.javaAPItest;

import org.junit.Test;

import java.util.*;

public class StringTest {
    //建造最大的岛屿
    private static int[][] direction = {{0, 1},{1, 0},{-1, 0},{0, -1}};

    private static HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[][] g = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = in.nextInt();
            }
        }
        int islandNum = 2;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && g[i][j] == 1) {
                    int a = dfs(g, visited, i, j, islandNum);
                    mp.put(islandNum, a);
                    islandNum += 1;
                }

            }
        }
        //遍历为 0 的陆地，将其变为 1 ，并计算它周围岛屿的面积
        int ans = 0;
        boolean check = false;//假设当前岛屿不全是陆地 即不全为 1
        Set<Integer> visited_island;//添加过的岛屿不能重复添加
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 1;
                if (g[i][j] == 0) {
                    check = true;
                    visited_island = new HashSet<Integer>();
                    for (int[] d : direction) {
                        int neari = i + d[0];
                        int nearj = j + d[1];
                        if (neari < 0 || nearj < 0 || neari >= g.length || nearj >= g[0].length) continue;
                        if (visited_island.contains(g[neari][nearj])) continue;
                        if (mp.containsKey(g[neari][nearj])) sum += mp.get(g[neari][nearj]);
                        visited_island.add(g[neari][nearj]);
                    }
                    ans = Math.max(ans, sum);
                }
            }
        }
        if (check) {
            System.out.println(ans);
        }else {
            System.out.println(n * m);
        }


    }
    public static int dfs(int[][] g, boolean[][] visited, int x, int y, int islandNum) {

        if (visited[x][y] || g[x][y] == 0) return 0;
        visited[x][y] = true;
        g[x][y] = islandNum;
        int area = 1;
        for (int[] d : direction) {
            int nextx = x + d[0];
            int nexty = y + d[1];
            if (nextx < 0 || nexty < 0 || nextx >= g.length || nexty >= g[0].length) continue;
            area += dfs(g, visited, nextx, nexty, islandNum);
        }
        return area;
    }
//    public static void main(String[] args) {
////        String[] a = new String[] {"c", "d", "a", "bb", "e"};
//
////        int[][] arr = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
////        List<List<Integer>> lists = pacificAtlantic(arr);
//
////        System.out.println("c".compareTo("b"));
////        StringBuilder str = new StringBuilder();
////        str.append("aaa");
////        str.append("bbb");
////        System.out.println(str);
////        ArrayList<Object> objects = new ArrayList<>();
////        Arrays.sort(a);
////        for (String ss : a) {
////            System.out.print(" " + ss);
////        }
//
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt(), m = in.nextInt();
//
//    }

    @Test
    public void test() {
        //测试结论：双重循环需要两个break才能全部停下
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            if (flag) break;
            for (int j = 0; j < 5; j++) {
                System.out.println(i * j);
                if (i * j == 2) {
                    flag = true;
                    break;
                }
            }
        }
    }
}




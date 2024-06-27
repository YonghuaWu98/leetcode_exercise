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


    //建造最大的岛屿（ACM模式）
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


    /**
     * 单词接龙
     * @param: beginWord
     * @param: endWord
     * @param: wordList
     * @return: int
     **/
    @Test
    public void testWord() {
        String[] str = {"hot","cog","dog","tot","hog","hop","pot","dot"};//["hot","cog","dog","tot","hog","hop","pot","dot"]
        List<String> strings = Arrays.asList(str);

        int i = ladderLength("hot", "dog", strings);
        System.out.println(i);
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();

        HashMap<String, List<String>> mp = new HashMap<String, List<String>>();
        mp.put(beginWord, new ArrayList<>());
        List<String> l;
        for (String w : wordList) {
            l = new ArrayList<>();
            mp.put(w, l);
        }
        //mp记录序列中每个单词 word 能够 1 步转换成为的单词
        for (int i = 0; i < n; i++) {
            if (canChangeByOneStep(beginWord, wordList.get(i))) {
                mp.get(beginWord).add(wordList.get(i));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                if (i == j) continue;
                if (canChangeByOneStep(wordList.get(i), wordList.get(j))) {
                    mp.get(wordList.get(i)).add(wordList.get(j));
                }
            }
        }
        //记录单词是否被访问
        HashSet<String> st = new HashSet<String>();
        Deque<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        st.add(beginWord);
        //todo: 广度优先搜索
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word == endWord) {
                    return ans + 1;
                }

                if (mp.containsKey(word)) {
                    for (String w : mp.get(word)) {
                        if (!st.contains(w)) {
                            st.add(w);
                            q.offer(w);
                        }
                    }
                }

            }
            ans++;
        }
        return 0;
    }
    /**
     * 比较两个单词只有一个字符不同
     * @return: boolean
     **/
    boolean canChangeByOneStep(String w1, String w2) {
        int l = w1.length();
        int l1 = 0;
        for (int i = 0; i < l; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                l1 += 1;
            }
        }
        return l1 == 1;
    }




}



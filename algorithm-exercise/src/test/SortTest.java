package test;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Description TODO 测试自定义排序

 **/
public class SortTest {

    public static void main(String[] args) {
//        Integer[] arr = {3, 5, 4, 6, 7, 1, 8, 9};
        int[][] arr = {{1, 2}, {2, 3}, {2, 1}, {3, 5}, {4, 4}};
        Arrays.sort(arr, (a, b) -> {
//            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];// 如果b - a大于零，就交换，降序排列
        });
//        System.out.println(Arrays.toString(arr));
        List<int []> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
           ans.add(arr[i]) ;
        }
        ans.sort((a, b) -> a[0] - b[0]);
//        System.out.println(ans.toString());
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(Arrays.toString(ans.get(i)));
        }



    }
    @Test
    public void testListSort() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(-3);
        list.add(2);
        list.add(4);
        Collections.sort(list);
        System.out.println(list);
    }
    @Test
    public void test() {
        int[][] edges = {{0,1}, {0,2},{0,3},{2,4},{4,5}};
        List<List<Integer>> lists = maximumScoreAfterOperations(edges);
//        StringBuilder builder = new StringBuilder();
        for (List<Integer> row : lists) {

                System.out.println(row + "");

        }

    }
    public List<List<Integer>> maximumScoreAfterOperations(int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        return list;

    }
    @Test
    public void testDfs() {
        int mask = dfs(0, 5, 3, 0);
        System.out.println(mask);
    }
    public int dfs(int indexStart, int m, int numSelect, int mask) {
        if (numSelect == 0) return mask;
        for (int i = indexStart; i < m; i++) {
            mask |= 1 << i;
            numSelect -= 1;
            dfs(i + 1, m, numSelect, mask);
            numSelect += 1;
            mask ^= 1 << i;

        }
        return 0;
    }
}

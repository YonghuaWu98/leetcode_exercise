package test.lanqiao;

import java.util.Scanner;
import java.util.*;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    static boolean flag;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int t = scan.nextInt();
        while (t --> 0) {
            int N = scan.nextInt();
            List<Integer>[] a = new ArrayList[N];
            Arrays.setAll(a, e -> new ArrayList<>());
            String x = scan.next();
            char[] c = x.toCharArray();
            for (int i = 0; i < N - 1; i++) {
                int u = scan.nextInt();
                int v = scan.nextInt();
                a[u - 1].add(v - 1);
            }
            flag = true;
            dfs(a, c, 0, 0,0);
            if(flag) {
                System.out.println("Yes");
            }else {
                System.out.println("NO");
            }

        }
        scan.close();
    }
    public static void dfs(List<Integer>[] a, char[] c, int numZ, int numO, int x) {

        if (c[x] == '1') numZ += 1;
        else numZ = 0;
        if (c[x] == '0') numO += 1;
        else numO = 0;
        if (numZ >= 3 || numO >= 3) flag = false;
        if (a[x].size() == 0) return;

        for (int y : a[x]) {
            dfs(a, c, numZ,numO, y);
        }

    }
}

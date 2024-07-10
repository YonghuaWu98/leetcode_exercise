package prefixsum_difference;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description TODO 差分相关题型 （ACM模式）
 **/
public class Difference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[200005];
        int n, k, q; // n 代表食谱的数量，k 代表一个温度 a 可以被接受时，当且仅当有大于等于 k 本食谱推荐用 a 来煮咖啡
        n = in.nextInt();
        k = in.nextInt();
        q = in.nextInt();
        for (int i = 0; i < n; i++) {
            int l ,r;
            l = in.nextInt();
            r = in.nextInt();
            arr[l] += 1;
            arr[r + 1] -= 1;
        }
        for (int j = 1; j < arr.length; j++) {
            arr[j] = arr[j - 1] + arr[j];
        }
        for (int i = 0; i < q; i++) {
            int a, b;
            a = in.nextInt();
            b = in.nextInt();
            int count = 0;
            for (int j = a; j <= b; j++) {
                if (arr[j] >= k) {
                    count++;
                }
            }
            System.out.println(count);

        }
    }
}

package test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 数学相关
 **/
public class MathTest {
    static final double a = 1000000000;
    static final int[] pal = new int[109999];
    // 打印一个整数的每一位
    public static void main(String[] args) {
//        int n = 100, k = 0;
//        while (n > 0) {
//            k = n % 10;
//            n -= k;
//            n /= 10;
////            System.out.print(k + " ");
//        }
        // 从小到大找出小于a的回文数
//        List<Integer> pal = new ArrayList<>();

//        int batch = 1;
//        int idx = 0;
//        while (batch <= 10000) {
//            //生成长度为奇数的回文数
//            for (int i = batch; i < batch * 10; i++) {
//                int x = i;
//                int t = x / 10;
//                while (t > 0) {
//                    x = x * 10 + t % 10;
//                    t /= 10;
//                }
//                pal[idx++] = x;
//            }
//            // 生成长度为偶数的回文数
//            if (batch <= 1000) {
//                for (int j = batch; j < batch * 10; j++) {
//                    int x = j, t = j;
//                    while (t > 0) {
//                        x = x * 10 + t % 10;
//                        t /= 10;
//                    }
//                    pal[idx++] = x;
//                }
//            }
//            batch *= 10;
//        }
//        System.out.println(pal);
//        int palIdx = 0;
//        for (int base = 1; base <= 10000; base *= 10) {
//            // 生成奇数长度回文数
//            for (int i = base; i < base * 10; i++) {
//                int x = i;
//                for (int t = i / 10; t > 0; t /= 10) {
//                    x = x * 10 + t % 10;
//                }
//                pal[palIdx++] = x;
//            }
//            // 生成偶数长度回文数
//            if (base <= 1000) {
//                for (int i = base; i < base * 10; i++) {
//                    int x = i;
//                    for (int t = i; t > 0; t /= 10) {
//                        x = x * 10 + t % 10;
//                    }
//                    pal[palIdx++] = x;
//                }
//            }
//
//        }
//        System.out.println(pal[109998]);
//
//        int[] nums = {6, 5, 7, 8};
//        incremovableSubarrayCount(nums);
        findMaximumNumber(9, 1);
    }
    public static long findMaximumNumber(long k, int x) {
        long v = 0, nums = 1;
        // long a = 1L;
        while (v <= k) {
            long b = nums;
            for (int i = 0; b != 0; i++) {
                b = b >> i;
                if ((1 & b) != 0 && (i + 1) % x == 0)  {
                    v += 1;
                }
            }
            nums++;
        }
        return nums;
    }
    public static int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int[] temp;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            temp = nums;
            for (int j = i; j >= 0; j--) {
                temp[j] = 0;
                int a = -1; boolean flag = true;
                for (int k = 0; k < n; k++) {
                    if (temp[k] == 0) continue;
                    if (temp[k] > a) a = temp[k];
                    else flag = false;
                }
                if (flag) cnt++;
            }
        }
        return cnt;
    }


}

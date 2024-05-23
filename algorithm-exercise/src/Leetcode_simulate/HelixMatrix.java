package Leetcode_simulate;

import org.junit.Test;

/**
 * @ClassName:HelixMatrix
 * @Description TODO leetcode 第59题 螺旋矩阵
 * @Author 86156
 * @Date 2023/4/7 22:25
 * @Version 1.0
 **/


public class HelixMatrix {

    public static void main(String[] args) {
        int[][] b = generateMatrix(5);
        for (int i = 0;i < 5;i++) {
            for (int j = 0;j < 5;j++) {
                System.out.print(b[i][j]);
            }


        }

    }

    public static int[][] generateMatrix(int n) {
        int t = 0;
        int b = n - 1;
        int l = 0;
        int r = n - 1;
        int [][] a = new int[n][n];
        int k = 1;
        while (k <= n*n) {
            for (int i = l;i <= r;i++) {a[t][i] = k;k++;}
            t++;
            for (int i = t;i <= b;i++) {a[i][r] = k;k++;}
            r--;
            for (int i = r;i >= l;i--) {a[b][i] = k;k++;}
            b--;
            for (int i = b;i >= t;i--) {a[i][l] = k;k++;}
            l++;

        }
        return a;
    }


}






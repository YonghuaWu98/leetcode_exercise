package math;
/**
 * 数学模块
 *
 **/
public class MathAlgo {

    // 辗转相除法（求两个数的最大因数）
    public static int gcd(int n, int m) { //假设 n > m

        int r = 1;
        while (r != 0) {
            r = n % m;
            n = m;
            m = r;
        }
        return n;
    }
    //递归写法
    public static int gcd1(int n, int m) {
        return n % m == 0 ? n : gcd(m, n % m);
    }

    // 模 10 除以 10 从低位到高遍历其各个数位
    public static void printNum(int n) {
        int s;
        for (int x = n; x > 0; x /= 10) {
            s = x % 10;
            System.out.print(s + " ");
        }
    }



    //判断一个数是否为质数
    //判断 n 能否被根号 n 以内的某个大于 1 的数整除，如果不能则说明 n 是质数， 1 不是质数
    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n >= 2;
    }
    // 快速幂
    // 实现 Math.pow(double x, int n)
    // 递归实现
    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickPow(x, N) : 1.0 / quickPow(x, N);
    }

    static double quickPow(double x, long n) {
        if (n == 0) return 1.0;

        double y = quickPow(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }


    public static void main(String args[]) {
        int ans = gcd1(198, 48);
        System.out.println(ans);//6
        boolean isP = isPrime(107);//true
        System.out.println(isP);
        printNum(1201231489);//9 8 4 1 3 2 1 0 2 1
        System.out.println();
        double pow = myPow(2, -2);
        System.out.println(pow);
    }
}

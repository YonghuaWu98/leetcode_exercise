package test;

import org.junit.Test;

import java.util.Date;

/**
 * @Description TODO 测试String类的常用方法
 **/
public class StringTest {
    int a;
    int b;

    public StringTest(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        /**
         *  substring()方法：实现截取字符串，利用字符串的下标索引来截取（字符串的下标从0开始，在字符串中空格占用一个索引位置）
         *  substring(int startIndex):截取从指定索引位置开始到字符串结尾的子串。
         *  substring(int startIndex, int endIndex):从startIndex开始，到endIndex - 1结束。
         */
//        String s = "abcdefg";
//        String subS1 = s.substring(2, 3);
//        String subS2 = s.substring(2);
//        System.out.println(subS1);
//        System.out.println(subS2);
//        String s3 = s;
//        System.out.println(s == s3);
//        s3 += "ssss";
//        System.out.println(s3);
//        System.out.println(s == s3);
//        String s = "javaEE";
//        String s1 = "javaEEandjavaSE";
//        String s2 = "javaEE" + "andjavaSE";
//        System.out.println(s1 == s2);
//        System.out.println(s1.equals(s2));
//        String s3 = s + "andjavaSE";
//        System.out.println(s3 == s1);
//        System.out.println(s3 == s2);
//        System.out.println(s3.equals(s1));//equals()方法比较两个字符串内容是否一致；== 如果是两个引用数据类型比较时，
//                                        // 比较的是变量地址值是否相等；如果基本数据类型比较时，比较的是两个变量的内容是否一致
//        String s4 = "123";
//        int num = Integer.parseInt(s4);
//        System.out.println(num);
        // 使用Date空参构造器创建对象
//        Date date = new Date();
//        System.out.println(date.getTime());//距离
//        System.out.println(date.toString());


        // 使用Date有参构造器创建对象
//        Date date1 = new Date(1701068278729L);
//        System.out.println(date1.toString());
//
//
//        String s4 = new String("helloworld");
//        String s5 = new String("helloworld");
//        System.out.println(s4 == s5);
//        String s6 = "helloworld";
//        System.out.println(s6 == s5);
//
//        System.out.println(s6 == s4);
//        s6 = s5;
//        System.out.println(s6 == s5);

//        String s = "null";
//        System.out.println(s.isEmpty());
//        longestPalindrome("cbbd");


    }
    public static String longestPalindrome(String s) {
        int n = s.length(), maxLen = 0; String ans = "";
        char[] c = s.toCharArray();
        boolean[][] memo = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    memo[i][j] = true;
                }else {
                    if (c[i] == c[j]) {
                        if (j - i <= 1) memo[i][j] = true;
                        if (j - i > 1 && memo[i + 1][j - 1]) {
                            memo[i][j] = true;
                        }
                    }
                }
            }
        }
        return ans;
    }


}

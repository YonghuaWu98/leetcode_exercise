package dynamic_programming;


import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DynamicProgramming {
    @Test
    public void test() {
        int i = DynamicProgramming.integerBreak(15);
        System.out.println(i);
    }



    /**
     * @description: LeetCode第343 整数拆分
     * @param: n
     * @return: int
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1]; //dp[i]代表数字i被拆分后的最大乘积
//        dp[1] = 1; //dp数组初始化
        dp[2] = 1;
        for (int i = 3; i <= n; i++) { //从第三个开始遍历
            for (int j = 1; j < i; j++) {
//                if (i < 10) { //如果n小于10，会出现(i - j)*j 更大的情况
//                    dp[i] = Math.max(dp[i], Math.max(dp[i - j]*dp[j],   (i - j)*j)); //比较dp中数字和为i的乘积和 (i - j)*j的大小
//                                                                                    // 再和dp[i]作比较，dp[i]取最大的
//                    dp[i] = Math.max(dp[i], dp[i - j]*j); //再和dp[i - j]*j作比较，同样取最大
//                }
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j)); //i大于10，则不需要考虑（i - j）*j
            }
        }
        return dp[n];
    }

    /**
     * @description: leetcode 96 不同的二叉搜索树
     * @param: n
     * @return: int
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
//                if (j == 1 || j == i)  dp[i] += dp[i - 1];
//                else dp[i] += dp[j - 1] * dp[i - j];
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * @description: TODO 01背包问题
     * @author 86156
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int m = in.nextInt();
        int N = in.nextInt();
        int[] weight = new int[m];
        int[] value = new int[m];
        in.nextLine();
        for (int i = 0; i < m; i++) {
            weight[i] = in.nextInt();

        }
        for (int i = 0; i < m; i++) {
            value[i] = in.nextInt();
        }
//        int res = DynamicProgramming.twoDimensions(weight, value, N);
        int res = DynamicProgramming.OneDimensions(weight, value, N);
        System.out.println(res);
    }

    // 二维数组实现形式
    public static int twoDimensions(int[] weight, int[] value, int N) {
        int[][] dp = new int[weight.length][N + 1];
        for (int j = N; j >= weight[0]; j--) {
            dp[0][j] = value[0];
        }
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= N; j++) {
                if (j < weight[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }
//        for (int l = 0; l < weight.length; l++) {
//            for (int k = 0; k <= N; k++) {
//                System.out.println(dp[l][k]);
//            }
//        }
        return dp[weight.length - 1][N];
    }

    //一维数组实现形式（滚动数组）
    public static int OneDimensions(int[] weight, int[] value, int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = N; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        return dp[N];
    }
    /**
     * @description: TODO leetcode 416 分割等和子集
     */
    public static boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        // 数组所有元素求和
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false; //如果和不是偶数，即不存在两个子数组元素和相等
        else sum /= 2;
        int[] dp = new int[sum + 1];
        // 开始 01背包问题
        for (int i = 0; i < len; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]); // 每一个元素一定是不可重复放入，所以从大到小遍历
            }
        }
        if (dp[sum] == sum) return true;

        return false;
    }
    /**
     * @description:  todo leetcode 1049 最后一块石头的重量||
     * @param: stones
     * @return: int
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int e : stones) {
            sum += e;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j-- ) {
                dp[j] = Math.max(dp[j], dp[j- stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] * 2;
    }
    // todo 完全背包问题
    // todo leetcode 第518 零钱兑换II
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }

    // todo leetcode 377 组合总和|| 回溯解法 （超时）
    int count = 0;
    public  int combinationSum3(int[] nums, int target) {

        backtrack(nums, 0, target);
        return count;
    }

    public void backtrack(int[] nums, int sum, int target) {
        if (sum == target) {
            count++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum > target) return;
            sum += nums[i];
            backtrack(nums, sum, target);
            sum -= nums[i];
        }
        return;
    }

    //todo 动态规划解法
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++ ) { //先遍历背包，在遍历物品
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    //todo 单词拆分
    // 动态规划
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int j = 1; j <= len; j++) {
            for (int i = 0; i < j; i++) {
                String subS = s.substring(i, j);
                if (set.contains(subS) && dp[i]) {
                    dp[j] = true;
                }

            }
        }
        return dp[len];
    }
    // todo 动态规划 + 字典树
    class Trie {
        public Trie[] children;
        public boolean isEnd;
        public Trie() {
            children = new Trie[26];
        }
        // 插入
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) node.children[index] = new Trie(); // 插入一个新的节点
                node = node.children[index];
            }
            node.isEnd = true; // 代表一个单词的结束
        }
        // 查找
        public boolean search(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) return false;
                node = node.children[index];
            }

            return node.isEnd;
        }

    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        Trie node = new Trie();
        for (String ss : wordDict) {
            node.insert(ss);
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int j = 1; j <= len; j++) {
            for (int i = 0; i < j; i++) {
                String subS = s.substring(i, j);
                if (node.search(subS) && dp[i]) {
                    dp[j] = true;
                }

            }
        }
        return dp[len];
    }

    // todo leetcode 198 打家劫舍

    //暴力递归（超时）
     public static int robb1(int[] nums) {
         int n = nums.length;
         return dfs(n - 1, nums);
     }
     public static int dfs(int i, int[] nums) {
         if (i < 0) return 0;
         int ans = Math.max(dfs(i- 1, nums), dfs(i - 2, nums) + nums[i]);
         return ans;
     }
    // 自顶向下：记忆化搜索+递归

    // 复杂度计算公式：用状态个数乘上单个状态所需的计算时间 状态个数O(N) 单个状态所需计算时间O(1)
     public static int robb2(int[] nums) {
         int n = nums.length;
         int[] memo = new int[n];
         Arrays.fill(memo, -1);
         return dfs(n - 1, nums, memo);
     }
     public static int dfs(int i, int[] nums, int[] memo) {
         if (i < 0) return 0;
         if (memo[i] != -1) return memo[i];
         int ans = Math.max(dfs(i- 1, nums, memo), dfs(i - 2, nums, memo) + nums[i]);
         memo[i] = ans;
         return ans;
     }

    // 自底向上 = 递推
    // dfs -> dp数组
    // 递归 -> 循环
    // 递归边界 -> 数组初始值
    // dfs(i) = max(dfs(i - 1), dfs(i - 2) + nums[i]) i从2开始
    // dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
    public static int robb3(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int j = 2; j <= len; j++) {
            dp[j] = Math.max(dp[j - 2] + nums[j - 1], dp[j - 1]);
        }
        return dp[len];
    }
    // 空间优化 当前 = max(上一个， 上上一个 + nums[i])  f0表示上上一个,f1表示上一个
    // newf = max(f1, f0 + nums[i])
    // f0 = f1 f1 = newf
    public static int robb4(int[] nums) {
        int len = nums.length;
        int f0 = 0, f1 = 0, ans = 0;
        for (int j = 0; j < len; j++) {
            ans = Math.max(f0 + nums[j], f1);
            f0 = f1;
            f1 = ans;
        }
        return ans;
    }
    // todo leetcode 213 打家劫舍II

    public static int rob2(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        // 第1家不偷 偷[2, n]家
        dp[0] = 0;
        dp[1] = nums[0];
        for (int j = 2; j < len; j++) {
            dp[j] = Math.max(dp[j - 2] + nums[j - 1], dp[j - 1]);
        }
        int maxNum = dp[len - 1];
        // 最后一家不偷，偷[1, n -1]家
        Arrays.fill(dp, 0);
        dp[1] = nums[1];
        for (int j = 2; j < len; j++) {
            dp[j] = Math.max(dp[j - 2] + nums[j], dp[j - 1]);
        }
        // 最后取两者最大值
        return Math.max(maxNum, dp[len - 1]);
    }
    // 官方：
    public static int rob22(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public static int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    // todo 打家劫舍III


    //todo 最大子数组和
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = -10000;
        int res = -10000;
        for (int i = 1; i <=n; i++) {
            dp[i] = Math.max(nums[i - 1], dp[i - 1] + nums[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    // todo 分治法


    //编辑距离
    static int[][] cache; //保存计算的结果给
    public int minDistance(String word1, String word2) {

        //确定dp[i][j]的意义, word1前 i 个字符包含 word2前 j 个字符的最少操纵数

        int n = word1.length(), m = word2.length();

        //操作与不操作的问题
        //s[i] == t[j], 不操作, 问题变成 i - 1, j - 1 的规模 dp[i][j] = dp[i - 1][j - 1]
        //s[i] != t[j]，操作有三种情况 要么删除要么插入要么替换
        //删除 s[i], 则问题变成 word1[i - 1] 和 word2[j] 的子问题
        //插入 t[j], 这时候s[i] == t[j], 问题关键则为 s[0-i](因为s插入了一个)包含t[0,j - 1]的最小操作次数
        //替换 s[i], 此时s[i] == t[j], 问题关键则回到 s[0 - (i - 1)] 和 t[0 - (j - 1)]了
        //综上， dp[i][j] = dp[i - 1][j - 1]  s[i] = t[j]
        //dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1

        //暴力递归 + 保存计算结果 = 记忆化搜索
        cache = new int[n][m];
        //初始化，-1 代表未搜索过
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }
        return dfs(word1, word2, n - 1, m - 1);
    }
    int dfs(String s, String t, int x, int y) {
        if (x < 0) return y + 1;//为空则返回 y + 1（加一是因为j为下标）, 其中一个字符串多出来的字符
        if (y < 0) return x + 1;
        if (cache[x][y] != -1) return cache[x][y];
        if (s.charAt(x) == t.charAt(y)) return cache[x][y] = dfs(s, t, x - 1, y - 1);
        else return cache[x][y] = Math.min(Math.min(dfs(s, t, x - 1, y), dfs(s, t, x, y - 1)), dfs(s, t, x - 1, y - 1)) + 1;
    }

    //编辑距离
    //递推


}

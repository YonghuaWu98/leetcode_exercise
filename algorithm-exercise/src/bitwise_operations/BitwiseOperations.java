package bitwise_operations;

import org.junit.Test;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 位运算
 **/
public class BitwiseOperations {

    /**
     * TODO leetcode 78子集
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }
    /*
     * todo leetcode 2939 最大异或乘积
     */
    public static int maximumXorProduct(long a, long b, int n) {


        return 0;
    }

    /**
     * todo leetcode 421 数组中两个数的最大异或值
     *
     */
    public static int findMaximumXOR(int[] nums) {
        return 0;
    }
    @Test
    public void test() {
        // 集合与集合
        int[] set1 = {0, 1, 2, 5, 4, 7}; //集合中的元素互斥
        int[] set2 = {1, 2, 3, 4, 6, 8};

        int s1 = compressSet(set1);
        int s2 = compressSet(set2);
        int s3 = s1 & s2; //求两个集合的交集
        int s4 = s1 | s2; //求两个集合的并集
        int s5 = s1 ^ s2; //求两个集合的对称差，即两个集合的并集除两个集合的交集外
        int s6 = s1 & ~s2;//求集合1和集合2的差
        System.out.println("s1: " + Integer.toString(s1, 2) + " s2: " + Integer.toString(s2, 2) + " s3: " + Integer.toString(s3, 2));
        System.out.println("s1: " + Integer.toString(s1, 2) + " s2: " + Integer.toString(s2, 2) + " s4: " + Integer.toString(s4, 2));
        System.out.println("s1: " + Integer.toString(s1, 2) + " s2: " + Integer.toString(s2, 2) + " s5: " + Integer.toString(s5, 2));
        System.out.println("s1: " + Integer.toString(s1, 2) + " s2: " + Integer.toString(s2, 2) + " s6: " + Integer.toString(s6, 2));


        // 集合与元素
        // 单元素集合
        // int[] set3 = {i}; // 1 << i
        // 全集 {0, 1, 2, 3, ....., n -1}   (1 << n) - 1
        int[] set4 = {0, 1, 2, 3, 4, 5, 6};
        int a = (1 << 31) - 1;
        System.out.println("全集: " + Integer.toString(a, 2)); // 全集: 111111
        // 补集 ~s 或者 ((1 << n) - 1) ^ s
        int[] s = {1, 2, 3};
        int ans = compressSet(s);
//        int ans2 = ~(ans & a);
        int ans3 = a ^ ans;
        System.out.println(Integer.toString(a ^ ans, 2));
        System.out.println(Integer.toString( ~(ans & a), 2)); // 取反后，打印出来的是它补码形式

        int len = 32 - Integer.numberOfLeadingZeros(2147483647);
        System.out.println(len);
    }
    // 将一个集合中出现的所有元素使用一串二进制表示
    public int compressSet(int[] set) {
        int ans = 0;
        for (int e : set) {
            ans ^= 1 << e; // 集合可以用二进制表示，二进制从低到高第i位为1表示i在集合中，为0表示i不在集合中。
        }
        return ans;
    }

    // leetcode 翻转数位
    /**
     * cur：统计遇到0之前1的个数
     * insert：统计遇到1的个数，当遇到0时，更新为cur + 1
     * res：返回值，初始为1
     *
     */
    public static int reverseBits(int num) {
        int cur = 0;
        int insert = 0;
        int res = 1;
        for(int i = 0; i < 32; i++){
            if((num & (1 << i)) != 0){
                cur += 1;
                insert += 1;
            }else{
                insert = cur + 1;
                cur = 0;
            }
            res = Math.max(res , insert);
        }
        return res;
    }
    // todo leetcode 77 组合 位运算解法
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) { // 1 << n表示全集{0，1，2,....n-1}的子集有1 << n种
            t.clear();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) { //扫描i的每一位，有1代表子集包含元素j + 1
                    t.add(j + 1);
                }
            }
            if (t.size() == k) ans.add(new ArrayList<>(t));
        }
        return ans;
    }
    @Test
    public void testPermute() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> ans = permute(nums);
        System.out.println(ans);
    }

    // todo 排列 leetcode 46
    // 采用位运算记录元素选用情况
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int b = 0;
        dfs(nums, b);
        return ans;
    }
    public void dfs(int[] nums, int b) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if ((b & (1 << i)) != 0) continue; //
            path.add(nums[i]);
            b ^= (1 << i);
            dfs(nums, b);
            path.remove(path.size() - 1);
            b ^= (1 << i);
        }
    }
    @Test
    public void testWon() {
        String s = "aba";
        long l = wonderfulSubstrings(s);
        System.out.println(l);
    }
    // todo leetcode 1915最美子字符串的数目
    // 采用二进制记录字母出现的奇数或偶数次情况，1代表奇数次 0代表偶数次
    public static long wonderfulSubstrings(String word) {
        int ans = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = i; j >= 0; j--) {
                int count = 0;
                t ^= (1 << (word.charAt(j) - 'a')); //
                for (int k = 0; k < 10; k++) {
                    if ((t & (1 << k)) != 0) {
                        count++;
                    }
                }
                if (count <= 1) ans++;
            }

        }
        return ans;
    }
    /*
     * todo 周赛 400 找到按位或最接近 k 的子数组
    按位 &, | 可以做如下思考:
    从集合的角度思考：
        如果 a & b = a, 那说明 a 是 b 的子集, 两个集合进行 & 运算，与出来的集合将越来越小
        如果 a | b = a, 则说明 b 是 a 的子集， 两个集合进行 | 运算，或出来的集合将越来越大
    在本题中，如果 nums[j] | x == nums[j]， 那么 j 及其左侧所有数（集合）都不会被更新
    一旦发现不变，则不用进行后续的或操作，内循环停止

    **/
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 单个元素也是一个子数组
            ans = Math.min(Math.abs(nums[i] - k), ans);

            for (int j = i - 1; j >= 0; j--) { // 从右往左遍历，将 nums[j] | nums[i] 保存在 nums[j] 中
                if ((nums[j] | nums[i]) == nums[j]) { // 如果 nums[j] | nums[i] == nums[j] 则停止遍历
                    break;
                }
                nums[j] |= nums[i]; // 这一定会导致 nums[j] 变大
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }
    @Test
    public void testCanSortArray() {
        int[] test1 = {8, 4 , 2, 30, 15};
        canSortArray(test1);
    }
    /*
     * leetcode 3011 判断一个数组是否可以变为有序
     * @return: boolean
     **/
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] <= nums[j + 1]) continue;
                // 计算当前元素的二进制 1 的个数

                int curOne = 0;
                int cur = nums[j];
                for (;cur > 0; cur >>= 1) {
                    if ((cur & 1) != 0) {
                        curOne++;
                    }
                }
                // 计算后一个元素的二进制 1 的个数
                int afterOne = 0;
                int after = nums[j + 1];
                for (; after > 0; after >>= 1) {
                    if ((after & 1) != 0) {
                        afterOne++;
                    }
                }
                if (curOne != afterOne) return false;
                int t = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = t;

            }
        }
        return true;
    }
}

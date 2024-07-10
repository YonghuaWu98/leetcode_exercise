package prefixsum_difference;
/**
 * @description: TODO 前缀和、哈希表、位运算相关题型
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PrefixSum {
    //洛谷 Subsequences Summing to Sevens S
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        int[] nums = new int[N];
//        for (int i = 0; i < N; i++) {
//            nums[i] = in.nextInt();
//            in.nextLine();
//        }
//        int maxLen = PrefixSum.subsequencesSummingToSevensSum(nums);
//        System.out.println(maxLen);
        String s = "a";
        String ss = "c";
        System.out.println(s.compareTo(ss));

    }

    public static int subsequencesSummingToSevensSum(int[] nums) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        int[] last = new int[7];
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = (prefixSum[i] + nums[i]) % 7;
        }
        for (int i = 0; i < 7; i++) {
            last[i] = -1;
        }

        for (int i = 0; i <= len; i++) {
            if (last[prefixSum[i]] == -1) {
                last[prefixSum[i]] = i;
            } else {
                maxLen = maxLen > i - last[prefixSum[i]] ? maxLen : i - last[prefixSum[i]];
            }
        }
        return maxLen;
    }

    //todo leetcode 560 和为k的子数组
    /**
     * @description: 子数组和应该想到前缀和或者滑动窗口
     * 前缀和定义：p[i] = nums[0]+nums[1]+...+nums[n - 1]，那么每个连续子数组的和sum（i，j）就可以写成P[j] - p[i - 1](其中0 < i < j)的形式。
     * 也就是p[j] - p[i - 1] == k 则p[j] - k == p[i - 1]
     * 在遍历数组的过程中，借助哈希表来记录p[j] - k出现的次数count，遍历过程中，如果满足p[j] - k在哈希表中存在，则让ans += count
     */
    public static int subarraySum(int[] nums, int k) {
        int ans = 0, sum = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int elem : nums) {
            sum += elem;
            ans += cnt.getOrDefault(sum - k, 0);
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    //todo leetcode 974 和可被k整除的子数组
    /**
     * @description: 子数组和 一般采用前缀和来解决  前缀和定义：p[i] = nums[0]+nums[1]+...+nums[n - 1]，那么每个连续子数组
     * 的和sum（i， j）就可以写成P[j] - p[i - 1](其中0 < i < j)的形式。此时判断子数组的和是否能被k整除就等价于判断
     *（P[j] - p[i - 1]）% k == 0，根据同余定理，只要p[j] % k == p[i- 1] % k,就可以保证上面的等式成立。
     * 因此我们可以考虑对数组进行遍历，在遍历同时统计答案。如果遍历到j的位置时，统计j前面有多少个子数组p[i - 1] % k == p[j] % k
     * 使用哈希表记录对应的子数组个数，此时，前缀和值对k取余作为键（前缀和取余k时会为负数，需要对其进行修正：((sum(i,j) % k + k) % k）
     * 在遍历的过程中更新它出现的次数
     */
    public static int subarraysDivByK(int[] nums, int k) {
        int ans = 0, sum = 0;
        HashMap<Integer,Integer> counter = new HashMap<>();
        counter.put(0, 1);
        for (int e : nums) {
            sum += e;
            int m = (sum % k + k) % k;
            ans += counter.getOrDefault(m, 0) ;
            counter.put(m, counter.getOrDefault(m, 0) + 1);
        }

        return ans;
    }

    //todo leetcode 统计美丽子字符串
    // 暴力
    public int beautifulSubstrings(String s, int k) {
        if (s.length() == 1) return 0;
        int subS = 0;
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            int y = 0, f = 0;
            for (int j = i; j < a.length; j++) {
                if (isY(a[j])) y++;else f++;
                if ((j - i + 1) % 2 != 0) continue;
                if (y == f && (y * f) % k == 0) {
                    subS++;
                }
            }
        }
        return subS;

    }
    public boolean isY(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return true;
        return false;
    }
    // 分解质因子、前缀和、哈希表
    private static final int AEIOU_MASK = 1065233;

    public int beautifulSubstrings2(String s, int k) {
        k = pSqrt(k * 4);
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = s.length();
        int sum = n; // 保证非负
        cnt.put((k - 1) << 16 | sum, 1); // 添加 (k-1, sum)
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int bit = (AEIOU_MASK >> (s.charAt(i) - 'a')) & 1;
            sum += bit * 2 - 1; // 1 -> 1    0 -> -1
            ans += cnt.merge((i % k) << 16 | sum, 1, Integer::sum) - 1; // ans += cnt[(i%k,sum)]++
        }
        return ans;
    }

    private int pSqrt(int n) {
        int res = 1;
        for (int i = 2; i * i <= n; i++) {
            int i2 = i * i;
            while (n % i2 == 0) {
                res *= i;
                n /= i2;
            }
            if (n % i == 0) {
                res *= i;
                n /= i;
            }
        }
        if (n > 1) {
            res *= n;
        }
        return res;
    }


    //todo leetcode 面试题17.05. 字母和数字
    /**
     * @description: 前缀和 + 哈希表
     */
    public static String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = array.length;
        int[] prefixSum = new int[n + 1];
        int maxLen = 0, idx = 0;
        for (int i = 0; i < n; i++) {
            char ch = array[i].charAt(0);
            if ((ch <= 'z' && ch >= 'a') || (ch >= 'A' && ch <= 'Z')) {
                prefixSum[i + 1] = 1;
            }else {
                prefixSum[i + 1] = -1;
            }
            prefixSum[i + 1] += prefixSum[i];
        }
        for (int i = 0; i <= n; i++) {
            if (!mp.containsKey(prefixSum[i])) {
                mp.put(prefixSum[i], i);
            }else {
                int l = i  - mp.get(prefixSum[i]);
                if (maxLen == l) {
                    idx = Math.min(idx, mp.get(prefixSum[i]));
                }else if (maxLen < l){
                    maxLen = l;
                    idx = mp.get(prefixSum[i]);
                }
            }
        }
        String[] ans = new String[maxLen];
        for (int i = 0; i < maxLen; i++) {
            ans[i] = array[idx++];
        }
        return ans;
    }


    // todo leetcode 1915最美子字符串的数目  标签 ---> 前缀和 哈希表 位运算 字符串
    /**
     * @description:  暴力解法超时
     * 思路： 枚举每一个子字符串，采用位运算对字母出现的次数进行压缩，二进制第i位比特位（从低位到高位），1代表字母出现奇数次，0代表出现偶数次
     */
    public static long wonderfulSubstrings2(String word) {
        int ans = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = i; j >= 0; j--) {
                int count = 0;
                t ^= (1 << (word.charAt(j) - 'a')); //异或压缩
                for (int k = 0; k < 10; k++) {
                    if ((t & (1 << k)) != 0) { //遍历二进制的每一位，统计比特位为1的个数，超过1则不符合题意
                        count++;
                    }
                }
                if (count <= 1) ans++;
            }

        }
        return ans;
    }
    /**
     * @description: 前缀和 + 位运算
     * 结论：如果字符串 word[i, j]是最美字符串，那么其中最多只有一个字符出现奇数次，这说明：
     *  对于任意一次字符c而言，word[0, i]前缀与前缀 word[0..j]中字符c的出现次数必须同奇偶。同时，我们最多允许有一个字符 c，
     *  它在两个前缀中出现次数的奇偶性不同。
     *  思路与算法：
     *      题目中对字母出现的范围为（a，b...j），所以可以用一个10位的二进制记录在遍历字符串时不同字母出现的次数，采用异或运算，
     *      1代表奇数次 0代表偶数次。具体实现如下：
     *           int re ^= 1 << (word.charAt(i) - 'a'); 从低位到高位，第0位为字母a出现的次数，其他依此类推
     *      根据结论可知，如果存在一个不同下标的子串的二进制各个位与当前下标子串都相同，此时两者的二进制表示（前缀和）进行异或的结果为0
     *      意味着当前下标对应的子串的各个字母的个数均为偶数，用一个长为 2^10=1024数组cnt统计每个前缀和二进制串出现的次数，
     *      从而得到相同前缀和的对数，即各个字母的个数均为偶数的子串个数。
     *      题目还允许有一个字母出现奇数次，这需要我们寻找两个前缀和，其异或结果的二进制数中恰好有一个 1，
     *      意味着这段子串的各个字母的个数仅有一个为奇数。对此我们可以枚举当前前缀和的每个比特，
     *      将其反转，然后去 cnt 中查找该前缀和二进制串的出现次数。
     *
     *
     */
    public static long wonderfulSubstrings(String word) {
        long ans = 0;
        int n = word.length();
        int[] cnt = new int[1024];
        cnt[0] = 1; //初始前缀和为 0，需将其计入出现次数
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum ^= 1 << (word.charAt(i) - 'a'); // 计算当前前缀和
            ans += cnt[sum]; // 所有字母均出现偶数次
            for (int j = 1; j < 1024; j <<= 1) { // 枚举其中一个字母出现奇数次
                ans += cnt[sum ^ j]; // 反转该字母的出现次数的奇偶性
            }
            cnt[sum]++;// 更新前缀和出现次数
        }
        return ans;
    }
    //todo leetcode 523 连续子数组和
    /**
     * @description: 计算前缀和，然后对k进行取余，当p[j]和p[i]的前缀和对k取余后值相同时，则满足(i, j]区间元素和为k的倍数，
     *
     * 借助哈希表记录取余后的值，第一次出现则加入到哈希表中，取余后的值作为键，下标作为值，如果第二次出现，则满足条件2，再判断是否
     * 满足条件1，判断子数组是否长度大于等于2.
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        boolean ans = false;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int m = sum % k;
            if (cnt.containsKey(m)) {
                if (i - cnt.get(m) >= 2) {
                    ans = true;
                    break;
                }
            }else {
                cnt.put(m, i);
            }
        }
        return ans;
    }

    //todo leetcode 525 连续数组
    public static int findMaxLength(int[] nums) {
        int maxLen = 0, sum = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum += -1;
            }else {
                sum += 1;
            }
            if (cnt.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - cnt.get(sum));
            }else {
                cnt.put(sum, i);
            }
        }
        return maxLen;
    }

    // todo leetcode 1371 每个元音包含偶数次的最长子字符串

    /**
     * @description: 思路和  1915最美子字符串的数目 类似
     *
     */
    public static int findTheLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int k = 0, maxLen = 0;
        cnt.put(0, -1);
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'a' || ch[i] == 'e' || ch[i] == 'i' || ch[i] == 'o' || ch[i] == 'u') {
                k ^= 1 << (ch[i] - 'a');
            }
            if (cnt.containsKey(k)) {
                maxLen = Math.max(maxLen, i - cnt.get(k));
            }else {
                cnt.put(k, i);
            }
        }
        return maxLen;
    }

}


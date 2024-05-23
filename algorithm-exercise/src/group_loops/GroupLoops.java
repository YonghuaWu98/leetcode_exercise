package group_loops;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 分组循环 板子
 **/
public class GroupLoops {

    /**
     * todo leetcode 2760 最长奇偶子数组
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。
     * 请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length) 且满足以下条件的 最长子数组 ：
     * 1 nums[l] % 2 == 0
     * 2 对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
     * 3 对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
     * 以整数形式返回满足题目要求的最长子数组的长度。
     * <p>
     * 注意：子数组 是数组中的一个连续非空元素序列。
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int len = nums.length;
        int l = 0, r = 0;
        int maxLen = 0;
        while (r <= len - 1) {
            if (nums[l] % 2 == 0) { //找到每个满足条件1的子数组的开始位置
                if (nums[r] <= threshold) { //满足条件1，判断是否满足条件3
                    if (r == len - 1 && nums[r] <= threshold) { // 处理r在数组最后一个位置时
                        maxLen = Math.max(maxLen, r - l + 1);
                        break;
                    }
                    if (nums[r] % 2 != nums[r + 1] % 2) { //满足条件2 则找到符合所有要求的元素，r往后移动
                        r++;
                    } else { //否则，第2个条件不符合，更新maxLen，r移动到下一个位置，l指向r的位置
                        maxLen = Math.max(maxLen, r - l + 1);
                        r++;
                        l = r;

                    }
                } else { // 不满足条件3，更新maxLen，r - l 不包括当前r这个位置
                    maxLen = Math.max(maxLen, r - l);
                    r++;
                    l = r;

                }
            } else { //不满足条件1，往后找下一个是否满足
                l++;
                r++;

            }
        }
        return maxLen;
    }

    // 分组循环板子
    public int longestAlternatingSubarray1(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0, i = 0;
        while (i < n) {
            if (nums[i] > threshold || nums[i] % 2 != 0) {
                i++; // 直接跳过
                continue;
            }
            int start = i; // 记录这一组的开始位置
            i++; // 开始位置已经满足要求，从下一个位置开始判断
            while (i < n && nums[i] <= threshold && nums[i] % 2 != nums[i - 1] % 2) {
                i++;
            }
            // 从 start 到 i-1 是满足题目要求的（并且无法再延长的）子数组
            ans = Math.max(ans, i - start);
        }
        return ans;
    }

    /**
     * TODO leetcode 1869 哪种连续子字符串更长
     * 给你一个二进制字符串 s 。如果字符串中由 1 组成的 最长 连续子字符串 严格长于 由 0 组成的 最长 连续子字符串，返回 true ；否则，返回 false 。
     * <p>
     * 例如，s = "110100010" 中，由 1 组成的最长连续子字符串的长度是 2 ，由 0 组成的最长连续子字符串的长度是 3 。
     * 注意，如果字符串中不存在 0 ，此时认为由 0 组成的最长连续子字符串的长度是 0 。字符串中不存在 1 的情况也适用此规则。
     */

    public boolean checkZeroOnes(String s) {
        int len = s.length();
        int ansOne = 0, ansZero = 0, i = 0;
        while (i < len) {
            if (s.charAt(i) == '1') {
                int startOne = i;
                i++;
                while (i < len && s.charAt(i) == '1') {
                    i++;
                }
                ansOne = Math.max(ansOne, i - startOne);
            } else {
                int startZero = i;
                i++;
                while (i < len && s.charAt(i) == '0') {
                    i++;
                }
                ansZero = Math.max(ansZero, i - startZero);
            }
        }
        return ansOne > ansZero;
    }

    /**
     * TODO leetcode 1446 连续字符
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     * 请你返回字符串 s 的 能量。
     */
    public int maxPower(String s) {
        int len = s.length();
        int energy = 0, i = 0;
        while (i < len) {
            int start = i;
            i++;
            while (i < len && s.charAt(i) == s.charAt(i - 1)) {
                i++;
            }
            energy = Math.max(energy, i - start);
            start = i;
        }
        return energy;
    }

    /**
     * TODO leetcode 2765 最长交替子序列
     * 给你一个下标从 0 开始的整数数组 nums 。如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个 交替子序列 ：
     * m 大于 1 。
     * s1 = s0 + 1 。
     * 下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，s4 - s3 = -1 ，以此类推，直到 s[m - 1] - s[m - 2] = (-1)m 。
     * 请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。
     * <p>
     * 子数组是一个数组中一段连续 非空 的元素序列。
     */
    public int alternatingSubarray(int[] nums) {
        int len = nums.length;
        int ans = 0, i = 0;
        while (i < len - 1) {
            if (nums[i + 1] - nums[i] != 1) {
                i++;
                continue;
            }
            int start = i;
            i++;

            if (i == len - 1 && Math.abs(nums[i] - nums[i - 1]) == 1) { //对最后一个进行处理
                ans = Math.max(ans, i - start + 1);
                break;
            }
            while (i < len - 1 && nums[i] - nums[i - 1] == nums[i] - nums[i + 1] && Math.abs(nums[i] - nums[i - 1]) == 1) {
                i++;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans == 0 ? -1 : ans;
    }

    //解法二
    public int alternatingSubarray1(int[] nums) {
        int len = nums.length;
        int ans = -1, i = 0;
        while (i < len - 1) {
            if (nums[i + 1] - nums[i] != 1) {
                i++;
                continue;
            }
            int start = i;
            i++;
            while (i < len && nums[i] == nums[start] + (i - start) % 2) { //如果满足条件，nums[start]与nums[i]的差要么是1要么是0
                // 利用（i - start）% 2 来模拟后面的数字与nums[start]的差
                i++;
            }
            ans = Math.max(ans, i - start);
            i--;
        }
        return ans;
    }

    /**
     * @description: TODO leetcode 228
     * 给定一个  无重复元素 的 有序 整数数组 nums 。
     * <p>
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * <p>
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * <p>
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     */

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return res;
        int i = 0;

        while (i < n) {
            String s = "";
            int start = i;
            i += 1;
            while (i < n && nums[i] - nums[i - 1] == 1) {
                i++;
            }
            if (i - 1 == start) { //处理自己指向自己的单个字符串
                s += nums[start];
                res.add(s);
                continue;
            }
            s = nums[start] + "->" + nums[i - 1];
            res.add(s);
        }

        return res;

    }

    // StringBuffer
    public List<String> summaryRanges1(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            ret.add(temp.toString());
        }
        return ret;
    }

    @Test
    public void test() {
        String s = "AAABABBBAAABBBBBBAAAAAAABBB";
        boolean b = winnerOfGame(s);
        System.out.println(b);
    }

    // leetcode 2038 如果相邻两个颜色均相同则删除当前颜色 （下列是屎一样的代码）
    public boolean winnerOfGame(String colors) {
        // char[] s = colors.toCharArray();
        int len = colors.length();
        if (len <= 2) return false;
        StringBuffer s = new StringBuffer(colors);
        int a = 1, b = 1;

        while (a < len && b < len) {
            boolean A = false, B = false;
            int j = a;
            // char ch = s.charAt(j);
            while (j < len - 1) {
                char ch = s.charAt(j);
                if (ch == 'A' && s.charAt(j - 1) == ch && s.charAt(j + 1) == ch) {
                    A = true;
                    s.delete(j, j + 1);
                    break;
                } else {
                    j++;
                }
            }
            if (!A) return false;
            int k = b > 1 ? b - 1 : b;
            // char c = s.charAt(k);
            while (k < len - 2) {
                char c = s.charAt(k);

                if (c == 'B' && s.charAt(k - 1) == c && s.charAt(k + 1) == c) {
                    B = true;
                    s.delete(k, k + 1);
                    break;
                } else {
                    k++;
                }
            }
            if (!B) return true;
            if (k < j) {
                a = j - 1;
                b = k;
            } else {
                a = j;
                b = k;
            }
            len = s.length();
        }
        return false;
    }
    // 实际上只要统计各自满足条件的个数，然后比较大小即可

    public boolean winnerOfGame1(String colors) {
        char[] cs = colors.toCharArray();
        int n = cs.length;
        int a = 0, b = 0;
        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == 'A' && cs[i - 1] == 'A' && cs[i + 1] == 'A') a++;
            if (cs[i] == 'B' && cs[i - 1] == 'B' && cs[i + 1] == 'B') b++;
        }
        return a > b;
    }

}

package slide_window;


import dynamic_programming.DynamicProgramming;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class SliderWindow {
    //    static int i;
    public static void main(String[] args) {
        // 在方法中如果变量只声明不初始化，变量不可使用
        // 如果变量是类的成员变量且为int型，如果没有显式地初始化，默认状态下创建变量初始值为0
//        String i ;
        //当声明的变量需要赋值一个相等的值

//        int i, j, k = 0; // i，j没有初始化
//        String a ,c = " "; // a没有初始化
//          int i, j, k;
//          i = j = k = 1; //可以先声明后，统一初始化
//        System.out.println(SliderWindow.i);
        Scanner in = new Scanner(System.in);
        // 读取整数，表示接下来要输入多少行字符串
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.println("输入的数字为: " + n + " " + m);
//        in.nextLine(); // 消耗整数后的换行符,用于读取输入中的下一行文本（字符串）。主要目的是跳过这个整数后的行换行符，以便从下一行开始读取输入的字符串

        // 读取并输出每行字符串
//        for (int i = 0; i < n; i++) {
//            String inputString = in.nextLine();
//            int length = lengthOfLongestSubstring2(inputString);
//            System.out.println(length);
//        }

        // 关闭输入流
//        in.close();
//        System.out.println();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.out.println(arr[i][0]);
        }
    }

    /**
     * @description: leetcode 3. 无重复字符的最长字串 （本人题解）
     * todo  leetcode 3. 无重复字符的最长字串
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length(); //如果s的长度小于0，返回s的长度
        int[] a = new int[256]; // 字符串有字母数字，符号组成，根据ascii码表字母数字和符号等加起来不超过256
        int maxCharLength = 1;
        int count = 0; // 不含有重复字符的最长子串的长度 计数器

        int pre = 0;
        int post = 0; // pre前指针， post后指针
        while (post < s.length()) {
            if (a[s.charAt(post) - ' '] == 0) { // 使用数组记录字母或者字符出现的次数
                count++;
                a[s.charAt(post) - ' ']++;
                post++;
            } else {
                maxCharLength = maxCharLength > count ? maxCharLength : count; //发现重复字母，更新maxCharLength
                while (pre < post) { //判断前指针的值是否与后指针一致，不一致让前指针往前移动，并将移动过程中的数组值重置为0（表示没出现过）
                    //如果相等则count更新为post - pre，两个指针之间有多少个元素，然后终止pre的移动，继续post的移动
                    if (s.charAt(pre) == s.charAt(post)) {
                        count = post - pre;
                        pre++;
                        post++;
                        break;
                    } else {
                        a[s.charAt(pre) - ' '] = 0;
                        pre++;
                    }
                }
            }
        }
        maxCharLength = maxCharLength > count ? maxCharLength : count;

        return maxCharLength;
    }

    // 滑动窗口实现
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    // 滑动窗口（灵神）
    public int lengthOfLongestSubstring2(String s) {
        char[] ch = s.toCharArray(); // 转换成 char[] 加快效率（忽略带来的空间消耗）
        int n = ch.length, ans = 0, left = 0;
        boolean[] has = new boolean[128]; // 也可以用 HashSet<Character>，这里为了效率用的数组
        for (int right = 0; right < n; ++right) {
            char c = ch[right];
            while (has[c]) // 加入 c 后，窗口内会有重复元素
                has[ch[left++]] = false;
            has[c] = true;
            ans = Math.max(ans, right - left + 1); // 更新窗口长度最大值
        }
        return ans;
    }

    public static String minWindow(String s, String t) {

        return "";
    }

    //todo leetcode 2958 最多k个重复元素的最长子数组  跟无重复字符的最长字串思路一致
    public static int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        if (n <= k) return n;
        int maxL = 0, l = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int r = 0; r < n; r++) {
            cnt.put(nums[r], cnt.getOrDefault(nums[r], 0) + 1);
            while (cnt.get(nums[r]) > k) {
                cnt.put(nums[l], cnt.get(nums[l]) - 1);
                l += 1;
            }
            maxL = Math.max(maxL, r - l + 1);
        }
        return maxL;
    }

    /**
     * todo leetcode 904 水果成篮   思路 滑动窗口
     */
    public static int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int left = 0, ans = 0; // left 窗口左边界，right 窗口右边界
        for (int right = 0; right < fruits.length; right++) {
            int n = fruits[right];
            cnt.put(n, cnt.getOrDefault(n, 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                left += 1;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    // todo leetcode 1493. 删掉一个元素以后全为 1 的最长子数组
    // 模拟
    public static int longestSubarray(int[] nums) {
        int last = 0, cnt = 0, ans = 0, n = nums.length;  //last记录上一次最长的全为1子数组，cnt记录当前全为1的子数组长度
        boolean flag = false; //判断是否在数组中出现过0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) { // 遇到0，更新ans
                ans = Math.max(ans, cnt + last); //当前最长和上一次最长相加与ans进行比较
                last = cnt; //把上一次最长last更新为当前最长cnt
                cnt = 0; //当前长度重置为0
                flag = true; //有为0的元素
            } else { //不为0
                cnt++; //只需要更新cnt
            }

        }
        if (flag) ans = Math.max(ans, cnt + last); //出现过0
        else ans = n - 1; //没有出现0
        return ans;
    }

    // todo 滑动窗口
    public static int longestSubarray2(int[] nums) {
        int ans = 0, zero = 0, l = 0, r = 0; //zero记录窗口中0个数，l窗口左边界， r窗口右边界
        for (; r < nums.length; r++) {
            if (nums[r] == 0) {
                zero += 1;
            }
            while (zero > 1) { //当0的个数大于1时，移动左边界，直到窗口内只有一个0，终止左边界移动
                ans = Math.max(ans, r - l - 1);
                if (nums[l] == 0) {
                    zero -= 1;
                }
                l += 1;
            }
        }
        ans = Math.max(ans, r - l - 1);
        return ans;
    }

    // 删除子数组的最大得分
    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0, n = nums.length;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int right = 0, left = 0, score = 0;
        while (right < n) {
            int m = nums[right];
            score += m;
            cnt.put(m, cnt.getOrDefault(m, 0) + 1);
            while (cnt.get(m) > 1) {
                score -= nums[left];
                cnt.put(nums[left], cnt.get(nums[left]) - 1);
                left++;
            }
            ans = Math.max(ans, score);
            right++;
        }
        return ans;
    }

    // todo leetcode 2841. 几乎唯一子数组的最大和(定长)

    public static long maxSum(List<Integer> nums, int m, int k) {
        long ans = 0, sum = 0; // sum记录窗口中元素的和
        int right = 0, n = nums.size();
        HashMap<Integer, Integer> mp = new HashMap<>();
        while (right < n) {
            int left = right - k + 1; // 保证窗口大小为k
            int a = nums.get(right);
            sum += a; //窗口向右移动
            mp.put(a, mp.getOrDefault(a, 0) + 1); //记录窗口中元素出现的次数
            if (left >= 0) { //窗口中包含k个数
                if (mp.size() >= m) ans = Math.max(ans, sum); //窗口中是否满足至少有m个元素互不相同
                mp.put(nums.get(left), mp.get(nums.get(left)) - 1); //移除窗口左边界元素
                sum -= nums.get(left);
                if (mp.get(nums.get(left)) == 0) mp.remove(nums.get(left)); //移除的元素计数为0，代表窗口中不包含该元素
            }
            right++;
        }
        return ans;
    }
    //todo 2024. 考试的最大困扰度
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        char[] ch = answerKey.toCharArray();
        int ans = 0, r = 0, l = 0, n = ch.length;
        int tCnt = 0, fCnt = 0;  //tCnt:T字符的个数，fCnt: f字符的个数
        while (r < n) {
            if (ch[r] == 'T') tCnt++;
            else fCnt++;
            while (tCnt > k && fCnt > k) {  //都大于k时，需要将窗口左边界右移，直到满足条件
                if (ch[l] == 'T') tCnt--;
                else fCnt--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }

    // todo 1004最大连续1的个数|||
    public static int longestOnes(int[] nums, int k) {
        int ans = 0;
        for (int r = 0, l = 0, cnt = 0; r < nums.length; r++) {
            if (nums[r] == 0) cnt += 1;
            while (cnt > k) {
                if (nums[l] == 0) cnt -= 1;
                l += 1;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}

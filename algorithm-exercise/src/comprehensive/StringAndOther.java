package comprehensive;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO 综合问题
 **/
public class StringAndOther {

    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "\'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put(" &frasl;", "/");
        StringBuffer s1 = new StringBuffer();
        int n = text.length();
        int i = 0;
        while (i < n) {
            char c = text.charAt(i);
            if (c != '&') {
                s1.append(c);
                i++;
                continue;
            }

            int j = i + 1;

            while (j < n && j - i <= 6) {
                String s = text.substring(i, j + 1);
                if (map.containsKey(s)) {
                    s1.append(map.get(s));
                    i = j;
                    break;
                } else {
                    j++;
                }
            }
            i++;
        }
        String res = s1.toString();
        return res;
    }

    // todo leetcode 第374场周赛 2953统计完全子字符串（超时）
    public static int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        if (n < k) return 0;
        int ans = 0;
        HashMap<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.clear();
            char c = word.charAt(i);
            for (int j = i; j >= 0; j--) {
                if (Math.abs(word.charAt(j) - c) <= 2 && Math.abs(word.charAt(j) - c) >= 0) {
                    mp.put(word.charAt(j), mp.getOrDefault(word.charAt(j), 0) + 1);
                    if ((i - j + 1) / mp.size() == k && isEqual(mp)) {
                        ans++;
                    }
                    c = word.charAt(j);
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    // 判断map中的value值是否都相等
    public static boolean isEqual(HashMap<Character, Integer> mp) {
        int i = 0, temp = 0;
        boolean flag = true;
        for (Integer e : mp.values()) {
            if (i == 0) {
                temp = e;
                i++;
            } else {
                if (temp != e) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    // todo 不超时做法  分组循环， 分治， 滑动窗口
    public int countCompleteSubstrings2(String word, int k) {
        int n = word.length();
        int ans = 0, i = 0;
        while (i < n) {
            int start = i;
            i++;
            while (i < n && Math.abs((int) word.charAt(i) - (int) word.charAt(i - 1)) <= 2) {
                i++;
            }
            ans += f(word.substring(start, i), k);

        }
        return ans;
    }

    public int f(String s, int k) {
        char[] c = s.toCharArray();
        int res = 0;
        for (int m = 1; m <= 26 && m * k <= c.length; m++) {
            int[] mp = new int[26];
            int r = 0;
            while (r < s.length()) {
                mp[c[r] - 'a']++;
                int l = r + 1 - k * m;
                if (l >= 0) {
                    boolean isCompelet = true;
                    for (int j = 0; j < mp.length; j++) {
                        if (mp[j] > 0 && mp[j] != k) {
                            isCompelet = false;
                            break;
                        }
                    }
                    if (isCompelet) res++;
                    mp[c[l] - 'a']--;
                }
                r++;
            }
        }
        return res;
    }


    @Test
    public void testLong() {
        String s = "YazaAay";
        String s1 = longestNiceSubstringRefine(s);
        System.out.println(s1);
    }

    // todo leetcode 最长的美好子字符串  标签： 位运算 分治 滑动窗口 字符串 哈希表
    // 思路：采用双指针做法，枚举所有子串，判断是否为美好字符串，用一个数组记录每个字母出现的次数
    public static String longestNiceSubstring(String s) {
        int n = s.length();
        String res = "";
        char[] ch = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[58]; // 每次枚举初始化一个数组
            for (int j = i; j >= 0; j--) {
                cnt[ch[j] - 'A']++;
                boolean isBea = true;
                for (int k = 0; k < 26; k++) { //每次枚举，遍历数组是否符合美好子字符串的要求
                    if ((cnt[k] > 0 && cnt[k + 32] == 0) || (cnt[k] == 0 && cnt[k + 32] != 0)) {
                        isBea = false; //出现小写字母而不出现大写字母或者出现大写字母而不出现小写字母，则该子字符串不是美好子字符串
                    }
                }
                if (isBea) { //是的话，如果ans小于该子字符串的长度，更新res
                    if (i - j + 1 > res.length()) {
                        res = s.substring(j, i + 1);
                    }
                }
            }
        }
        return res;
    }

    //改进：二进制记录单词是否出现 1代表出现 0代表不出现
    public static String longestNiceSubstringRefine(String s) {
        int n = s.length();
        String res = "";
        char[] ch = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int upper = 0, lower = 0;
            for (int j = i; j >= 0; j--) {
                if (ch[j] <= 'Z') upper |= (1 << (ch[j] - 'A'));  //如果是大写
                if (ch[j] >= 'a') lower |= (1 << (ch[j] - 'a'));  //如果是小写
                if ((upper ^ lower) == 0 && i - j + 1 > res.length()) { //如果一个字母大小写都出现时，则upper异或lower为0
                    res = s.substring(j, i + 1);
                }
            }
        }
        return res;
    }
    // todo leetcode 395. 至少有 K 个重复字符的最长子串  字符串 哈希表 滑动窗口 分治

    /**
     * @description: 暴力解法
     */
    public int longestSubstring1(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int maxLen = 0;
        for (int i = 0; i < ch.length; i++) {
            int[] cnt = new int[26];
            for (int j = i; j >= 0; j--) {
                cnt[ch[j] - 'a']++;
                boolean ok = true;
                for (int l = 0; l < 26; l++) {
                    if (cnt[l] > 0 && cnt[l] < k) {
                        ok = false;
                    }
                }
                if (ok) {
                    maxLen = Math.max(maxLen, i - j + 1);
                }
            }
        }
        return maxLen;
    }

    /**
     * @description： 分治法
     * 假设输入字符串符合题意要求，统计字符串中所有字符出现的次数，次数小于k次的字符作为整个字符串的分割条件，使用递归函数对每个
     * 被分割的字符串进行处理。
     */
    public int longestSubstring2(String s, int k) {
        int n = s.length();
        if (k == 1) return n;
        if (k > n) return 0;
        HashMap<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Character c : mp.keySet()) {
            if (mp.get(c) < k) { //只需要找到一个可以分割的字符
                int res = 0;
                for (String ss : s.split(String.valueOf(c))) {
                    res = Math.max(res, longestSubstring2(ss, k));
                }
                return res;
            }
        }
        return n;
    }
    // （如果是数组而不是字符串）采用模拟的方法进行分割


    // 滑动窗口

    public int longestSubstring(String s, int k) {
        int ans = 0;
        int len = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (int curKind = 1; curKind <= 26; curKind++) {
            //对于限定的字符数量的条件下进行滑动窗口
            Arrays.fill(cnt, 0);
            int left = 0, right = 0;
            //totalKind:窗口内所有字符类型数量，sumKind:窗口内满足出现次数不少于k的字符类型数量
            int totalKind = 0, sumKind = 0;
            while (right < len) {
                int rightIndex = cs[right] - 'a';
                cnt[rightIndex]++;
                if (cnt[rightIndex] == 1) totalKind++;
                if (cnt[rightIndex] == k) sumKind++;
                //当总字符种类数量不满足限定的字符种类数量，需要被迫移动左指针来减少总字符种类数量
                while (totalKind > curKind) {
                    int leftIndex = cs[left] - 'a';
                    if (cnt[leftIndex] == 1) totalKind--;
                    if (cnt[leftIndex] == k) sumKind--;
                    cnt[leftIndex]--;
                    left++;
                }
                if (totalKind == sumKind) ans = Math.max(ans, right - left + 1);
                //主动移动右指针
                right++;
            }
        }
        return ans;
    }

    //todo leetcode 2953 统计完全子字符串   字符串 哈希表 滑动窗口


    //todo leetcode 最长的美好子字符串   字符串 哈希表 滑动窗口 分治


}

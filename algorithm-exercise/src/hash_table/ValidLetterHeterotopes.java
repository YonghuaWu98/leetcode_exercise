package hash_table;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:ValidLetterHeterotopes
 * @Description TODO Leetcode第242题  有效的字母异位词
 * @Author 86156
 * @Date 2023/4/20 20:36
 * @Version 1.0
 **/
public class ValidLetterHeterotopes {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        boolean anagram = ValidLetterHeterotopes.isAnagram(s, t);
        System.out.println(anagram);
    }


    /**
     * @Description //TODO 定义一个数组记录字符串s里字符出现的次数（数组是一个简单哈希表）
     * @Param [s, t]
     * @return boolean
     **/
    public static boolean isAnagram(String s,String t) {
        int[] ans = new int[26];  // 定义一个哈希数组
        for (int i = 0; i < s.length(); i++) {
            ans[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            ans[t.charAt(i) - 'a'] -= 1;
            if (ans[i] < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * @Description //TODO 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     * @Param [s, t]
     * @return boolean
     **/
    public static boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i = 0;i < s.length();i++) {
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(int i = 0;i < t.length();i++) {
            char ch1 = t.charAt(i);
            map.put(ch1,map.getOrDefault(ch1,0)-1);
            if(map.get(ch1) < 0) {
                return false;
            }
        }
        return true;
    }
}

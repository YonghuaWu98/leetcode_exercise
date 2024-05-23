package hash_table;/*
 *@title RansomLetter
 *@author 86156
 *@description
 *@create 2023/4/20 20:02
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:RansomLetter
 * @Description TODO Leetcode第383题  赎金信
 * @Author 86156
 * @Date 2023/4/20 20:02
 * @Version 1.0
 *
 * java中遍历字符串中字符的三种方式
 * 1）.toCharArray()
 * 2）.length()， charAt()
 * 3）.length()， substring(i ,i+1)
 * public static void main(String[] args) {
 *         String str = "keep walking！！！";
 *         方法一
 *         char[] charArray = str.toCharArray();
 *         for (char i:charArray){
 *             System.out.println(i);
 *         }
 *         for (int i = 0; i < charArray.length; i++) {
 *             System.out.println(c[i]);
 *         }
            方法二  for(int i=0;i<str.length();i++){
          System.out.println(str.charAt(i));
          }
        方法三
       *for(int i=0;i<str.length();i++){
       *System.out.println(str.substring(i,i+1));
 *      }
}
**/
public class RansomLetter {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (int i = 0;i < ransomNote.length();i++) {
            map.put(ransomNote.charAt(i),map.getOrDefault(ransomNote.charAt(i),0)+1); //map中的key装ransonNote中的字符，value装ransomNote中字符出现的次数。
        }
        for (int i = 0;i < magazine.length();i++) { //遍历magazine字符串
            if (map.containsKey(magazine.charAt(i)) && map.get(magazine.charAt(i)) != 0) { //判断map是否包含magazine字符串的元素，并且map以该元素为键的value值不小于零。
                map.put(magazine.charAt(i),map.getOrDefault(magazine.charAt(i),0)-1); //map中对应的value减一
            }
        }
        for (Integer value : map.values()) { //遍历map的value，当存在一个不为零时，表示ransomNote有的元素magazine没有或者这个元素比magazine中的多。
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * @Description //TODO 官方题解
     * @Param [ransomNote, magazine]
     * @return boolean
     **/
    public boolean canConstruct1(String ransomNote, String magazine) {
        // 定义一个哈希映射数组
        int[] record = new int[26];

        // 遍历
        for(char c : magazine.toCharArray()){
            record[c - 'a'] += 1;
        }

        for(char c : ransomNote.toCharArray()){
            record[c - 'a'] -= 1;
        }

        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for(int i : record){
            if(i < 0){
                return false;
            }
        }

        return true;
    }
}

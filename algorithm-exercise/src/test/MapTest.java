package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String, Character> map1 = new HashMap<>();
        map1.put("&quot;", '\"');
        map1.put("&apos;", '\'');
        map1.put("&amp;", '&');
        map1.put("&gt;", '>');
        map1.put("&lt;", '<');
        map1.put(" &frasl;", '/');
        // 遍历值（只取值，效率高，代码简洁）
//        for (Character c : map.values()) {
//            System.out.println("value = " + c);
//        }
        // 遍历键 （只取键，效率高，代码简洁）
//        for (String s : map.keySet()) {
//            System.out.println("key = " + s);
//        }
        //通过键找值 效率低
//        for (String key : map.keySet()) {
//
//            Character value = map.get(key);
//
//            System.out.println("Key = " + key + ", Value = " + value);
//
//        }
        // 在for-each循环使用entries来遍历 效率高
//        for (Map.Entry<String, Character> entry : map1.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }


//        HashMap<int[], String> map = new HashMap<>();
//
//        int[] key1 = {1, 2, 3};
//        int[] key2 = {1, 2, 3};
//
//        map.put(key1, "Value1");
//
//        // 使用 Arrays.hashCode() 和 Arrays.equals() 来判断数组相等性
//        if (map.containsKey(key2)) {
//            String value = map.get(key2);
//            System.out.println("Contains key, value: " + value);
//        } else {
//            System.out.println("Key not found");
//        }


        HashMap<List<Integer>, String> map = new HashMap<>();

        int[] key1 = {1, 2, 3};
        List<Integer> keyList1 = Arrays.asList(1, 2, 3);

        map.put(keyList1, "Value1");

        int[] key2 = {1, 2, 3};
        List<Integer> keyList2 = Arrays.asList(1, 2, 3);

        // 使用 List 作为键
        if (map.containsKey(keyList2)) {
            String value = map.get(keyList2);
            System.out.println("Contains key, value: " + value);
        } else {
            System.out.println("Key not found");
        }


    }

}

package hash_table;/*
 *@title TowNumSum
 *@author 86156
 *@description
 *@create 2023/4/19 15:25
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:TowNumSum
 * @Description TODO leetcode第一题：两数之和
 * @Date 2023/4/19 15:25
 * @Version 1.0
 **/
public class TowNumSum {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 3, 4};
        int target = 6;
        int[] a = TowNumSum.twoSum2(nums, target);
        System.out.print("这两个整数的下标为：");
        for (int i : a) {
            System.out.print(i);
        }


    }

    /*
     * @Description //TODO 官方暴力法
     * @Param [nums, target]
     * @return int[]
     **/
    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    /*
     * @Description //TODO 暴力解法： 双重for循环
     * @Param [nums, target]
     * @return int[]
     **/
    public static int[] twoSum0(int[] nums, int target) {
        int[] a = new int[2]; // 数组a记录两数和为target的下标
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (diff == nums[j]) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }

    /*
     * @Description //TODO 哈希表法
     * @Param [nums, target]
     * @return int[]
     **/
    public static int[] towSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            map.put(i, target - i);  // 数组元素作为map的key，target-i作为map的值
        }
        int k = 0;
        int[] a = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.size() == 1 && map.get(nums[i]) * 2 == target) {  // 如果nums长度为2且目标元素重复，即nums[i]*2 = 6，如：nums = [3,3]，target = 6；
                a[0] = 0;
                a[1] = 1;
            }
            if (map.size() < nums.length && map.get(nums[i]) * 2 == target) { // 如果nums长度大于2且目标元素重复，nums = [3,4,7,3]，target = 6；
                a[k] = i;
                k++;
            }
            int t = map.getOrDefault((target - nums[i]), 0);  // 如果nums长度大于2且目标元素不重复，nums = [3,2,4], target = 6;
            if (t != 0 && t != map.get(t)) {
                a[k] = i;
                k++;
            }
        }
        return a;
    }
    /*
     * @Description //TODO 官方哈希表法
     * @Param [nums, target]
     * @return int[]
     **/
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}


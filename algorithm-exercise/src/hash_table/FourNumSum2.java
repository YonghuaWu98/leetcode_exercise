package hash_table;/*
 *@title FourNumSum
 *@author 86156
 *@description
 *@create 2023/4/19 20:30
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:FourNumSum
 * @Description TODO LeetCode第454题 四数相加II   总结，看到形如：A+B....+N=0的式子，要转换为(A+...T)=-((T+1)...+N)再计算，这个T的分割点一般是一半，特殊情况下需要自行判断。定T是解题的关键。
 * @Version 1.0
 **/
public class FourNumSum2 {
    public int fourSumCount1(int[] nums1,int[] nums2,int[] nums3,int[] nums4) {
        Map<Integer,Integer> map1 = new HashMap<Integer, Integer>();//map1中key放nums1和nums2数组中两两元素之和，value放两两元素之和出现的次数。
        int count = 0; //count计数符合的元组个数
        for(int i:nums1) {
            for(int j:nums2) {
                map1.put(i+j,map1.getOrDefault(i+j,0)+1);
            }
        }
        Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();//map2中key放0-（nums3和nums3数组中两两元素之和），value这个数出现的次数。
        for(int i:nums3) {
            for(int j:nums4) {
                map2.put(-i-j,map2.getOrDefault(-i-j,0)+1);
            }

        }
        for(Integer key:map1.keySet()) {
            if(map2.containsKey(key)) {
                count += map1.get(key)*map2.get(key);
            }
        }
        return count;
    }
 /**
  * @Description //TODO 86156 官方题解
  * @Date 17:34 2023/4/20
  * @Param [A, B, C, D]
  * @return int
  **/
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }
}

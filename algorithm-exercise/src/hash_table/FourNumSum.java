package hash_table;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:FourNumSum
 * @Description TODO
 * @Date 2023/4/21 15:15
 * @Version 1.0
 **/
public class FourNumSum {
    @Test
    public void test() {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> lists = FourNumSum.fourSum(nums, target);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }

    }

    /**
     * @Description //TODO 四数之和 a+b+c+d = target
     * @Date 19:37 2023/4/21
     * @Param [nums, target]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> fourSum(int[] nums,int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0;i < nums.length - 3;i++) {
            if (nums[i] > 0 && target <= 0) return result;  //如果排好序的数组的第一个元素大于零且target小于等于零，则返回空的result
            if (i > 0 && nums[i] == nums[i - 1]) continue; //如果nums[i]和它前一个相等，则结束本次循环，跳到下一次循环
            for (int j = i + 1;j < nums.length - 2;j++) { //
                int right = nums.length - 1; //数组尾部
                int left = j + 1;
                if (j > i+1 && nums[j] == nums[j - 1]) continue; //j > i+1 避免本次循环nums[j]与前一个相等时，而被跳过。
                while(left < right){
                    if ((long)nums[i] + nums[j] + nums[left] + nums[right] < target) {
                        left++;
                    }else if ((long)nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    }else {
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while (right > left && nums[right] == nums[right - 1]) right--; //去重c
                        while (right > left && nums[left] == nums[left + 1]) left++; //去重d
                        right--;
                        left++;
                    }

                }
            }

        }
        return result;
    }
}

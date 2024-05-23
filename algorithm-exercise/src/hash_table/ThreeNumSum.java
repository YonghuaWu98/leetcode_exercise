package hash_table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:ThreeNumSum
 * @Description TODO Leetcode第15题 三数之和  a+b+c
 * @Version 1.0
 **/
public class ThreeNumSum {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> tupleRes = ThreeNumSum.threeSum(nums);
        for (int i = 0; i < tupleRes.size(); i++) {
            System.out.println(tupleRes.get(i));
        }

    }
    /**
     * @Description //TODO 先排序，后双指针
     * @Param [nums]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> tupleRes = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {  //固定a值
            if (nums[i] > 0) return tupleRes;
            int left = i + 1; //b值
            int right = nums.length - 1;//c值
            if (i > 0 && nums[i] == nums[i - 1]) continue; //a去重，当前a值与前一个相同时直接跳过本次循环，写成num[i] == nums[i +1],表示遇到相同a就跳过
            while (left < right) { //循环条件，right > left
                if (nums[i] + nums[left] + nums[right] > 0) {  //a+b+c大于零，移动rigth，减小a+b+c的值
                    right--;
                }else if (nums[i] + nums[left] + nums[right] < 0) {  //a+b+c小于零，移动left，增大a+b+c的值
                    left++;
                }else {  //a+b+c等于零，添加该元组，并移动right和left
                    tupleRes.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (right > left && nums[right] == nums[right - 1]) right--; //去重b
                    while (right > left && nums[left] == nums[left + 1]) left++; //去重c
                    // 没有重复，正常移动
                    right--;
                    left++;
                }
            }
        }
        return tupleRes;
    }
}


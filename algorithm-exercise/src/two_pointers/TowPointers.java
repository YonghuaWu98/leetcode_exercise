package two_pointers;

/**
 * @Description TODO 双指针ac
 **/
public class TowPointers {
    //leetcode 11 盛最多水的容器
    public static int maxArea(int[] height) {
        int res = 0, l = 0, r = height.length - 1;
        while (r > l) {
            int col = Math.min(height[l], height[r]) * (r - l);
            res = Math.max(res, col);
            if (height[l] < height[r]) {
                l++;
            }else {
                r--;
            }

        }
        return res;
    }

}

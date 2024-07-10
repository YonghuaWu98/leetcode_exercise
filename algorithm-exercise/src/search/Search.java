package search;

/**
 *  TODO 查找相关算法
 **/
public class Search {
    /**
     * todo 二分查找 开区间(l, r)模板
     * 查找最后一个 <= target的数的下标
     * 最大化查找（可行区在左侧）
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int l = -1, r = arr.length; // 开区间
        while (l + 1 < r) {  //循环中始终保持区间的定义
            int mid = (r + l) >> 1;
            if (arr[mid] <=  target) l = mid;
            else r = mid;
        }
        return l;
    }
    /**
     * todo 二分查找 开区间(l, r)模板
     * 查找第一个 >= target的数的下标
     * 最小化查找（可行区在右侧）
     */
    public static int binarySearch2(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] >= target) r = mid;
            else l = mid;
        }
        return r;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,5,5,7,8,9};
        int idx = binarySearch(arr, 5);
        System.out.println(idx);

        int idx2 = binarySearch2(arr, 5);
        System.out.println(idx2);
        System.out.println((1 << 31) - 1);

        System.out.println(1 << 0);
    }

}

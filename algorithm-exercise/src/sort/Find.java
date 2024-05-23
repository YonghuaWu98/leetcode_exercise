package sort;


import java.util.ArrayList;
import java.util.Arrays;

public class Find {

    public static void main(String[] args) {
        int[] spells = {5, 1, 3};
        int[] potions = {1, 3, 2, 5, 4};
        int success = 7;
        Find.successfulPairs(spells, potions, success);
    }
    //二分查找应用
    //todo leetcode2300 咒语和药水的成功对数
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;

        int[] res = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            int l = 0, r = potions.length - 1, mid = (l + r) / 2, tag = 0;
            while (r >= l) {
                long mergy = (long) potions[mid] * spell;

                if (mergy >= success) {
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
                // tag = mid;
                mid = (l + r) / 2;
            }
            res[i] = n - l;
        }
        return res;
    }
}

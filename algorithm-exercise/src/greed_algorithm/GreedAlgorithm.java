package greed_algorithm;

import org.junit.Test;

import java.util.*;

/**
 * @Description TODO
 * @Author 86156
 * @Date 2023/7/4 16:33
 * @Version 1.0
 **/
public class GreedAlgorithm {

    public static void main(String[] args) {
//        int[] g = {1, 2, 3};
//        int[] s = {1, 1};
//        int contentChildren = findContentChildren(g, s);
//        System.out.println(contentChildren);
        int n, beg, end;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            beg = in.nextInt();
            end = in.nextInt();
            arr[i][0] = beg;
            arr[i][1] = end;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        int i = GreedAlgorithm.attendMoreCompetion(arr);
        System.out.println(i);
    }

    /**
     * @description: 分发饼干
     * @param: g 胃口值数组
     * @param: s 饼干值数组
     * @return: int meetChildNum 满足孩子的最大数值
     */
    public static int findContentChildren(int[] g, int[] s) {
        if (s.length == 0) return 0;
        int meetChildNum = 0; //为满足孩子的最大数值
        boolean[] tag = new boolean[s.length]; //记录饼干值是否分配，true分配，false为未分配
        boolean[] temp = new boolean[g.length];//记录胃口值是否满足，true为满足，false为未满足
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < s.length; j++) {
                //如果饼干值满足孩子胃口值、饼干未分配以及胃口未满足
                if (s[j] >= g[i] && tag[j] == false && temp[i] == false) {
                    meetChildNum++;
                    tag[j] = true;
                    temp[i] = true;
                }
            }
        }
        return meetChildNum;
    }

    //代码随想录题解
    public static int findContentChildren1(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int meetChildNum1 = 0;
        int start = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (start >= 0 && s[start] >= g[i]) {
                meetChildNum1++;
                start++;
            }
        }
        return meetChildNum1;
    }

    //leetcode 第53题 最大子数组和

    /**
     * @description: 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * @param: nums 整数数组
     * @return: int result  最大子数组和
     */
    public static int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE; //最大子数组和
        int i = 0;
        int count = 0; //子数组的和
        while (i < nums.length) {
            count += nums[i]; //count不小于零时，继续累加后面的元素
            result = count > result ? count : result; //result记录当前最大子数组和
            if (count < 0) { //贪心所在：如果子数组和小于零，那就从下一个元素，count从0开始进行累加
                i++; //i必须加，否则死循环，因为在本次循环中，执行到continue时，本次循环中剩下的语句不会执行
                count = 0; //count重新从零开始
                continue; //跳过本次循环
            }
            i++;
        }
        return result;
    }

    //TODO 方法二: 动态规划

    //leetcode 第122题 买卖股票的最佳时机2

    /**
     * @description: 思想--局部最优：收集每天的正利润，全局最优：求得最大利润。
     * @param: prices
     * @return: int
     */
    public static int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0); //收集每天的正利润
        }
        return result;
    }
    //TODO 动态规划
    //leetcode 第55题 跳跃游戏

    /**
     * @description: 贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点。
     * @param: nums
     * @return: boolean
     */
    public static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;// 只有一个元素，就是能达到
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(nums[i] + i, cover);// 注意这里是小于等于cover
            if (cover >= nums.length - 1) return true;// 说明可以覆盖到终点了

        }
        return false;
    }

    //TODO 动态规划
    //leetcode 第45题 跳跃游戏||
    public static int jump(int[] nums) {
        if (nums.length == 1) return 0;
        //最大覆盖范围
        int maxCoverDistance = 0;
        //当前最大覆盖区域
        int curCoverDistance = 0;
        //记录跳跃次数
        int jumpCount = 0;
        for (int i = 0; i < nums.length; i++) {
            //在最大覆盖区域内，更新最大覆盖范围
            maxCoverDistance = Math.max(nums[i] + i, maxCoverDistance);
            if (maxCoverDistance >= nums.length - 1) { //如果最大覆盖范围达到或者包括集合终点，则还需要跳跃一次到达终点
                jumpCount++;
                break;
            }
            if (i == curCoverDistance) { //没有到达尾部时，当前位置是否在当前最大覆盖区域的最后，是则更新为最大覆盖范围
                curCoverDistance = maxCoverDistance;
                jumpCount++; //跳跃一次
            }
        }
        return jumpCount;
    }

    //leetcode 第1005题 K次取反后最大化的数组和
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);//将数组转为递增有序数组
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) { //从前往后遍历，将遇见的负数转为正数，同时K--
                nums[i] *= -1;
                k--;
            }
        }
        Arrays.sort(nums);
        if (k % 2 != 0) { //如果K还是大于0，将重新排序的数组中的最小值反复转变
            nums[0] *= -1;
        }
        for (int i : nums) {
            sum += i;
        }
        return sum;
    }
    //leetcode 第134题 加油站

    //860题 柠檬水找零

    /**
     * @description: 依次收取顾客的费用并找零
     * @param: bills
     * @return: boolean
     */
    public static boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> recordNum = new HashMap<>();
        recordNum.put(5, 0);
        recordNum.put(10, 0);
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 10) {
                if (recordNum.get(5) != 0) {
                    recordNum.put(5, recordNum.get(5) - 1);
                    recordNum.put(bills[i], recordNum.get(bills[i]) + 1);
                } else {
                    return false;
                }
            } else if (bills[i] == 20) {
                if (recordNum.get(5) >= 1 && recordNum.get(10) >= 1) {
                    recordNum.put(5, recordNum.get(5) - 1);
                    recordNum.put(10, recordNum.get(10) - 1);
                } else if (recordNum.get(5) >= 3) {
                    recordNum.put(5, recordNum.get(5) - 3);
                } else {
                    return false;
                }

            } else {
                recordNum.put(bills[i], recordNum.get(bills[i]) + 1);
            }

        }
        return true;
    }

    //代码随想录

//    int five = 0;
//    int ten = 0;
//
//        for (int i = 0; i < bills.length; i++) {
//        if (bills[i] == 5) {
//            five++;
//        } else if (bills[i] == 10) {
//            five--;
//            ten++;
//        } else if (bills[i] == 20) {
//            if (ten > 0) {
//                ten--;
//                five--;
//            } else {
//                five -= 3;
//            }
//        }
//        if (five < 0 || ten < 0) return false;
//    }
//        return true;
    //leetcode第406题 根据身高重建队列
    public static int[][] reconstructQueue(int[][] people) {
        //先根据身高进行排序
        Arrays.sort(people,(a, b) ->{
            if (a[0] == b[0]) return a[1] - b[1];  //在身高相同的情况下，a[1] - b[1] > 0,则按k（前面有k个身高大于或等于自己的）
                                                    //进行降序排序
            return b[0] - a[0]; //在身高不相等的情况下，b[0] - a[0] > 0,则按k（前面有k个身高大于或等于自己的）
                                //进行升序排序
        });
        LinkedList<int[]> res = new LinkedList<>();

        for (int[] p : people) { //将排序好的数组按k对应的位置插入链表
            res.add(p[1],p);
        }
        //res满足要求，转为二维数组
        return res.toArray(new int[people.length][]);
    }

    //leetcode第452题 用最少数量的箭引爆气球
    public static int findMinArrowShots(int[][] points) {
        int left, right = 0;//记录可以射爆气球的左右区间
        int count = 1;//记录需要几只箭
        Arrays.sort(points,(a, b) ->{ //按从小到大的顺序排序points
            return a[0] - b[0];
        });
        left = points[0][0]; //初始化左右区间
        right = points[0][1];
        for (int[] p : points) {
            if (p[0] >= left && p[0] <= right) { //在区间内时，更新新的left和right  left取两者的最大值  right取两者的最小值
                left = Math.max(p[0],left);
                right = Math.min(p[1],right);
            }else{  //否则，count++，left，right更新为不满足条件p的左右区间
                count += 1;
                left = p[0];
                right = p[1];
            }
        }
        return count;
    }
    //leetcode第435题 无重叠区间
    public static int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals,(a, b) -> {
            return a[1] - b[1];
        });
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < right) {
                count += 1;
            }else{
                right = Math.max(right, intervals[i][1]);
            }
        }
        return count;
    }
    //leetcode第763题 划分字母区间
    public static List<Integer> partitionLabels(String s) {

        Set<Character> res = new HashSet<>(); //用来记录字符串有哪些字符，用set进行去重
        for (int i = 0;i < s.length();i++) {
            res.add(s.charAt(i));
        }
        List<int[]> arr = new ArrayList<>(); //用于保存每个字符起始和终止位置
        for (Character c : res) {
            arr.add(new int[]{s.indexOf(c), s.lastIndexOf(c)});
        }
        int [][] array = arr.toArray(new int[res.size()][]); //转成二维数组
        Arrays.sort(array, (a, b) -> {
            return a[0] - b[0];
        });
        int right = array[0][1]; //右边界
        int left = array[0][0]; //左边界
        List<Integer> result = new ArrayList<>(); //保存每个区域中字符的长度
        for (int i = 1;i < array.length;i ++) {
            if(array[i][0] > right) { //判断字符初始位置是否大于right，如果大于，则来到分割点，记录长度，并更新左边界
                result.add(right - left + 1);
                left = array[i][0];
            }
            right = Math.max(right, array[i][1]); //更新右边界

        }
        result.add(right - left + 1); //最右端
        return result;
    }
    /**
     * @description: 1 统计每一个字符最后出现的位置
     *               2 从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
     * @param: S
     */
    public static List<Integer> partitionLabels_daimasuixianglu(String S) {
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26];
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }
        for (int i = 0; i < edge.length; i++) {
            System.out.println(edge[i]);
        }

        int idx = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx,edge[chars[i] - 'a']);
            if (i == idx) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }
    @Test
    public void test() {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> list = GreedAlgorithm.partitionLabels_daimasuixianglu(s);
        System.out.println(list);
    }
    @Test
    public void testAttendMoreCompetion() {
        int n, beg, end;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            beg = in.nextInt();
            end = in.nextInt();
            arr[i][0] = beg;
            arr[i][1] = end;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        int i = GreedAlgorithm.attendMoreCompetion(arr);
        System.out.println(i);

    }
    //洛谷1803
    public static int attendMoreCompetion(int[][] arr) {
        int count = 1;
        int endTime = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] >= endTime) {
                count++;
                endTime = arr[i][1];
            }else {
                endTime = endTime < arr[i][1] ? endTime : arr[i][1];
            }
        }
        return count;
    }
}

package backtracking;

import org.junit.Test;

import java.util.*;

/**
 * @Description TODO
 * @Author 86156
 * @Date 2023/6/6 10:49
 * @Version 1.0
 **/
public class BacktrackingSubject {

    //回溯三部曲
    //1 递归函数的返回值以及参数
    //2 回溯函数终止条件
    //3 单层搜索过程
    //回溯模板
/*   void backtrackingFunction(parameters) {
        if (终止条件） {
            存放结果；
            return；
        }
     }
     for (选择：本层集合元素（树中节点孩子的数量就是集合的大小））{
        处理节点；
        backtracking（路径，选择列表）；//递归
        回溯，撤销处理结果
     }

 */
    public static void main(String[] arg) {
//        List<String> res = letterCombinations("23");
//        System.out.println(res);
        int[] nums = {84, -48, -33, -34, -52, 72, 75, -12, 72, -45};
//        int target = 8;
//        combinationSum2(candidates, target);
//        List<List<Integer>> subsequences = findSubsequences(nums);
//        System.out.println(subsequences.size());
//        combinationSum3(3,9);
//        System.out.println(res);
        int[] b = {1,2,3};
        List<List<Integer>> permute = permute(b);
        System.out.println(permute);

    }

    //leetcode 第77题 给定两个整数n和k，返回范围[1,n]中所有可能的k个数的组合
    public static List<List<Integer>> result = new ArrayList<>();
    public static LinkedList<Integer> path1 = new LinkedList<>();

    public static List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    public static void combineHelper(int n, int k, int startIndex) {
        if (path1.size() == k) {
            result.add(new ArrayList<>(path1));
            return;
        }
        for (int i = startIndex; i < n - (k - path1.size()) + 1; i++) { //单层for循环
            path1.add(i);
            combineHelper(n, k, i + 1);//递归
            path1.removeLast();
        }
    }
    //leetcode 第216题 找出所有相加之后为n的k个数的组合，且满足下列条件：
    //只使用数字1到9
    //每个数字最多使用一次

    public static List<List<Integer>> res = new ArrayList<>();  //全局集合res，收集所有满足条件的组合
    public static LinkedList<Integer> path2 = new LinkedList<>(); //
    public static int sum = 0;

    public static List<List<Integer>> combinationSum3(int k, int n) {
        combinationSumHelper(k, n, 1);
        return res;
    }

    public static void combinationSumHelper(int k, int n, int startIndex) {
        if (sum > n) return; //剪枝
        if (path2.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(path2));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path2.size()) + 1; i++) { //单层遍历
            path2.add(i);
            sum += i;
            combinationSumHelper(k, n, i + 1); //递归
            sum -= i;
            path2.removeLast();
        }
    }

    //leetcode第17题 电话号码的字母组合
    public static List<String> res1 = new ArrayList<>();
    public static String path = "";

    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return res1;
        }
        HashMap<Character, String> letterMemo = new HashMap<>();
        letterMemo.put('2', "abc");
        letterMemo.put('3', "def");
        letterMemo.put('4', "ghi");
        letterMemo.put('5', "jkl");
        letterMemo.put('6', "mno");
        letterMemo.put('7', "pqrs");
        letterMemo.put('8', "tuv");
        letterMemo.put('9', "wxyz");
        backtracking(letterMemo, digits, 0, digits.length());
        return res1;
    }

    public static void backtracking(HashMap<Character, String> memo, String digits, int startIndex, int k) { //
        if (path.length() == k) { // 结束条件
            res1.add(path);
            return;
        }
        String s = memo.get(digits.charAt(startIndex));
        for (int i = 0; i < s.length(); i++) { // 遍历树的宽度
            path += s.charAt(i);
            backtracking(memo, digits, startIndex + 1, k); // 递归树的下一层
            path = path.substring(0, path.length() - 1); // 回溯
        }
    }

    // TODO leetcode 第39题 组合总数
    public static List<List<Integer>> results = new ArrayList<>();
    public static LinkedList<Integer> paths = new LinkedList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrackingHelper(candidates, target, 0, 0);
        return results;
    }

    public static void backtrackingHelper(int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            results.add(new ArrayList<>(paths));
            return;
        }
//        for (int i = startIndex;i <= candidates.length - 1;i++) {
        for (int i = startIndex; i <= candidates.length - 1 && sum + candidates[i] <= target; i++) { //剪枝处理
            paths.add(candidates[i]);
            sum += candidates[i];
            backtrackingHelper(candidates, target, sum, i); //不用i+1，startIndex = 1表示可以重复读取当前的数
            sum -= candidates[i];
            paths.removeLast();
        }
    }

    // TODO leetcode 第40题 组合总和||
    public static List<List<Integer>> results1 = new ArrayList<>();
    public static List<Integer> paths1 = new ArrayList<>();

    /**
     * @description: 找出集合中所有可以使数字和为target的组合，集合中的每一个数字在每一个组合中只能使用一次，解集中不能包含重复的组合
     * @param: candidates 编号集合
     * @param: target 目标数
     * @return: java.util.List<java.util.List < java.lang.Integer>>
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); //将集合元素进行升序排序
        backtrackingHelper1(candidates, target, 0, 0);
        return results1;
    }

    /**
     * @description: 递归函数
     * @param: candidates 编号集合
     * @param: target 目标数
     * @param: sum 数字和
     * @param: startIndex 每一轮for循环的起始位置
     * ① 如果一个集合来求组合的话，就需要startIndex ② 如果是多个集合去组合，
     * 各个集合之间相互影响，那么就不用startIndex
     * @return: void
     */
    public static void backtrackingHelper1(int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            results1.add(new ArrayList<>(paths1));
            return;
        }
        for (int i = startIndex; i <= candidates.length - 1 && sum + candidates[i] <= target; i++) { //剪枝处理
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue;  //对同一层使用过的元素进行跳过,传入的数组必须是递增的。
            paths1.add(candidates[i]);
            sum += candidates[i];
            backtrackingHelper1(candidates, target, sum, i + 1); //每个数字只能使用一次所以是i+1
            sum -= candidates[i];
            paths1.remove(paths1.size() - 1);

        }
    }

    // TODO 使用标记数组
    LinkedList<Integer> pathS = new LinkedList<>();
    List<List<Integer>> ans = new ArrayList<>();
    boolean[] used;
    int sumN = 0;

    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        used = new boolean[candidates.length];
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        Arrays.fill(used, false);
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTracking(candidates, target, 0);
        return ans;
    }

    private void backTracking(int[] candidates, int target, int startIndex) {
        if (sumN == target) {
            ans.add(new ArrayList(pathS));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sumN + candidates[i] > target) {
                break;
            }
            // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            sumN += candidates[i];
            pathS.add(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            backTracking(candidates, target, i + 1);
            used[i] = false;
            sum -= candidates[i];
            pathS.removeLast();
        }
    }

    // TODO LeetCode第78题 子集
    public List<List<Integer>> set1 = new ArrayList<>();
    public List<Integer> setChildren1 = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs1(nums, 0);
        return set1;
    }

    private void dfs1(int[] nums, int startIndex) {
        set1.add(new ArrayList<>(setChildren1));
        for (int i = startIndex; i < nums.length; i++) {
            setChildren1.add(nums[i]);
            dfs1(nums, i + 1);
            setChildren1.remove(setChildren1.size() - 1);
        }
    }

    // TODO LeetCode 第90题 子集||
    public static List<List<Integer>> set2 = new ArrayList<>();
    public static List<Integer> setChildren2 = new ArrayList<>();

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs2(nums, 0);
        return set2;
    }

    private static void dfs2(int[] nums, int startIndex) {
        set2.add(new ArrayList<>(setChildren2));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) continue;
            setChildren2.add(nums[i]);
            dfs2(nums, i + 1);
            setChildren2.remove(setChildren2.size() - 1);
        }
    }

    // TODO leetcode第491题 递增子序列
    public static List<Integer> subsequences = new ArrayList<>();
    public static List<List<Integer>> incrementSubsequences = new ArrayList<>();
    public static Set<List<Integer>> set3 = new HashSet<>();

    public static List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length == 1) {
            return incrementSubsequences;
        }
        dfs(nums, 0);
        for (List<Integer> i : set3) {
            incrementSubsequences.add(i);
        }
        return incrementSubsequences;
    }
    // 判断子序列是否为递增序列
    public static boolean isIncrementSubsequences(List<Integer> list) {
        int temp = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i >= temp) {
                temp = i;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void dfs(int[] nums, int startIndex) {
        if (subsequences.size() > 1) {
            if (isIncrementSubsequences(subsequences)) {
//                if (!set3.contains(subsequences)) {
                    set3.add(new ArrayList<>(subsequences));
//                    if (set3.contains(subsequences)) incrementSubsequences.add(new ArrayList<>(subsequences));
            } else {
                return;
            }
        }
        for (int i = startIndex; i < nums.length; i++) {
//            if (i > startIndex && nums[i] == nums[i - 1]) continue;
            subsequences.add(nums[i]);
            dfs(nums, i + 1); //递归
            subsequences.remove(subsequences.size() - 1); //回溯
        }
    }

    //代码随想录题解
    private List<Integer> path_k = new ArrayList<>();
    private List<List<Integer>> res_k = new ArrayList<>();
    public List<List<Integer>> findSubsequences1(int[] nums) {
        backtracking(nums,0);
        return res_k;
    }

    private void backtracking (int[] nums, int start) {
        if (path_k.size() > 1) {
            res_k.add(new ArrayList<>(path_k));
        }

        int[] used = new int[201];
        for (int i = start; i < nums.length; i++) {
            if (!path_k.isEmpty() && nums[i] < path_k.get(path_k.size() - 1) ||
                    (used[nums[i] + 100] == 1)) continue;
            used[nums[i] + 100] = 1;
            path_k.add(nums[i]);
            backtracking(nums, i + 1);
            path_k.remove(path_k.size() - 1);
        }
    }

    //TODO leetcode第46题 全排列
    static List<List<Integer>> pailie = new ArrayList<>();
    static List<Integer> response = new ArrayList<>();

    public static List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums, used);
        return pailie;
    }
    public static void dfs(int[] nums, boolean[] used) {
        if (response.size() == nums.length) {
            pailie.add(new ArrayList<>(response));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            response.add(nums[i]);
            used[i] = true;
            dfs(nums, used);
            response.remove(response.size() - 1);
            used[i] = false;
        }
    }
    @Test
    public void test() {
        BacktrackingSubject b = new BacktrackingSubject();
        int[] nums = {2,3,4,2};
        List<List<Integer>> lists = b.permuteUnique(nums);
        System.out.println(lists);
    }
    //TODO Leetcode第47题 全排列||
    List<List<Integer>> pailie2 = new ArrayList<>();
    List<Integer> response2 = new ArrayList<>();
    boolean[] used2;
    public List<List<Integer>> permuteUnique(int[] nums) {
        used2 = new boolean[nums.length];
        Arrays.sort(nums);
        dfs2(nums, used2);
        return pailie2;
    }
    private void dfs2(int[] nums, boolean[] used) {
        //结束条件
        if (response2.size() == nums.length) {
            pailie2.add(new ArrayList<>(response2));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //used2[i] == true，说明同一树枝nums[i]使用过
            //i > 0 && used2[i - 1] == false && nums[i -1] == nums[i -1],说明同一树层使用过nums[i]
            if (used[i] || (i > 0 && used[i -1] == false && nums[i] == nums[i - 1])) continue; //树层去重和树枝去重
            response2.add(nums[i]);
            used[i] = true;
            dfs2(nums, used); //递归
            response2.remove(response2.size() - 1); //回溯
            used[i] = false;
        }
    }
    //TODO 131 分割回文串
    public List<List<String>> lists = new ArrayList<>();
    public List<String> list = new ArrayList<>();

    /**
     * @description:
     * @param: s
     * @return: java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> partition(String s) {
        dfs(s, 0);
        return lists;
    }
    /**
     * @description:
     * @param: s
     * @param: startIndex
     * @return: void
     */
    public void dfs(String s,int startIndex) {
        if (startIndex >= s.length()) { //结束条件：当切割点大等于字符串的长度
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (isHuiWen(s, startIndex, i)) { //判断截取的字符串是否为回文串
                list.add(s.substring(startIndex,i + 1));
            }else {
                continue;
            }
            dfs(s, i + 1); //递归
            list.remove(list.size() - 1); //回溯
        }
    }
    /**
     * @description: 判断截取字符串是否为回文串
     * @param: s 输入的字符串
     * @param: startIndex 截取的开始索引
     * @param: endIndex 结束索引
     * @return: boolean

     */
    public boolean isHuiWen(String s, int startIndex, int endIndex) {
        while (startIndex <= endIndex) {
            if (s.charAt(startIndex) == s.charAt(endIndex)) {
                startIndex++;
                endIndex--;
            }else {
                return false;
            }

        }
        return true;
    }

    // todo 分割回文串 1 回溯+动态规划预处理  2 回溯+记忆化搜索

    //TODO 93题 复原IP地址
    List<String> ipRes = new ArrayList<String>();
    StringBuilder stringBuilder = new StringBuilder();
    public List<String> restoreIpAddresses(String s) {
        restoreIpAddressesHandler(s, 0, 0);
        return ipRes;
    }
    // number表示stringbuilder中ip段的数量
    public void restoreIpAddressesHandler(String s, int start, int number) {
        // 如果start等于s的长度并且ip段的数量是4，则加入结果集，并返回
        if (start == s.length() && number == 4) {
            ipRes.add(stringBuilder.toString());
            return;
        }
        // 如果start等于s的长度但是ip段的数量不为4，或者ip段的数量为4但是start小于s的长度，则直接返回
        if (start == s.length() || number == 4) {
            return;
        }
        // 剪枝：ip段的长度最大是3，并且ip段处于[0,255]
        for (int i = start; i < s.length() && i - start < 3 && Integer.parseInt(s.substring(start, i + 1)) >= 0
                && Integer.parseInt(s.substring(start, i + 1)) <= 255; i++) {
            // 如果ip段的长度大于1，并且第一位为0的话，continue
            if (i + 1 - start > 1 && s.charAt(start) - '0' == 0) {
                continue;
            }
            stringBuilder.append(s.substring(start, i + 1));
            // 当stringBuilder里的网段数量小于3时，才会加点；如果等于3，说明已经有3段了，最后一段不需要再加点
            if (number < 3) {
                stringBuilder.append(".");
            }
            number++;
            restoreIpAddressesHandler(s, i + 1, number);
            number--;
            // 删除当前stringBuilder最后一个网段，注意考虑点的数量的问题
            stringBuilder.delete(start + number, i + number + 2);
        }
    }
    @Test
    public void testIp() {
        String s = "aba";
        List<List<String>> partition = partition(s);
        System.out.println(partition);
    }

    // leetcode 698 划分为k个相等子集
    // 先对问题进行一层抽象：有 n 个球，k 个桶，如何分配球放入桶中使得每个桶中球的总和均为 target
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        // 数组所有元素求和
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        int[] bucket = new int[k + 1];
        boolean res = backtrack(nums, 0, bucket, k, target);
        return res;
    }
    /**
     * @description:
     * @param: nums
     * @param: index 第几个球开始选择
     * @param: bucket 桶
     * @param: k
     * @param: target
     * @return: boolean
     */
    public boolean backtrack(int[] nums, int index, int[] bucket, int k, int target) {
        //结束条件
        if (index == nums.length) return true; //当index == nums.length时，每个桶内球的和一定为target，因为这是在满足sum % k == 0的条件下
        //所以下面这个结束条件就不需要了

//        if (index == nums.length) {
//            // 判断现在桶中的球是否符合要求 -> 路径是否满足要求
//            for (int i = 0; i < k; i++) {
//                if (bucket[i] != target) return false;
//            }
//            return true;
//        }
        for (int i = 0; i < k; i++) {
            if (i > 0 && bucket[i] == bucket[i -1]) continue; //当遍历的这个桶与前一个桶的重量相同时，，跳过本次循环
            if (bucket[i] + nums[index] > target) continue;
            bucket[i] += nums[index];
            if(backtrack(nums, index + 1, bucket, k, target)) return true;
            bucket[i] -= nums[index];
        }

        return false;
    }
    //测试
    @Test
    public void testCanPartitionKSubsets() {
        int[] nums = {2, 2, 2, 3, 3};
        boolean b = canPartitionKSubsets(nums, 3);
        System.out.println(b);
    }
    @Test
    public void testFindTargetSumWays() {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        int ways = findTargetSumWays(nums, target);
        System.out.println(ways);
    }

    /** 
     * @description:  leetcode 第494 目标和 （抽象为二叉树：采用回溯法枚举所有组合，记录符合的结果数，采用递归深度优先搜索，并使用
     * 数组存储二叉树节点，通过遍历数组来访问二叉树）
     * @aram: null
     * @return:
     */ 
    int count = 0; //全局变量，记录满足的构造方式的种数
    public int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        int[] num = new int[len * 2];
        int left = 0, right = 1;
        for (int i = 0; i < len; i++) { //取出数组nums中的元素，每个元素取一正一负填充扩容数组num
            if (right < len * 2) {
                num[left] = nums[i];
                num[right] -= nums[i];
                left += 2;
                right += 2;
            }
        }

        dfs(num, target, 0, 1, 0);
        return count;

    }
    /**
     * @description:  回溯法枚举，深度优先遍历
     * @param: num 扩容的数组
     * @param: target 目标和
     * @param: left 左子树
     * @param: right 右子树
     * @param: sum
     */
    public void dfs(int[] num, int target, int left, int right, int sum) {

        if (left > num.length - 2) { //走到了叶子节点
            if (sum == target) { //如果和与目标和相等则count加1
                count++;
            }
            return;
        }
        for (int i = left; i <= right; i++) { //for循环控制遍历节点的左右孩子
//            if (sum + num[i] > target) continue;
            sum += num[i];
            dfs(num, target, left + 2, right + 2, sum); //用数组存储二叉树节点
            sum -= num[i];
        }
        return;
    }
        //leetcode 官方题解
        int count_l = 0;

        public int findTargetSumWays_l(int[] nums, int target) {
            backtrack(nums, target, 0, 0);
            return count;
        }

        public void backtrack(int[] nums, int target, int index, int sum) {
            if (index == nums.length) {
                if (sum == target) {
                    count++;
                }
            } else {
                backtrack(nums, target, index + 1, sum + nums[index]);
                backtrack(nums, target, index + 1, sum - nums[index]);
            }
        }



}
    
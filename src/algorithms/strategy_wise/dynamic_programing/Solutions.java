package algorithms.strategy_wise.dynamic_programing;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    /**
     * LeetCode 70: 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     *
     * 示例 2：
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 解题思路：斐波那契数列的现实问题
     *  思路一：记忆化搜索（自上向下的解题顺序）：需要递归
     *  思路二【正解】：动态规划（自下向上的解题思路）：不需要递归
     *      此题中，状态转移方程为：f(n) = f(n-1)+f(n-2)
     *      即，当前步骤的结果为前两步之和
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 递归解法：记忆化搜索
//        return countSteps(n);
        // 动态规划解法
        return countStepsDP(n);
    }
    private int countSteps(int n){
        // 这里最好生成一个有 n+1个元素的列表，跟实际意义对应
        int[] record = new int[n+1];
        if (n==1) return 1;
        if (n==2) return 2;

        if(record[n]==0){
            record[n]= countSteps(n-1) + countSteps(n-2);
        }
        return record[n];
    }
    private int countStepsDP(int n){
        int[] record = new int[n+1];
        // 这里和斐波那契数列不太一样，当 n=2 时，值为2，而斐波那契数列中为1
        record[0] = 1;  // 这里初始的是1
        record[1] = 1;
        for (int i = 2; i < n+1; i++) {
            // 状态转移方程：爬 n 阶台阶的可行数=(爬 n-1 阶台阶可行数) + (爬 n-2 阶台阶可行数)
            record[i] = record[i-1] + record[i-2];
        }
        return record[n+1];
    }


    /**
     * LeetCode 120: 三角形最短路径之和 【中等】
     *
     * 解题思路：动态规划
     *  1、寻找转移方程的时候，要自上而下的去归纳，而实现的时候，要自下而上的实现，而且要考虑边界情况是否有特殊情况
     *      1.1 比如本题中，我们要找到达某个元素的最短路径和，就要考虑到达该点是从哪里转移过来的
     *      1.2 而非是我现在在哪个点，我能到达哪个点
     *      1.3 概括起来就是：我是从哪儿得来的（自上而下）而不是我能得道什么（自下而上）
     *  2、确定每个元素的最小路径和
     *  3、最终结果就是最后一行元素中最小路径和最小的那个
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 记录所有元素的最小路径和：这里二维数组的列数是有冗余的，列数是最大列数
        int[][] allEleSum = new int[triangle.size()][triangle.size()];
        // 初始情况是第一个值
        allEleSum[0][0] = triangle.get(0).get(0);
        // 遍历每一行
        for (int i = 1; i < triangle.size(); i++) {
            // 每行的第一元素的最小路径和就是上层第一个元素得道的，所以转移方程如下
            allEleSum[i][0] = allEleSum[i-1][0] + triangle.get(i).get(0);
            // 为每行里的每个元素生成最小路径和
            for (int j = 1; j < triangle.get(i).size(); j++) {
                // 对于每行的非边界元素的最小路径和的转移方程如下
                allEleSum[i][j] = Math.min(allEleSum[i-1][j-1],allEleSum[i-1][j]) + triangle.get(i).get(j);
            }
            // 每行最后一个元素的最小路径和只能来自上层最后一个元素(每行元素个数等于行数）
            allEleSum[i][i] = allEleSum[i-1][i-1] + triangle.get(i).get(i);
        }
        // 返回最终结果：最后一行([n-1]行)的最小值
        int res = allEleSum[triangle.size()-1][0];  // 初始为最后一行的第一个元素
        for (int k = 1; k < triangle.size(); k++) {  // 最后一行有 n 个元素
            res = Math.min(res, allEleSum[triangle.size()-1][k]);
        }
        return res;
    }
    // 对空间进行优化:这里需要自下而上的递推
    public int minimumTotal2(List<List<Integer>> triangle) {
        /**
         * 遍历要保证两点：
         *  1、确保每个元素被扫描到
         *  2、明确扫描到顺序
         *  此题的扫描顺序就有点需要注意
         *  另外，申请保留状态的空间是n，遍历完三角形的最后一层，保留的就是最后一层中每个元素的最小路径和
         *  三角形前几层的遍历的时候，数组只使用了部分
         */
        int len = triangle.size();
        // 用来记录每个元素的最小路径和，初始为每个元素的路径和为0（因为数组从索引0开始，这里index=0的位置是多余的）
        int[] dp = new int[len];
        dp[0] = triangle.get(0).get(0);
        // 遍历每一行
        for (int i = 1; i < len; i++) {
            // 遍历每行的元素，更新 dp 中的值【这里每个元素需要用到前面元素，所以需要从后往前更新，不然就覆盖了】
            dp[i] = dp[i-1]+triangle.get(i).get(i);
            for (int j = i-1; j >0 ; j--) {
                // 每行有几个元素，就能更新几个(Math.min 中访问的是上一行的时候，元素的最小路径和）
                dp[j] = Math.min(dp[j], dp[j-1]) + triangle.get(i).get(j);
            }
            dp[0] = dp[0] + triangle.get(i).get(0);
        }
        // 遍历完后，取最小值
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }

    /**
     * LeetCode 64: 最小路径和 【中等】
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     *
     * 解题思路：动态规划（正解）
     * 解法一：修改原数组
     *  遍历矩阵，修改每个元素的值为当前路径最小值，最后返回右下角元素的值
     * 解法二：不修改原数组
     *  设置一个等于列数的数组，用来装当前行每个元素的最小路径和（记录前面所有状态）
     *  当前元素如果是第一个元素，那么只能从上一行对应位置到达此元素，所以转移方程为 dp[j] = dp[j]+cur 【此时的j是上一行的记录】
     *  当前元素如果是第一行的元素，那么只能从当前元素前一个元素到达，所以转移方程为 dp[j] = dp[j-1]+cur 【此时的j-1是当前行的记录】
     *  否则，转移方程为 dp[j] = min(dp[j],dp[j-1]) + cur
     *  最后返回状态数组的最后一个元素
     *
     *  PS：动态规划需要遍历到所有元素，所有元素不一定要都保留状态
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int len = grid.length;
        int cols = grid[0].length;
        // 记录当前行每个元素的状态，初始时元素状态都为0，相当于第一行的上一行
        int[] dpRecord = new int[cols];
        // 遍历矩阵每一行
        for (int i = 0; i < len; i++) {
            // 第一个元素的转移方程
            dpRecord[0] = dpRecord[0] + grid[i][0];
            // 遍历当前行每个元素
            for (int j = 1; j < cols; j++) {
                // 第一行只能从左边到达
                if (i==0){
                    dpRecord[j] = dpRecord[j-1] + grid[i][j];
                } else {
                    dpRecord[j] = Math.min(dpRecord[j], dpRecord[j - 1]) + grid[i][j];
                }
            }
            // 最后一个元素的转移方程
//            dpRecord[cols-1] = dpRecord[cols-2] + grid[i][cols-1];
        }
        return dpRecord[cols-1];
    }


    /**
     * LeetCode 343：整数拆分
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * 示例 1:
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     *
     * 示例 2:
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     *
     * 解题思路：动态规划
     * 1、状态记录：申请一个 n+1 的空间，记录所有数值的状态
     * 2、扫描所有元素（一个整数 n 可看作从 1 到 n 个数值）
     * 3、转移方程：dp[n] = max(dp[n],i*(n-i),i*dp[n-i])
     *      可能的最大值：
     *          1）就是当前值 dp[n]
     *          2）就分割成了一种情况，而且是最大值 i*(n-i)
     *          3）一般情况：当前值和子问题最优解的乘积
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        // 记录元素i分解后的最大乘积（这里数组大小为n和n+1都可以，这里主要是为了让索引和实际情况对应）
        int[] pdRecord = new int[n + 1];
        pdRecord[1] = 1;
        // 对每个元素求最大乘积
        for (int i = 1; i <= n; i++) {
            // 自底向上，遍历i被分解成 j + (i-j)，计算i的最大乘积
            for (int j = 1; j <= i-1; j++) {
                // 每个可能包括：分割的j和(i-j)恰好就是最大值结果、当前值✖️子问题的最优解、当前值
                pdRecord[i] = max3(j*(i-j), j*pdRecord[i-j], pdRecord[i]);
            }
        }
        return pdRecord[n];
    }
    private int max3(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }

    // 测试
    public static void main(String[] args) {
        Solutions slt = new Solutions();
        int[][] raw = new int[][]{{-1},{2,3},{1,-1,-3}};
        int[][] raw2 = new int[][]{{2},{3,4},{6,5,7},{4,1,8,3}};
        int[][] testMinPathSum = {{1,3,1},{1,5,1},{4,2,1}};
        List<List<Integer>> test = generateData(raw);
        System.out.println(slt.minimumTotal2(test));

        System.out.println(slt.minPathSum(testMinPathSum));
    }
    private static List<List<Integer>> generateData(int[][] raw){
        List<List<Integer>> test = new ArrayList<>();
        for (int i = 0; i < raw.length; i++) {
            List<Integer> curRow = new ArrayList<>();
            for (int j = 0; j < raw[i].length; j++) {
                curRow.add(raw[i][j]);
            }
            test.add(curRow);
        }
        return test;
    }

}
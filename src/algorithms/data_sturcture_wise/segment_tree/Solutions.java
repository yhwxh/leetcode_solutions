package algorithms.data_sturcture_wise.segment_tree;

import data_sturcture.SegmentTree;
import utils.Merger;

public class Solutions {
    /**
     * LeetCode 303: 区域和检查（数组不可变,即不涉及线段树的更新操作）【简单】
     * 给定一个整数数组 nums，求出数组从索引 i 到 j（i≤j）范围内元素的总和，包含i、j两点。
     * <p>
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引i到j（i≤j）范围内元素的总和，包含i、j两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     * <p>
     * 示例：
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     * <p>
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     */

    // 线段树解法
    private class NumArray {
        private SegmentTree<Integer> data;

        public NumArray(int[] nums) {
            Integer[] tmp = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                tmp[i] = nums[i];
            }
            data = new SegmentTree<>(tmp, (a, b) -> a + b);
        }

        public int sumRange(int left, int right) {
            if (left < 0 || left >= data.getSize() || right < 0 || right >= data.getSize() || left > right) {
                throw new IllegalArgumentException("");
            }
            return data.get(left, right);
        }
    }
    // 经典解法: 进行预处理，不需要每次求区间和都扫描一下区间
    private class NumArray2 {
        // 存储传入数组前i个元素的和，即 sums[0,i-1]

        private int[] sum;

        public NumArray2(int[] nums) {
            nums = new int[nums.length+1];
            sum[0] = 0;
            // 这里 i 含义表示个数，不是索引，即数组中前几个元素的和
            for (int i = 1; i < sum.length; i++) {
                // 前 i 个元素的和=前i-1个元素的和+数组中第i个元素（对于索引为i-1）
                sum[i] = sum[i-1] + nums[i-1];
            }
        }

        public int sumRange(int left, int right) {
            if (left < 0 || left >= sum.length || right < 0 || right >= sum.length || left > right) {
                throw new IllegalArgumentException("");
            }
            // 注意：这里用户传入的是索引，需要转化为sum的索引，sum的索引是有含义的，表示几个元素的和，是个数
            return sum[right+1] - sum[left];
        }
    }
}

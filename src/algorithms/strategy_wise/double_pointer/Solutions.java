package algorithms.strategy_wise.double_pointer;

public class Solutions {
    /**
     * LeetCode 930: 和相同的二元子数组
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * 子数组 是数组的一段连续部分。
     *
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
     *
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        //TODO
        int res = 0;
        int left = 0, right = 0;
        int sum = nums[0];
        while (left <=right && right<nums.length) {
            if (sum < goal) {
                right++;
                if (right<=nums.length-1)
                    sum += nums[right];
            } else if (sum > goal) {
                sum -= nums[left];
                left++;
            } else if (sum==goal){
                res++;
                sum-=nums[left];
                left++;
            }
        }
        return res;
    }

    /**
     * 将整数转化成字符串
     * @param num
     */
    public String translateNum(int num){
        StringBuilder res = new StringBuilder();
        while (num>0){
            res.append(num%10);
            num /= 10;
        }
        reverse(res.toString());
        return res.toString();
    }
    private void reverse(String str){
        int a = 0, b = str.length()-1;
        while (a < b){

        }
    }

    /**
     * LeetCode 42: 接雨水 【困难】
     * 给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     *
     * 解题思路：碰撞指针
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        //TODO
        // 定义遍历数组的左右指针
        int left=0, right = height.length-1;
        // 记录返回结果
        int res = 0;
        // 定义记录当前元素左侧[0,i-1]最大值, 和当前元素右侧[i+1,height.lenght-1]的最大值
        int leftMax =0, rightMax = 0;
        // 遍历数组，左右指针交替遍历完整个数组
        while (left<right){
            // 分情况移动指针
            if (height[left] < height[right]){

            }

        }
        return res;
    }


    public static void main(String[] args) {
        Solutions s = new Solutions();
        int[] testArr = {1, 0, 1, 0, 1};
        System.out.println(s.numSubarraysWithSum(testArr, 2));
    }
}

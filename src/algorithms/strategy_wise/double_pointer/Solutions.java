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
        int res = 0;
        int left = 0, right = 0;
        int sum = nums[0];
        while (left < nums.length-1 || right < nums.length-1) {
            if (sum < goal) {
                right++;
                sum += nums[right];
            } else if (sum > goal) {
                left++;
                sum -= nums[left];
            } else {
                res++;
                if (right < nums.length - 1){
                    right++;
                    sum+=nums[right];
                } else if (left < nums.length-1){
                    left++;
                    sum-=nums[left];
                }
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


    public static void main(String[] args) {
        Solutions s = new Solutions();
        int[] testArr = {1, 0, 1, 0, 1};
        System.out.println(s.numSubarraysWithSum(testArr, 2));
    }
}

package algorithms.data_sturcture_wise.priority_queue;

import java.util.*;

public class Solutions {
    /**
     * LeetCode 347： 前K个高频元素 【中等】
     *  给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     *
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     * 解题思路：
     *  1、先将数组放入Map，记录每个元素的出现次数
     *  2、将map中的元素加入一个大小为K的最大堆里
     *  3、遍历最大堆，取出堆中元素，放进数组，返回结果
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)){
                map.put(i, map.get(i)+1);
            } else {
                map.put(i, 1);
            }
        }
        // 按频次大小排序的堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> map.get(a) - map.get(b)
        );
        for (int key: map.keySet()){
            if (pq.size() < k){
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())){
                // 维护堆，让堆保持频次最高堆三个元素：当前元素比堆顶元素大，就替换
                pq.remove();
                pq.add(key);
            }
        }
        int[] res = new int[k];
        int i = 0;
        while (!pq.isEmpty()){
            res[i] = pq.remove();
            i++;
        }
        return res;
    }

    /**
     * LeetCode 239 或者 剑指Offer 59-1 ：滑动窗口最大值 【困难】
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     *
     * 解释:
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * 解题思路：
     *  思路1：最大堆
     *  思路2：队列，令队列的队首永远保留者当前队列的最大值，所以，关键是如何维护这个队列
     *      1、初始时，队列为空，第一个元素加入队列后，队列中最大值就是当前元素
     *      2、第二个元素加入的时候，判断该元素与队首元素谁大，若新元素大，则让新元素替换原来最大值位置，进入队首；若不比它大，就保留
     *      3、后续加入的元素重复 （2）中判断，
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //TODO
        if (nums == null || nums.length==0) return null;
        int[] res = new int[nums.length - k + 1];
        Queue<Integer> window = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i==0) window.add(nums[i]);
            else {
                if (window.size()==k)
                    window.poll();
                window.add(nums[i]);
            }
        }
        return res;
    }
    // 最大堆实现
    public int[] maxSlidingWindow2(int[] nums, int k){
        if (nums == null || nums.length==0) return null;
        int[] res = new int[nums.length-k+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> a - b
        );
        for (int i = 0; i < nums.length; i++) {
            if(i<k-1) {
                pq.add(nums[i]);
            } else
            // 从第 k 个元素开始形成一个窗口
            if (nums[i]>=pq.peek()){
                // 每移动一个元素
                pq.remove();
                pq.add(nums[i]);
                res[i-k+1] = pq.peek();
            }
        }
        return res;
    }
}

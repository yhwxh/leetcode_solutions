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
                // 前 - 后：表示升序； 后 - 前：表示降序
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
     *  思路2：队列，令队列的队首永远保留者当前队列的最大值，所以，关键是如何维护这个队列（队列中元素个数最多k个）
     *      1、初始时，队列为空，第一个元素加入队列后，队列中最大值就是当前元素
     *      2、第二个元素加入的时候，判断该元素与队尾元素谁大（需要从队尾来操作），若新元素大，则让队尾出队（poll），直到队列中没有比新元素大的元素（新元素把队列中所有比他小的清理掉才能入队）【这一步必须从队尾操作】
     *      3、而且，每新加一个元素（滑动窗口移动一次）都要判断一下当前队首元素是不是有效最大值（因为有可能最大值已经滑出窗口覆盖区间）【这一步必须从队首操作】
     *      4、所以，最终队列中元素的状态是：要么就一个最大值，要么就是从大到小排序后的2～K个值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length<=1) return nums;
        int[] res = new int[nums.length - k + 1];
        // 创建一个双端队列（必须是双端队列），这里存放的是索引，不是元素值，这样会方便点儿，因为后面需要看队首元素是不是有效最大值（就是看索引是不是滑过了 [i-k+1, i] 这个范围）
        Deque<Integer> queueMax = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 先判断队列中的队尾元素是否需要出队（这里需要队尾能有队首的操作，所以双端队列）：就是比较当前队尾元素是否比新来的元素小，如果小，就让队尾元素出队，给新来的老大让位
            while (!queueMax.isEmpty() && nums[queueMax.peekLast()]<=nums[i]){
                // 新来的老大需要把所有比他小的队尾先干掉，所以这里要一个while循环，不是一个if就能完成的
                queueMax.pollLast();
            }
            // 不满足的时候(新来的元素小于队尾，就进入队列排着，这样队列中元素一直保持着从队首到队尾由大到小的排序)，可以直接将新元素加入队尾
            queueMax.addLast(i);
            // 判断队首元素是否依然有效：新元素加入后，相当于窗口移动了一位，此时的队首元素还是原来的老大，但是它有可过时了
            if (queueMax.peek() <= i-k){
                queueMax.poll();
            }
            // 最大值保存：在窗口够数的时候才能记录，当遍历元素个数小于窗口覆盖数量的时候，还不足以能够判断窗口的最大值
            if (i>=k-1){
                res[i-k+1] = nums[queueMax.peek()];
            }
        }
        return res;
    }
    // 优先队列（最大堆）实现
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums==null || nums.length<2) return nums;
        // 结果中，最大值的个数跟窗口的个数有关：n-k+1个
        int[] res = new int[nums.length-k+1];
        // 定义一个优先队列，维护滑动窗口覆盖到的所有元素的最大值（不一定是k个，可能多余K个）
        PriorityQueue<int[]> kTh = new PriorityQueue<>(
                // 最大堆的话就是 后-前
                (a,b)->b[0]-a[0]
        );
        // 初始优先队列，将k个元素先存入优先队列
        for (int i = 0; i < k; i++) {
            kTh.add(new int[]{nums[i], i});
        }
        // 初始res中第一个最大值
        res[0] = kTh.peek()[0];
        // 遍历数组，移动滑动窗口，查找每个最大值: 从第K个元素开始
        for (int i = k; i < nums.length; i++) {
            // 先将当前第i个元素加入队列：此时i是新进入窗口的
            kTh.add(new int[]{nums[i],i});
            // 维护下队列中的元素，删除被划过的元素：元素i进入窗口，同时还有一个元素滑出窗口
            while(kTh.peek()[1] <= i-k){ // 这里就是要维护一下当前窗口中最大值
                // 循环只有当最大值走出窗口才会触发，如果其一直在窗口中，那么就会一直保留，同时队列中元素会一直增加，而且队列里还保留了被滑出的非最大值的元素
                // 如果当前队列状态是：最大元素为被滑出的元素，则从队列剔除（因为剔除后还有可能是无效的最大值，所以要用wile循环）
                kTh.poll();
            }
            // 将窗口中最大值加入res
            res[i - k + 1] = kTh.peek()[0];
        }
        return res;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums==null) return null;
        double[] res = new double[nums.length -k +1];
        int windowSize = k;
        if (nums.length<k){
            windowSize = nums.length;
        }
        for (int i = 0; i <= nums.length-k+1; i++) {
            double mid = 0;
            for (int j = i; j <= i+windowSize-1; j++) {
                mid += nums[i];
            }
            res[i] = mid/windowSize;
        }
        return res;
    }

    public static void main(String[] args) {
        Solutions slt = new Solutions();
        int[] test = new int[]{7,2,4};
        slt.maxSlidingWindow(test, 2);
    }
}

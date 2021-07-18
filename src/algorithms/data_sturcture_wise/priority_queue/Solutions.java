package algorithms.data_sturcture_wise.priority_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
                pq.add(k);
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
}

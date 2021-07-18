package data_sturcture;

import data_sturcture.interfaces.QueuePrivate;

import java.util.*;

/**
 * 优先队列(PriorityQueue): 适用于动态数据的场景
 *      需要对数据进行动态处理，队列中的数据时刻在变化，比如操作系统对进程的调度
 *
 *  logn 复杂度，基本和树这种数据结构有关
 */

public class PriQueue<E extends Comparable<E>> implements QueuePrivate<E> {
    private MaxHeap<E> maxHeap;
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.getMax();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }


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

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,34,12,42,-13,4,9};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

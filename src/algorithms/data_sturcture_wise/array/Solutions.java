package algorithms.data_sturcture_wise.array;

import java.util.*;

public class Solutions {
    /**
     * 什么时候用 Set 什么时候用 Map
     * LeetCode 349：两个数组的交集 【简单】
     * <p>
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     * <p>
     * 说明：
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> numsSet = new HashSet<>();
        for (int i : nums1) {
            numsSet.add(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int i : nums2) {
            if (numsSet.contains(i)) {  // 如果用treeSet 这里 contains 的复杂度为 O(logn)
                res.add(i);
                numsSet.remove(i);  // 如果用treeSet 这里 remove 的复杂度为 O(logn)
            }
        }
        int[] resArr = new int[res.size()];
        for (int k = 0; k < res.size(); k++) {
            resArr[k] = res.get(k);
        }
        return resArr;
    }

    /**
     * LeetCode 350: 两个数组的交集 II 【简单】
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * <p>
     * 示例 2:
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * <p>
     * 说明：
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     *
     * @param args
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> mumsMap1 = new HashMap<>();
        for (int i : nums1) {
            if (mumsMap1.containsKey(i)) {
                mumsMap1.put(i, mumsMap1.get(i) + 1);
            } else {
                mumsMap1.put(i, 1);
            }
        }
        List<Integer> ls = new ArrayList<>();
        for (int j : nums2) {
            if (mumsMap1.containsKey(j)) {
                ls.add(j);
                mumsMap1.put(j, mumsMap1.get(j) - 1);
                if (mumsMap1.get(j) == 0) {
                    mumsMap1.remove(j);
                }
            }
        }
        int[] res = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            res[i] = ls.get(i);
        }
        return res;
    }

    /**
     * LeetCode 283: 移动零 【简单】
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * Facebook Bloomberg
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public void moveZeroes(int[] nums) {
        List<Integer> nonZeroElements = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nonZeroElements.add(nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i >= nonZeroElements.size()){
                nums[i] = 0;
            } else {
                nums[i] = nonZeroElements.get(i);
            }
        }
    }
    public void moveZerosOpt(int[] nums){
        // 使用双指针，一个指针遍历数组，另一个用来指向非零元素的末尾边界（维护一个[0,k)这样一个区间，来存放非零元素）
        int k = 0;  // 初始非零区间为[0,0]
        for (int i = 0; i<nums.length; i++){
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for (int i = k; i<nums.length;i++){
            nums[i] = 0;
        }
    }

    public void moveZerosOpt2(int[] nums){
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                if (i != k) {
                    int tmp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = tmp;
                }
                k++;
            }
        }
    }

    /**
     * LeetCode 27：
     */
    public int removeElement(int[] nums, int val) {
        //TODO
        return -1;
    }


    /**
     * 如何写出一个正确的程序：我们以二分查找为例
     *      1、想出正确的算法思路、逻辑
     *      2、正确考虑到所有边界问题
     *          明确变量（边界）的实际意义
     *          循环不变量（循环过程中，不变的因素是什么）
     *      3、
     * @param args
     */
    public int binarySearch(int[] arr, int target){
        int len = arr.length;
        // l,r 为要查找区域的边界
        int l = 0, r = len-1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (arr[mid] == target){
                return mid;
            } else if (target > arr[mid]){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}

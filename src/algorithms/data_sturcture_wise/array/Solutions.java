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

    public static void main(String[] args) {

    }
}

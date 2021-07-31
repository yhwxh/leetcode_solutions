package algorithms.data_sturcture_wise.array;

import algorithms.basic_alg.SortAlgorithm;

import java.util.*;

public class Solutions {
    /**
     * 什么时候用 Set 什么时候用 Map
     * LeetCode 349：两个数组的交集 【简单】
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
     * @param nums1
     * @param nums2
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
     * <p>
     * Facebook Bloomberg
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * <p>
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public void moveZeroes(int[] nums) {
        List<Integer> nonZeroElements = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroElements.add(nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i >= nonZeroElements.size()) {
                nums[i] = 0;
            } else {
                nums[i] = nonZeroElements.get(i);
            }
        }
    }

    public void moveZerosOpt(int[] nums) {
        // 使用双指针，一个指针遍历数组，另一个用来指向非零元素的末尾边界（维护一个[0,k)这样一个区间，来存放非零元素）
        int k = 0;  // 初始非零区间为[0,0]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZerosOpt2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
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
     * LeetCode 27：移除元素 【简单】
     * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 1：
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
     */
    public int removeElement(int[] nums, int val) {
        // 双指针：一个指针用来维护一个[0,k) 的区间，保存所有要留下的元素，同时也维护了新数组的长度
        int k = 0;
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                // 覆盖 [0,k) 中的最后一个元素，也可以替换
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    /**
     * LeetCode 26: 删除有序数组中的重复元素【简单】
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 2：
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     */
    public int removeDuplicates(int[] nums) {
        // 双指针：一个指针遍历数组，一个指针维护一个[0,k) 的区间，用来装所有非重复元素，同时维护长度
        if (nums.length == 1) {
            return 1;
        } else {
            int k = 1;
            for (int i = 1; i < nums.length; i++) {
                // 因为数组有序，我们只需跟 [0,k）中最后一个元素（索引为k-1的元素）比较就可以
                if (nums[i] != nums[k - 1]) {
                    nums[k] = nums[i];
                    k++;
                }
            }
            return k;
        }
    }


    /**
     * LeetCode 80: 删除有序数组中的重复元素【中等】
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1：
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 2：
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
     */
    public int removeDuplicatesII(int[] nums) {
        // 这里要用到三个指针，相对上面的问题，要多一个指针用来记录相似元素的间隔个数（因为有序，索引可以计算间隔）
        if (nums.length == 1) {
            return 1;
        } else {
            int k = 1, j = k - 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[k - 1]) {
                    nums[k] = nums[i];
                    k++;
                    // 移动记录距离的指针，每次添加新元素，该指针都要跟最后一个元素重合
                    j = k - 1;
                } else {
                    if (k - j < 2) {
                        nums[k] = nums[i];
                        k++;
                    }
                }
            }
            return k;
        }
    }

    /**
     * LeetCode 75: 颜色分类 【中等】
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 示例 1：
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * <p>
     * 示例 2：
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     * <p>
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[0]
     * <p>
     * 示例 4：
     * 输入：nums = [1]
     * 输出：[1]
     */
    public void sortColors(int[] nums) {
        // 计数排序
        int[] count = new int[]{0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        for (int j = 0; j < nums.length; j++) {
            if (j < count[0]) {
                nums[j] = 0;
            } else if (j >= count[0] && j < count[0] + count[1]) {
                nums[j] = 1;
            } else {
                nums[j] = 2;
            }
        }
    }

    public void sortColors2(int[] nums) {
        /** 三路快排
         * 三个指针维护三个区域
         */
        int zeros = -1;  // nums[0,zeros] 用来维护所有0的元素
        int two = nums.length;  // nums[two, n-1] 用来装值为2的元素

        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                two--;  // 这里先移动了指针，后面取元素的时候就不需要减 1 了
                swap(nums[i], nums[two]);
//                i++; // 此时是不需要移动 i 的，它会继续判断是否为零的情况
            } else {
                zeros++;
                swap(nums[i], nums[zeros]);
                i++;
            }
        }
    }

    private void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }


    /**
     * leetCode 88: 合并两个有序数组 (考察的是归并排序的归并过程)
     * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
     * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] aux = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (i < m)
                aux[i] = nums1[i];
            else
                aux[i] = nums2[i - m];
        }
        int l = 0, r = m;
        for (int k = 0; k < nums1.length; k++) {
            if (l >= m) {
                nums1[k] = aux[r];
                r++;
            } else if (r >= m + n) {
                nums1[k] = aux[l];
                l++;
            } else if (aux[l] <= aux[r]) {
                nums1[k] = aux[l];
                l++;
            } else {
                nums1[k] = aux[r];
                r++;
            }
        }
    }

    // TODO

    /**
     * leetCode 215: 数组中的第K个最大元素 【中等】
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 解题思路：
     * 1、思路一：先快排，再去第k个最大值，复杂度为 O(nlogn)
     * 2、思路二：利用快排 partition 操作的性质：O(n)
     *    2.1 在寻找基准点的时候，基准点的合适位置，就是该元素最终排好序后的索引位置(每次递归找pivot的时候，已经找到的pivot的位置是不会变的)
     *    2.2 取出第 K 个最大元素就是取第 K 个（或者 arr.length-k 的位置）索引处的位置
     *    2.3 所以，我们可以在找pivot的时候判断pivot的索引是否等于 K
     *    2.4 如果 pivot 比k小，就只在左区间找就好了，否则就只在有区间找
     */
    public int findKthLargest2(int[] nums, int k) {
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        sortAlgorithm.quickSort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1,k);
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int pivot = partition2(nums, left, right);
        // 这里添加上使用用第二种思路的代码
        if (pivot == nums.length-k){
            return;
        }
        quickSort(nums, left, pivot - 1,k);
        quickSort(nums, pivot + 1, right,k);

    }

    private int partition(int[] nums, int left, int right) {
        // 普通快排
        int pivot = left;
        int separator = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] <= nums[pivot]) {
                swap(nums, i, separator + 1);
                separator++;
            }
        }
        swap(nums, pivot, separator);
        return separator;
    }

    private int partition2(int[] nums, int left, int right) {
        // 双路快排
        int pivot = left;
        int less = left + 1;
        int great = right;

        while (less <= great) {
            if (nums[less] < nums[pivot]) {
                less++;
            } else if (nums[great] > nums[pivot]) {
                great--;
            } else {
                swap(nums, less, great);
                less++;
                great--;
            }
        }
        swap(nums, pivot, great);
        return great;
    }

    private void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

    /** leetCode 167: 两数之和II - 输入有序数组 【简单】
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 示例 1：
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * 示例 2：
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     *
     * 示例 3：
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     *
     * 解题思路：碰撞指针
     * 1、两个指针同时从最左边和最右边相向移动
     * 2、判断当前元素和是否满足条件
     * 3、小于就向右移动左指针，大于就向左移动右指针
     * 4、满足条件返回索引
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int left=0;
        int right= numbers.length-1;

        while(left <right){
            int curSum = numbers[left] + numbers[right];
            if( curSum < target){
                left++;
            } else if(curSum > target){
                right--;
            } else{
                return new int[]{left+1, right+1};
            }
        }
        return null;
    }
    // leetCode 125:
    // leetCode 344:
    // leetCode 345:
    // leetCode 11:
    // leetCode 209:
    // leetCode 3:
    // leetCode 438:
    // leetCode 76:

    /**
     * LeetCode 14：最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串""。
     * <p>
     * 示例 1：
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * <p>
     * 示例 2：
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        // 最长公共前缀就是所有是所有字符串中最短的，我们先假定一个字符串为公共前缀，然后依次跟其他元素进行判断
        // 如果其他元素的公共前缀不是它，就缩小公共前缀的长度，知道满足停止条件
        if (strs.length == 0) return "";
        String pivot = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(pivot)) {
                if (pivot.length() == 0) return "";
                pivot = pivot.substring(0, pivot.length() - 1);
            }
        }
        return pivot;
    }

    /**
     * LeetCode 122: 买卖股票的最佳时机II（其实是买卖股票的最大利润问题）
     * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 解题思路：
     * 如何实现利润最大化的求解本质是：怎么抓住每个上升波段
     * 1、假定我们知道了买进的最低价格，只要遍历数组，在高于它的价格卖出就可以
     * 2、可以初始化买入价格为数组的第一个元素（数组必须大于1才能有一次完整交易）
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        // 固定住买入价格
        int buyPrice = prices[0];
        // 遍历数组去寻找卖出点
        for (int k = 1; k < prices.length; k++) {
            if (prices[k] > buyPrice) {
                sum += prices[k] - buyPrice;
            }
            // 当前价格不是最低价格，修改买入价格；如果卖出也要修改最低价格，因为我们要在卖出后的范围重新寻找买入价格
            buyPrice = prices[k];
        }
        return sum;
    }

    /**
     * 如何写出一个正确的程序：我们以二分查找为例
     * 1、想出正确的算法思路、逻辑
     * 2、正确考虑到所有边界问题
     * 明确变量（边界）的实际意义
     * 循环不变量（循环过程中，不变的因素是什么）
     * 3、
     *
     * @param arr
     * @param target
     */
    public int binarySearch(int[] arr, int target) {
        int len = arr.length;
        // l,r 为要查找区域的边界
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target > arr[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 剑指Offer 51：数组中的你序对 【困难】
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数
     *
     * 解题思路：归并排序的衍生问题
     * 1、对归并排序对合并过程进行改造，记录逆序对的个数
     * 2、我们需要利用合并的时候，左右两个子区间是排好序的性质，所以排序这个动作要保留
     * 3、在寻找当前位置的正确元素的时候，可以同时计算出这个所谓的元素的当前逆序对
     * 4、如果左侧区间所指元素比右侧区间所指元素大，那么当前元素跟右侧区间指向元素以后的元素所有元素构成逆序，所以该元素产生 right-r+1 个逆序对
     * 5、反之不构成逆序对
     * @param args
     */
    public int reversePairs(int[] nums){
        return reversePairs(nums, 0, nums.length - 1);
    }

    private int reversePairs(int[] nums, int left, int right){
        if (left >= right){
            return 0;
        }
        int mid = left + (right - left)/2;
        int sum1 = reversePairs(nums, left, mid);
        int sum2 = reversePairs(nums, mid + 1, right);
        return merge(nums, left, mid, right, sum1+sum2);
    }
    private int merge(int[] nums, int left, int mid, int right, int counts) {
        // 遍历左侧子数组的指针
        int l = left;
        // 遍历右侧子数组的指针
        int r = mid+1;
        // 逆序对计数器: counts

        // 构造一个额外数组，来辅助对两个子数组的排序
        int[] aux = new int[right - left + 1];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = nums[left + i];
        }
        // 遍历要合并的数组区间[left, right] (这里不需要创造一个额外数组接受)
        for (int k = left; k <= right; k++) {
            if (l > mid){  // 只剩右侧区间的时候,此时该区间都是顺序的，没有逆序对
                nums[k] = aux[r - left];
                r++;
            } else if (r > right){  // 只剩左侧区间的时候，此时该区间是顺序对，没有逆序对
                nums[k] = aux[l -left];
                l++;
            } else{
                if (aux[l-left] <= aux[r-left]){
                    nums[k] = aux[l - left];
                    l++;
                } else {
                    nums[k] = aux[r - left];
                    counts += mid-l+1;
                    r++;
                }
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        Solutions slt = new Solutions();
        int[] testArr = new int[]{2, 0, 2, 1, 1, 0};
        slt.sortColors(testArr);

        int[] nums1 = new int[]{2,4,3,5,1};
        int[] nums2 = new int[]{1};
        System.out.println(slt.reversePairs(nums1));
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();
    }
}

package algorithms.data_sturcture_wise.array;

import algorithms.basic_alg.SortAlgorithm;
import com.sun.codemodel.internal.JStringLiteral;

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
        int k = 0;  // 初始非零区间为[0,0)
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
                swap(nums, i, two);
//                i++; // 此时是不需要移动 i 的，它会继续判断是否为零的情况
            } else {
                zeros++;
                swap(nums, i, zeros);
                i++;
            }
        }
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

    /**
     * leetCode 215: 数组中的第K个最大元素 【中等】
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 解题思路：
     * 1、思路一：先快排，再去第k个最大值，复杂度为 O(nlogn)
     * 2、思路二：利用快排 partition 操作的性质：O(n)
     * 2.1 在寻找基准点的时候，基准点的合适位置，就是该元素最终排好序后的索引位置(每次递归找pivot的时候，已经找到的pivot的位置是不会变的)
     * 2.2 取出第 K 个最大元素就是取第 K 个（或者 arr.length-k 的位置）索引处的位置
     * 2.3 所以，我们可以调用多次partition操作，在找到pivot的合适位置时候判断pivot的索引是否等于 K
     * 2.4 如果 pivot 比k小，就只在左区间找就好了，否则就只在有区间找
     */
    public int findKthLargest2(int[] nums, int k) {
        // 快排方式
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        sortAlgorithm.quickSort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1, k);
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int pivot = partition2(nums, left, right);
        // 这里添加上使用用第二种思路的代码
        if (pivot == nums.length - k) {
            return;
        }
        quickSort(nums, left, pivot - 1, k);
        quickSort(nums, pivot + 1, right, k);

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

    /**
     * leetCode 167: 两数之和II - 输入有序数组 【简单】
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * <p>
     * 示例 1：
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * <p>
     * 示例 2：
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     * <p>
     * 示例 3：
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     * <p>
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
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int curSum = numbers[left] + numbers[right];
            if (curSum < target) {
                left++;
            } else if (curSum > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return null;
    }


    /**
     * LeetCode 15: 三数之和 【中等】
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 解题思路：
     *  1、先对数组排序：因为要求返回结果不重复，所以不能暴力扫描，排序后方便判断是否重复
     *  2、扫描数组，先固定第一个元素，
     *  3、然后使用双向指针来遍历剩余元素，判断是否满足条件
     *      如果剩余元素的和比固定元素的相反数大，需要向前移动右侧指针
     *      如果剩余元素的和比固定元素的相反数小，需要向后移动左侧指针
     *      否则，满足条件，同时相向移动两个指针
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 扫描数组，固定第一个元素，然后判断剩余元素
        for (int first = 0; first < n; ++first) {
            // 排除重复情况：如果下个元素和上个元素重复，剩余结果中也会有重复，所以遍历两个重复结果没区别
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // 初始化两个指针
            int second = first + 1;
            int third = n - 1;
            // 三数之和，固定第一个元素后，剩余元素的和
            int target = -nums[first];
            // 枚举 b
            while (second < third) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    second++;
                    continue;
                }
                // 这里不是用if判断，而是while循环，因为可以能有重复，需要多次判断
                while (nums[second] + nums[third] > target) {
                    third--;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    res.add(list);
                    second++;
                    third--;
                }
            }
        }
        return res;
    }


    /**
     * leetCode 125: 验证回文串 【简单】（回文串就是正读和反渎结果一样）
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 解释："amanaplanacanalpanama" 是回文串
     *
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     * 解释："raceacar" 不是回文串
     *
     * 解题思路：
     *  思路一：将字符串反转，看字符串是否相等
     *  思路二：双指针，判断左右两个指针指向的字符是否相等
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s==null || s.length()<=0) return true;
        // 因为不区分大小写，所以先将 s 转为小写
        s = s.toLowerCase();
        int left = 0, right = s.length()-1;
        while (left < right){
            // 跳过非字符和数字的字符(注意，这里不是一个if判断，而是一个循环，一定要跳过所有非法字符，if判断只能跳过一个)
            // 而且这里也必须做边界判断，否则会有越界情况
            while (left<right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left<right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            // 还得判断一次 left<right ，因为上面如果发生移动，left<right 就不一定成立了
            if (left <right && s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /** leetCode 344: 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符
     *
     * 示例 1：
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     *
     * 解题思路：碰撞指针
     * @param s
     */
    public void reverseString(char[] s) {
        if(s==null || s.length == 0){
            throw new IllegalArgumentException("");
        }
//        int head = 0, tail = s.length-1;
//        while (head < tail){
//            char tmp = s[head];
//            s[head] = s[tail];
//            s[tail] = tmp;
//            head++;
//            tail--;
//        }
        for(int head=0, tail=s.length-1; head<tail; head++,tail--){
            char tmp = s[head];
            s[head] = s[tail];
            s[tail] = tmp;
        }
    }

    // TODO
    // leetCode 345:
    // leetCode 11:

    /**
     * LeetCode 541：反转字符串II 【简单】
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     *
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     *
     * 解题思路：界定好边界，移动指针
     *  1、两个指针，维护一个长度为k的区域
     *  2、对区域内元素反转
     *  3、指针要每次移动 2k 个
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        reverseArr(arr, k);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            res.append(arr[i]);
        }
        return res.toString();
    }
    private void reverseArr(char[] arr, int k){
        if (arr == null || arr.length <= 0) {
            throw new IllegalArgumentException("");
        }
        int a = 0, b = k - 1;
        int len = arr.length;

        // 只要左边界没有到头，就还有剩余元素，就需反转
        while (a < len) {
            // 考虑特殊情况：剩余不足k个时，要反转对区间有变化
            if (b > len - 1) {
                reverse(arr, a, len-1);
                break;
            }
            reverse(arr, a, b);
            // 每隔 2k 个移动指针
            a += 2 * k;
            b += 2 * k;
        }
    }
    private void reverse(char[] arrary, int start, int end) {
        while (start < end) {
            char temp = arrary[start];
            arrary[start] = arrary[end];
            arrary[end] = temp;

            start++;
            end--;
        }
    }

    /**
     * LeetCode 557：反转字符串中的单词III 【简单】
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 示例：
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *
     * 解题思路：双指针
     * 1、用 [l, right) 区间表示一个单词，当right 指向空格或者数组结尾时停止
     * 2、将这个区间中的字符反转
     * 3、左指针移动到 ringt+1 处，右指针移动到 right+1 处
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length()<=0) return null;
        char[] strChars = s.toCharArray();
        int left=0, right =0;

        while (right<=s.length()){
            if (right == s.length() || strChars[right]==' '){
                reverse(strChars, left, right-1);
                left = right+1;
                right = right +1;
            } else {
                right++;
            }
        }
        return new String(strChars);
    }

    /**
     * leetCode 209: 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * <p>
     * 示例：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * <p>
     * 解题思路：双指针——滑动窗口
     * 1、注意边界问题
     *
     * @param target
     * @param nums
     * @return
     */

    public int minSubArrayLen(int target, int[] nums) {
        int windowStart = 0;
        int windowEnd = -1;
        int sum = 0;
        int res = nums.length + 1;
        while (windowStart < nums.length) {
            // 先确定当前窗口
            if (sum < target && windowEnd + 1 < nums.length) {
                windowEnd++;
                sum += nums[windowEnd];
            } else {
                sum -= nums[windowStart];
                windowStart++;
            }
            // 然后判断该窗口是否满足条件
            if (sum >= target) {
                res = res < (windowEnd - windowStart + 1) ? res : (windowEnd - windowStart + 1);
            }
        }
        // 注意判断没有最小长度的时候
        if (res == nums.length + 1) return 0;
        return res;
    }

    /**
     * leetCode 3: 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
     * <p>
     * 示例1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 解题思路：双指针——滑动窗口
     * 1、需要借助一个记录所有ASCII码字符是否出现的数组，数组每个索引表示该字符
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int left = 0, right = -1;
        int res = 0;
        while (left < s.length()) {
            // s的访问索引传入的是字符类型，当作整数类型用的
            if (right + 1 < s.length() && freq[s.charAt(right + 1)] == 0) {
                right++;
                // 如果字符出现就将记录字符串出现次数的数组相应索引赋值为 1， 否则为 0；
                freq[s.charAt(right)] = 1;
            } else {
                freq[s.charAt(left)] = 0;
                left++;
            }
            res = res > (right - left + 1) ? res : (right - left + 1);
        }
        return res;
    }
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
     * <p>
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
     * 剑指Offer 51：数组中的你序对 【困难】
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数
     * <p>
     * 解题思路：归并排序的衍生问题
     * 1、对归并排序对合并过程进行改造，记录逆序对的个数
     * 2、我们需要利用合并的时候，左右两个子区间是排好序的性质，所以排序这个动作要保留
     * 3、在寻找当前位置的正确元素的时候，可以同时计算出这个所谓的元素的当前逆序对
     * 4、如果左侧区间所指元素比右侧区间所指元素大，那么当前元素跟右侧区间指向元素以后的元素所有元素构成逆序，所以该元素产生 right-r+1 个逆序对
     * 5、反之不构成逆序对
     *
     * @param nums
     */
    public int reversePairs(int[] nums) {
        return reversePairs(nums, 0, nums.length - 1);
    }

    private int reversePairs(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int sum1 = reversePairs(nums, left, mid);
        int sum2 = reversePairs(nums, mid + 1, right);
        return merge(nums, left, mid, right, sum1 + sum2);
    }

    private int merge(int[] nums, int left, int mid, int right, int counts) {
        // 遍历左侧子数组的指针
        int l = left;
        // 遍历右侧子数组的指针
        int r = mid + 1;
        // 逆序对计数器: counts

        // 构造一个额外数组，来辅助对两个子数组的排序
        int[] aux = new int[right - left + 1];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = nums[left + i];
        }
        // 遍历要合并的数组区间[left, right] (这里不需要创造一个额外数组接受)
        for (int k = left; k <= right; k++) {
            if (l > mid) {  // 只剩右侧区间的时候,此时该区间都是顺序的，没有逆序对
                nums[k] = aux[r - left];
                r++;
            } else if (r > right) {  // 只剩左侧区间的时候，此时该区间是顺序对，没有逆序对
                nums[k] = aux[l - left];
                l++;
            } else {
                if (aux[l - left] <= aux[r - left]) {
                    nums[k] = aux[l - left];
                    l++;
                } else {
                    nums[k] = aux[r - left];
                    counts += mid - l + 1;
                    r++;
                }
            }
        }
        return counts;
    }


    /**
     * LeetCode 1572: 矩阵对角线元素的和 【简单】
     * <p>
     * 解题思路：
     * 1、确定好边界
     * 2、主对角线元素为 mat[i][i]
     * 3、副对角线元素为 mat[i][j]
     * 4、注意交叉位置的处理
     *
     * @param mat
     */
    public int diagonalSum(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int sum = 0;
        for (int i = 0, j = cols - 1; i < rows && j >= 0; i++, j--) {
            if (i == j) {
                sum += mat[i][i];
            } else {
                sum += mat[i][i] + mat[i][j];
            }
        }
        return sum;
    }

    /**
     * LeetCode 74：搜索二维矩阵（搜索矩阵中的元素）
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * <p>
     * 解题思路：
     * 思路一：根据给定矩阵的性质，将矩阵中元素按行拼接后是有序的，所以可以使用二分查找
     * 思路二：对每行（列）进行二分搜索
     * 1、从第一列开始，对当前列进行二分搜索，直到剩下一个元素
     * 2、如果该元素是要找当元素就返回true，否则就比较要找的元素与当前元素大小
     * 3、如果大，就对该元素所在行进行二分搜索，否则对该元素所在行对上一行进行二分搜索
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int rows = matrix.length;
        int columns = matrix[0].length;
        // 将矩阵中的元素想象为平铺在一个数组中，根据数组位置推断该元素在矩阵中的位置
        int left = 0, right = rows * columns - 1;
        while (left <= right) {
            // 计算当前元素在 [left,right] 中的索引
            int mid = left + (right - left) / 2;
            // 推断 mid 在矩阵中的索引：矩阵中行号就是 mid 与每行元素个数的商
            int curRow = mid / columns;
            // 矩阵中列号就是 mid 与每行元素个数的模
            int curCol = mid % columns;
            if (target == matrix[curRow][curCol]) {
                return true;
            } else if (target < matrix[curRow][curCol]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }


    /**
     * LeetCode 240：搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <p>
     * 解题思路：利用 matrix 的性质
     * 思路一：以矩阵的右上角（或者左下角）为起点
     * 1、从右上角开始，如果要查找元素比当前元素大，则向该元素的下一行移动（该元素左边都比他小）
     * 2、如果要查找元素比该元素小，就像该元素的左边一列移动（因为该元素下面的元素都比他大）
     * 3、直到找到该元素
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix == null) {
            return false;
        }
        // 初始化当前位置
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            int cur = matrix[row][col];
            if (target == cur) {
                return true;
            } else if (target > cur) {
                row++;
            } else if (target < cur) {
                col--;
            }
        }
        return false;
    }


    /**
     * 剑指Offer 29：顺时针打印数组
     * <p>
     * 解题思路：
     * 1、界定好边界
     * 2、按照 从左到右、从上到下、从右到左、从下到上 的顺序打印
     * 3、每扫过一行或者一列数据，相应边界进行缩减 1
     * 4、直到任意一个边界有交叉
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int count = 0;
        // 初始矩阵的四个化边界：上边界、下边界、左边界、右边界
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;

        while (true) {
            // step1:从左到右打印当前行的所有元素，即 [left,right] 这个区间
            if (left > right) break;
            for (int i = left; i <= right; i++) {
                res[count] = matrix[top][i];
                count++;
            }
            // 从左向右打印完一行，我们将上边界 top 指针下移一位
            top++;

            // step2：从上到下打印当前列的所有元素，即 [top,bottom] 这个区间
            if (top > bottom) break;
            for (int j = top; j <= bottom; j++) {
                res[count] = matrix[j][right];
                count++;
            }
            // 从上到下打印完，右侧边界 right 向左移动一位
            right--;

            // step3：从右到左打印当前行的所有元素，即 [right,left] 这个区间
            if (right < left) break;
            for (int k = right; k >= left; k--) {
                res[count] = matrix[bottom][k];
                count++;
            }
            // 从右到左打印完，底部边界 bottom 向上移动一位
            bottom--;

            // step4：从下到上打印当前列的所有元素，即 [bottom,top] 这个区间
            if (bottom < top) break;
            for (int m = bottom; m >= top; m--) {
                res[count] = matrix[m][left];
                count++;
            }
            // 从下到上打印完，左侧边界 left 向右移动一位
            left++;
        }
        return res;
    }

    /**
     * LeetCode 189: 反转数值 【中等】
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例：
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * <p>
     * 解题思路：右移k个位置，本质是将数组后k个元素放到数组前面
     * 1、先将数组进行反转
     * 2、确定要移动元素个数k
     * 3、将 [0,k-1] 这个区间的元素反转，回到原来数组的顺序状态
     * 4、将 [k,size-1] 这个区间的元素反转，回到原来数组的顺序状态
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || k == 0) return;
        reverseArr(nums, 0, nums.length - 1);
        k %= nums.length;

        reverseArr(nums, 0, k - 1);
        reverseArr(nums, k, nums.length - 1);
    }

    private void reverseArr(int[] arr, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("illegal argument");
        }
        for (int i = start, k = end; i < k; i++, k--) {
            swap(arr, i, k);
        }
    }


    public static void main(String[] args) {
        Solutions slt = new Solutions();
        int[] testArr = new int[]{2, 0, 2, 1, 1, 0};
        slt.sortColors(testArr);

        int[] nums1 = new int[]{2, 4, 3, 5, 1};
        int[] nums2 = new int[]{1};
        System.out.println(slt.reversePairs(nums1));
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();
        slt.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});

        // test matrix
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6},{7,8,9}};
        slt.searchMatrixII(matrix, 20);
        int[] printMatrix = slt.spiralOrder(matrix2);
        for (int i = 0; i < printMatrix.length; i++) {
            System.out.print(printMatrix[i] + " ");
        }
        System.out.println("\n end");

        String testStr = "A man, a plan, a canal: Panama";
        System.out.println(slt.isPalindrome(testStr));
    }


    /**
     * 如何写出一个正确的程序：我们以二分查找为例
     * 1、想出正确的算法思路、逻辑
     * 2、正确考虑到所有边界问题
     * 明确变量（边界）的实际意义
     * 循环不变量（循环过程中，不变的因素是什么）
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
}

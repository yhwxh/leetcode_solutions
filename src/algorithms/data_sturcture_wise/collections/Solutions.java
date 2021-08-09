package algorithms.data_sturcture_wise.collections;

import java.util.*;

public class Solutions {
    /**
     * LeetCode 804: 唯一的摩尔斯密码词
     * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
     * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * <p>
     * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作单词翻译。
     * 返回我们可以获得所有词不同单词翻译的数量。
     * <p>
     * 例如:
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * 输出: 2
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * <p>
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     * <p>
     * 解题思路：需要有个辅助数组来获取每个字符对应的密码
     * 1、遍历每个字符串
     * 2、遍历每个字符串的每个字符，并将字符转为摩尔斯密码并拼接起来
     * 3、将每个字符串的转化后的密码放入set ，返回 set 的大小
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] codeArray = new String[]{
                ".-", "-...", "-.-.", "-..", ".",
                "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---",
                ".--.", "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-", "-.--", "--.."
        };
        // 基于红黑树的Set（平衡二叉树）
//        Set<String> res = new TreeSet<>();
        // 使用哈希set，让结果更快,因为基于hash表的Set，增删查会更快（O(1)）
        Set<String> res = new HashSet<>();
        for (String word : words) {  // 遍历每个单词
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                // 这里巧妙的用字符相对a的偏移，找到了字符对应的密码在密码数组里的索引
                sb.append(codeArray[word.charAt(i) - 'a']);  // 将单词中每个字符的密码拼接出来
            }
            res.add(sb.toString());
        }
        return res.size();
    }

    /**
     * LeetCode 1： 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> record = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            if(record.containsKey(target - nums[i])){
                return new int[]{record.get(target-nums[i]), i};
            }
            record.put(nums[i], i);
        }
        return null;
    }

    /**
     * LeetCode 454: 四数之和II 【中等】
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                record.put(nums3[i] + nums4[j],record.get(nums3[i] + nums4[j]+1));
            }
        }
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (record.containsKey(0-nums1[i]-nums2[j])){
                    res += record.get(0-nums1[i]-nums2[j]);
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 447：回旋镖数量 【中等】
     * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
     * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     * 返回平面上所有回旋镖的数量。
     *
     * 示例 1：
     * 输入：points = [[0,0],[1,0],[2,0]]
     * 输出：2
     * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     *
     * 解题思路：
     *  1、计算每两个点之间点距离
     *  2、使用map将距离和距离出现点次数保存下来
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length<3) return 0;
        int res = 0;
        // 遍历每个数据点
        for (int i = 0; i < points.length; i++) {
            // 给每个数据点创建一个map，来装该点与其他点点距离和个数
            Map<Integer, Integer> record = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if(i==j)continue;
                int dist = distance(points[i], points[j]);
                if (record.containsKey(dist))
                    record.put(dist, record.get(dist)+1);
                else
                    record.put(dist, 1);
            }
            // 记录了每个数据点和其他点距离个数后，遍历map，查看有多少满足条件点数组
            for (Map.Entry<Integer, Integer> e: record.entrySet()) {
                // 当某个距离下的数据点个数大于等于2的时候才满足条件
                if (e.getValue() >= 2){
                    // 因为要考虑元组间的顺序，所以满足条件的个数为一个A(n,m)的排列
                    res += e.getValue()*(e.getValue()-1);
                }
            }
        }
        return res;
    }

    private int distance(int[] a, int[] b) {
        // 严格讲，距离需要开平方，但是开平方会带来浮点数，由于误差点存在，在Map中用浮点数作为key是不安全的，所以可以不对其开平方处理
        return (int)(Math.pow(a[0]-b[0],2) + Math.pow(a[1]-b[1],2));
    }

    /**
     * LeetCode 242: 有效的字母异位词 【简单】
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     *
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     *
     * 解题思路：
     *  思路一：借助map
     *      1、用一个map 记录其中一个字符串中字符的出现次数
     *      2、遍历另一个字符，判断每个字符跟map中的字符次数是否抵消
     *      3、如果都抵消，就满足条件，否则不满足
     *  思路二：借助一个记录所有字母的数组
     *      1、因为两个字符串满足条件的话，两个字符串的长度必须相等，所以可以将两个字符串放到一个循环遍历
     *      2、遍历字符串，将一个字符串中出现字符的出现记录下来，出现一次增加一次次数，将另一个字符串出现的字符去消减原来的记录
     *      3、如果满足条件，最终数组中记录的次数都是0，否则不满足条件
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t){
        if (s == null || t == null || s.length() != t.length())
            return false;
        // 记录26个小写英文字母，下标表示所有字符，相应元素表示该字符在字符串中出现次数
        int[] records = new int[26];
        for (int i = 0; i < s.length(); i++){
            // 用其中一个字符串中的字符，来记录字符出现次数
            records[s.charAt(i) - 'a']++;  // 这里使用了每个字符相对'a'的偏移作为索引
            // 用另一个字符串中的字符，来消减字符的出现次数
            records[t.charAt(i) - 'a']--;
        }
        // 检查所有字符的出现次数是否完全被消除
        for (int j = 0; j < 26; j++) {
            if (records[j] > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;
        Map<Character, Integer> record = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (record.containsKey(cur)) {
                record.put(cur, record.get(cur) + 1);
            } else {
                record.put(cur, 1);
            }
        }
        for (int j = 0; j < t.length(); j++) {
            char curJ = t.charAt(j);
            if (!record.containsKey(curJ)) {
                return false;
            }
            record.put(curJ, record.get(curJ) - 1);
            if (record.get(curJ) == 0)
                record.remove(curJ);
        }
        return record.isEmpty();
    }

    /**
     * LeetCode 219：存在重复元素II 【简单】
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
     * 并且 i 和 j 的差的 绝对值 至多为 k。
     *
     * 示例1:
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     *
     * 示例 2:
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     *
     * 示例 3:
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     *
     * 解题思路：该问题等价于在固定长度为k的窗口中，是否存在两个元素的值相等
     *  1、我们需要将窗口外的元素去一个地方查找有没有，所以这里需要一个 set 来记录窗口中的元素，来辅助查找
     *  2、当 Set 中的元素个数小于k的时候就找到了满足条件的元素，那么必定在窗口内也满足
     *  3、如果 Set 的元素个数大于k，还没找到，就向后移动一下窗口继续判断
     *  4、这里不需要在数组上定义指针来维护窗口，维护Set的大小就是我们要的窗口大小
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> record = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (record.contains(cur)){
                return true;
            } else {
                record.add(cur);
                // 维护 set的大小，始终让他保持为 k
                if (record.size()>k){
                    // 删除窗口最左侧的数据
                    record.remove(nums[i-k]);
                }
            }
        }
        return false;
    }

    /**
     * LeetCode 220：存在重复元素III 【中等】
     * 给你一个整数数组 nums 和两个整数 k 和 t 。
     * 请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
     * 如果存在则返回 true，不存在返回 false。
     *
     * 示例1：
     * 输入：nums = [1,2,3,1], k = 3, t = 0
     * 输出：true
     *
     * 示例 2：
     * 输入：nums = [1,0,1,1], k = 1, t = 2
     * 输出：true
     *
     * 示例 3：
     * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出：false
     *
     * 解题思路：将问题等价转化一下，两个元素的差值不超过t，其实就是相当于在 [v-t, v+t] 这个区间找一个数
     *  1、满足条件的值必定在 [v-t, v+t] 这个区间里
     *  2、如果 treeSet 中的元素完全被 [v-t, v+t] 这个区间覆盖，那么肯定有满足条件的元素
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 这里需要Set有顺序，所以用treeSet
        TreeSet<Long> record = new TreeSet<>(  );
        for (int i = 0; i < nums.length; i++) {
            long cur = nums[i];
            // 当 treeSet 中的最大值存在，且不能比满足条件的 [v-t, v+t] 区间的最小值小
            Long ceiling = record.ceiling((long) nums[i] - (long) t);
            // record 中所有元素落在 [v-t, v+t] 这个区间里的时候，必定存在满足条件的元素
            if (ceiling!=null && ceiling <= cur+t){
                //
                return true;
            } else {
                record.add(cur);
                // 维护 set的大小，始终让他保持为 k
                if (record.size()>k){
                    // 删除窗口最左侧的数据
                    record.remove((long)nums[i-k]);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] test = {2, 7, 11, 15};
        Solutions solutions = new Solutions();
        int[] res = solutions.twoSum(test, 9);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[][] test2 = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
        System.out.println(solutions.numberOfBoomerangs(test2));
    }
}

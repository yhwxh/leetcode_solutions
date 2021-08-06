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
     * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
     * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
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
        // TODO
        if (points == null || points.length<3) return 0;
        for (int i = 0; i < points.length-1; i++) {
            for (int j = 1; j < points.length; j++) {
                int dist = points[0][0];
            }
        }
        return 0;
    }

    /**
     * LeetCode 242: 有效的字母异位词 【简单】
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     *
     * 示例 1:
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

    public static void main(String[] args) {
        int[] test = {2, 7, 11, 15};
        Solutions solutions = new Solutions();
        int[] res = solutions.twoSum(test, 9);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

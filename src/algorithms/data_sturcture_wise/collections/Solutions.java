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
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        // TODO
        return 0;
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

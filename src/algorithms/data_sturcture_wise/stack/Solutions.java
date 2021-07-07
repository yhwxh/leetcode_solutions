package algorithms.data_sturcture_wise.stack;

import java.util.Stack;

public class Solutions {
    /**
     * LeetCode 20: 有效括号 【简单】
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * 示例1：
     * 输入：s = "()"
     * 输出：true
     *
     * 示例2：
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * 示例3：
     * 输入：s = "(]"
     * 输出：false
     *
     * 示例4：
     * 输入：s = "([)]"
     * 输出：false
     *
     * 示例5：
     * 输入：s = "{[]}"
     * 输出：true
     *
     * 解题思路：
     *      1、遍历字符串
     *      2、当是做括号就压栈
     *      3、当是右括号当时候，就出栈，并判断栈顶元素是不是和当前右括号匹配当做括号
     * 总之，有效括号是：既满足左右对称配对，而且左右括号得数量必须一样（也就是每个括号都能满足对称配对）
     */

    // 利用栈的特性
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 当遇到字符是右括号的时候，栈中必须要有做括号才行，否则就不是有效括号
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        // 当所有左括号都压栈，右括号都弹栈，并有匹配的时候，最终还要看栈里是否有字符
        return stack.isEmpty();
    }
}

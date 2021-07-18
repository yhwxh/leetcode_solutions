package data_sturcture;//import java.util.Stack;

import data_sturcture.interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {
    // 基于动态数组的栈实现
    DynamicArray<E> data;

    public ArrayStack(int capacity) {
        data = new DynamicArray<>(capacity);
    }
    public ArrayStack() {
        data = new DynamicArray<>();
    }
    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void put(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: [");

        for (int i = 0; i < data.getSize(); i++){
            res.append(i);
            if (i != data.getSize() -1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        // 利用栈来检查括号匹配问题
        Solution solution = new Solution();
        System.out.println(solution.isValid("{([])}"));
    }
}

class Solution {
    public boolean isValid(String s) {
        // 主旨是：左括号就进栈，右括号就出栈
        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            } else {
                // 先判断是否为空
                if(stack.isEmpty()){
                    return false;
                }
                char topChar = stack.pop();
                if( c == ')' && topChar!='('){
                    return false;
                }
                if(c == ']' && topChar != '['){
                    return false;
                }
                if(c == '}' && topChar != '{'){
                    return false;
                }
            }
        }
        // 这里的返回方式比较难想到
        return stack.isEmpty();
    }
}
package algorithms.data_sturcture_wise.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 225: 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *  
 * 示例：
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *
 *
 * 解题思路：
 *  1、使用两个队列：一个用来装 栈 中所有元素； 一个用来向栈中栈中添加元素
 *  2、每次往添加元素的队列中加入一个元素，就要把栈中所有元素移动到该队列，此时，现在队列的元素保持了栈中元素的顺序
 *      这个动作保证了后加入的元素先出
 *  3、交换两个队列
 */

public class StackBasedQueue {
    // 用来装所有栈中的元素
    private Queue<Integer> queue1;
    // 用来完成 push 动作的辅助队列
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public StackBasedQueue() {
        queue1  = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        // step1：先把元素加入 queue2，此时该队列为空
        queue2.add(x);
        // 将 queue1 中的元素按顺序加入 queue2 中。该动作结束后queue1为空，queue2是添加元素后栈中所有的元素
        while (!queue1.isEmpty()){
            queue2.add(queue1.poll());
        }

        // 上面动作结束后queue1为空，queue2是添加元素后栈中所有的元素。所以要做一次交换，保持我们对两个队列的定义
        Queue<Integer> tmp = new LinkedList<>();
        tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        // 由于queue1中保存的是所有栈中的元素，所以拿出栈顶元素就是取 queue1 中队首的元素
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        // 由于queue1中保存的是所有栈中的元素，所以查看栈顶元素就是查看 queue1 中队首的元素
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        // 由于queue1中保存的是所有栈中的元素，所以判断栈是否为空，就是 queue1 是否为空
        return queue1.isEmpty();
    }
}

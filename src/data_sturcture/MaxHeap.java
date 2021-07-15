package data_sturcture;

import java.util.ArrayList;

/**
 * 堆，可以作为优先队列的基础数据结构
 * 堆其实也是一种树，一般基于二叉树实现，基于二叉树实现的堆也叫二叉堆
 * 二叉堆是一颗不一定是满二叉树，但是肯定是一颗完全二叉树
 * <p>
 * 完全二叉树：就是将所有节点一层一层放起来，每层从左到右的开始放，所有，完全二叉树不满的情况只能出现在右侧
 * <p>
 * 二叉堆性质：
 * 1、堆中某个节点的值总是小于等于其父节点的值（按此得到的堆叫最大堆）
 * 2、如果我们从根节点开始，按层序的顺序给每个节点从1～n编号，那么
 * 每个节点的左孩子节点的索引为父节点索引的2倍，即 left_child_index = parent_index * 2
 * 每个节点的右孩子节点的索引为父节点索引的2倍+1，即 right_child_index = parent_index * 2 + 1
 * 如果已知孩子节点的索引（无论是左节点还是右节点），那么父节点的索引为孩子节点的 1/2 ，即 parent_index = child_index/2
 * 这里巧妙用到了运算符 / 的特点，得到的是整数，左节点除以 2 正好是父节点，右节点为奇数，除以二也等于父节点索引
 * <p>
 * 3、如果是从 0 开始索引，那么索引关系如下：
 * parent_index = (i-1)/2
 * left_child_index = 2*i + 1
 * right_child_index = 2*i + 2
 *
 * @param <E>
 */

public class MaxHeap<E extends Comparable> {
    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 三个私有的辅助函数
    private int parent(int index) {
        /**
         * 返回完全二叉树的数组表示中，一个索引表示的元素的父节点的索引
         */
        if (index == 0) {
            throw new IllegalArgumentException("root doesn't have parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        /**
         * 返回完全二叉树的数组表示中，一个索引表示的元素的左子树节点的索引
         */
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        /**
         * 返回完全二叉树的数组表示中，一个索引表示的元素的右子树节点的索引
         */
        return 2 * index + 2;
    }

    // 向最大堆中添加元素
    public void add(E e) {

    }

    // 向最大堆中取出元素
    public E getMax(){
        return data.get(0);
    }

}

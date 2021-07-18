package data_sturcture;

import utils.Merger;

/**
 * 树结构：线段树（区间树），每个节点存储某个区间
 * 为什么使用线段树这种数据结构，它解决了什么样的问题
 * 如果我们关心的是一个线段（或者一个区间），那么线段树这个数据结构比较合适
 * 例如：区间染色问题：有一段墙，长度为n，对其中一段墙进行染色
 * 可以基于数组实现
 * 涉及的操作有：
 * 区间的状态是动态的，就是区间状态在不断更新，而不是确定不变的，操作原子是区间，而且区间大小不固定
 * 更新区间：O(n)
 * 查询区间: O(n)
 * <p>
 * 线段树的特点：
 * 线段树的每个节点存的是一段区间
 * 整体区间大小是固定的，所以不用考虑添加元素，而子区间大小是非固定的
 * 不一定是满二叉树，也不一定是完全二叉树，但它是平衡二叉树（所有叶子节点所在深度相差不超过1）
 * 堆（完全二叉树）也是平衡二叉树
 * <p>
 * 既然我们用数组来表示线段树，而线段树又不一定是完全二叉树（完全二叉树才能用数组表示，完全二叉树的层数和节点数有严格的关系），
 * 那怎么实现呢？
 * 当然是把线段树凑成完全二叉树啊，具体的就是将倒数第二曾的叶子节点的子节点补上
 * 那么具体补多少呢，也就是说，如果用数组来表示这个完全二叉树，这个数组大小是多少呢（需要多少节点）
 * 1、如果树是满二叉树的话，即 n=2^k 那么需要 2*n个节点
 * 2、如果不满的话，最后一层只有几个元素，这时，我们需要 4*n 个节点，多余的就是没满的那几个节点
 * 所以，最多我们需要 4*n 个节点存储这颗树，最坏情况下，有一半空间浪费，最好情况下，有两个空间浪费
 */
public class SegmentTree<E> {
    /**
     * 线段树不考虑添加动作，区间大小不会改变，改变的是区间中的元素，所以使用静态数组就可以
     * 去
     */
    // data用来保存原始数据，方便用户查询
    private E[] data;
    // tree 是专门用来维护树结构的数组，它相对真实的数组要有冗余，是拿空间花时间的做法
    private E[] tree;
    // 用户自定义的融合器
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        // 初始化线段树：线段树的空间是真是数组的4倍
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        /**
         * 在 treeIndex 的位置创建表示区间为 [l, r] 的线段树
         *
         * @param treeIndex：节点的索引
         * @param l: 该节点对应区间的起始索引
         * @param r：该节点对应区间的终止索引
         */
        // 递归终止条件为只有一个元素的时候
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        // 接下来是创建当前节点的左右子树
        // 当左右索引不相等时（元素个数不是一个的时候），该节点肯定是有左右孩子节点的
        int left = leftChild(treeIndex);  // 这里相对于树这种逻辑结构的索引
        int right = rightChild(treeIndex);

        // 当前区间的中间索引：注意这里是计算中间索引的标准解法（如果是 (l+r)/2 的话，当数值不较大的时候会有整数溢出的风险）
        // 这里是相对于数组这种物理结构的索引
        int mid = l + (r - l) / 2;  // l + r和l距离的二分之一，和 （l+r）/2 等价
        buildSegmentTree(left, l, mid);
        buildSegmentTree(right, mid + 1, r);

        // 定义当前节点融合方式，这个需要用户自定义
        tree[treeIndex] = merger.merge(tree[left], tree[right]);
    }

    // 线段树的查询操作
    public E get(int left, int right) {
        if (left < 0 || left >= data.length || right < 0 || right >= data.length || left > right) {
            throw new IllegalArgumentException("");
        }
        return get(0, 0, data.length - 1, left, right);
    }

    // 在以 treeIndex 为根.区间为 [l, r] 的线段树中，搜索区间[left, right] 的值
    private E get(int treeIndex, int l, int r, int left, int right) {
        /**
         * @param treeIndex: 当前线段树的根索引
         * @param l: 当前线段树节点覆盖区间的起始索引
         * @param r: 当前线段树节点覆盖区间的结束索引
         * @param left: 用户要查询区间的起始索引
         * @param right: 用户要查询区间的结束索引
         */

        // 递归到底情况
        if (l == left && r == right){
            return tree[treeIndex];
        }

        // 子节点的索引和区间
        int mid = l + (r-l) /2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 分情况看
        if (left > mid){  // 查找区间完全不在左子树
            return get(rightTreeIndex, mid+1, r, left, right);
        } else if (right <= mid) {   // 查找区间完全不在右子树
            return get(leftTreeIndex, l, mid, left, right);
        }
        // 当要查找的区间同时分布在左右子树
        E leftSegment = get(leftTreeIndex, l, mid, left, mid);
        E rightSegment = get(rightTreeIndex, mid+1, r, mid+1, right);
        return merger.merge(leftSegment, rightSegment);
    }

    // 修改线段树中某个索引的值
    public void set(int index, E e){
        if (index < 0 || index > data.length){
            throw new IllegalArgumentException("");
        }
        // 修改原始数组的元素
        data[index] = e;
        // 修改线段树
        set(0, 0, data.length -1, index, e);
    }
    private void set(int treeIndex, int l, int r, int index, E e){
        /**
         * 对索引为 treeIndex 为根，存储区间为[l,r]的线段树中，索引为 index 的节点进行替换
         */
        if (l==r){
            tree[l] = e;
            return;
        }
        int mid = l + (r - l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        if (index <= mid){
            set(leftIndex, l, mid, index, e);
        } else {
            set(rightIndex, mid+1, r, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    public E get(int index) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }


    // 辅助函数（注意，线段树跟堆不一样，他不需要找父节点是谁）
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * LeetCode 303
     */
}

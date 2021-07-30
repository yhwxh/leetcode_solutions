package data_sturcture;

import java.util.ArrayList;

/**
 * 堆，可以作为优先队列的基础数据结构
 * 堆其实也是一种树（逻辑上是树，物理上不一定是树，我们只需要保证其物理性质即可），一般基于二叉树实现，基于二叉树实现的堆也叫二叉堆
 * 二叉堆是一颗不一定是满二叉树，但是肯定是一颗完全二叉树
 * <p>
 * 完全二叉树：就是将所有节点一层一层放起来，每层从左到右的开始放。所以，完全二叉树不满的情况只能出现在右侧
 * <p>
 * 二叉堆性质：
 * 1、堆中某个节点的值总是小于等于其父节点的值（按此得到的堆叫最大堆），也就是每个节点都比他的所有子节点大
 * 注意，每个节点只跟其父节点有关系，跟其他非父节点之间大小不能确定
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
        /**
         * 在堆中添加元素
         *      添加元素就是在堆的最底层的最后一个位置上添加，
         *      难点就是怎么维护堆的性质：保证每个节点都小于等于其父节点
         *      这个维护过程叫做 sift up（上浮）
         * 由于堆堆性质，在上浮过程中，新加入的节点要不要上浮，只需要跟它的父节点进行比较就可以（只需保证其和父节点的关系，不用考虑其他节点的关系）
         * 直到堆堆性质满足停止
         */
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int index) {
        /**
         * 对传入索引处的节点进行上浮操作
         *
         */
        // 这里需要用循环来达到上浮到满足堆性质为止
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
//            data.swap(index, parent(index));
            E tmp = data.get(index);
            data.set(index, data.get(parent(index)));
            data.set(parent(index), tmp);
            // 记得更新插入元素的索引
            index = parent(index);
        }
    }

    // 取出堆中最大元素
    public E extractMax() {
        /**
         * 从堆中取出元素：一般只取最大值
         *      一般我们的方法是：将堆中最后一个元素跟堆顶元素置换，然后将置换后的堆顶元素做 sift down 操作
         */
        E max = getMax();

        // 交换堆的最后一个元素和堆顶元素: 因为最后一个元素要删除，索引替换动作就不给最后一个元素重新赋值了，直接将最后一个元素赋值给堆顶元素就行
        data.set(0, data.get(data.size() - 1));
        // 删除最后一个元素
        data.remove(data.size() - 1);

        // 删除后，堆的性质被破坏，所以我们要维护一下堆堆性质：对堆顶元素做下沉操作
        siftDown(0);
        return max;
    }

    private void siftDown(int index) {
        /**
         * 堆的下沉操作是维护堆性质堆基本操作之一，
         * 基本操作在维护堆堆性质的时候，可以实现最大（小）值的动态调整
         */
        // 这里处处考虑到了堆的性质：判断条件是左孩子节点的索引，因为如果左节点都到了最大索引，根据完全二叉树堆性质，肯定不会有后面的节点
        while (leftChild(index) < data.size()) {  // 此条件就是索引为叶子节点的时候的表达
            int maxValIndex = leftChild(index);  // 用来保存最大值的索引，初始化为左节点的索引
            // 此处 left + 1 就是右节点的索引
            if (maxValIndex+1<data.size() && data.get(maxValIndex + 1).compareTo(data.get(maxValIndex)) > 0){
                // 当有右节点，且右节点比左节点大的时候，更新当前最大值所在索引
                maxValIndex = rightChild(index);
            }
            // 当找到后继节点中最大的节点后，跟当前节点比较，判断是否要下沉
            if (data.get(index).compareTo(data.get(maxValIndex)) >=0){
                break;
            }
            E tmp = data.get(index);
            data.set(index, data.get(maxValIndex));
            data.set(maxValIndex, tmp);
            index = maxValIndex;
        }
    }

    // 查看堆中最大元素
    public E getMax() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("");
        }
        return data.get(0);
    }

    public MaxHeap(E[] arr){
        /**
         *
         * heapify 操作：将任意一个数组，整理成二叉树的形状
         * 思路就是：此思路是 O(n) 复杂度，如果将元素一个一个的插入空堆，复杂度是 O(nlogn)
         *  1、将当前这个任意数组看成一棵完全二叉树，此时它虽然是完全二叉树，但是不一定满足最大堆性质
         *  2、然后就是维护最大堆的性质：
         *      2.1 从堆的最后一个非叶子节点开始遍历，判断其与子节点是否满足最大堆的性质
         *          之所以从最后一个非叶子节点开始，是因为堆的性质，我们只考虑父节点与子节点的关系，
         *          不用从叶子节点开始做起，这样能省去一半的节点遍历
         *      2.2 不满足的话进行 sift down 操作
         *
         * 完全二叉树中，最后一个非叶子节点对应的索引就是最后一个节点的父节点
         */
        data = new ArrayList<E>();
        for (int j = 0; j < arr.length; j++){
            data.add(arr[j]);
        }
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public E replace(E e){
        /**
         * 取出最大元素，然后再放入一个新的元素
         * 直观上，我们可以先 extractMax 取出最大元素，然后在 add 一个元素，调用两次 O(logn)的操作
         * 但是，由于replace操作让堆的大小没有发生变化，我们可以这样：
         *  将堆顶的元素替换为新元素，然后对新元素进行 siftDown操作，这样就是一次 O(logn)
         */
        E max = getMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }
}

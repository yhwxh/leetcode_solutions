package data_sturcture;

/**
 * 基于 BST 的 Set 时间复杂度分析
 * @param <E>
 */

public class BSTSet<E extends Comparable> {
    private BinarySearchTree<E> data;
    private int size;

    public void add(E e){
        data.add(e);
    }

    public void remove(E e){
        data.remove(e);
    }

    boolean contains(E e){
       return data.contains(e);
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}

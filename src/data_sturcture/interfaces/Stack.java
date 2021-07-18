package data_sturcture.interfaces;

public interface Stack<E> {
    // 使用接口的形式是为了使用多态这个特性，而直接写一个类是不能达到这种目的的
    public int getSize();
    public boolean isEmpty();

    public void put(E e);
    public E pop();
    public E peek();
}

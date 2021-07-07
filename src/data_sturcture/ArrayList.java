package data_sturcture;

public interface ArrayList<E> {
    public void add(E e);
    public E remove(E e);
    
    public void set(int index, E e);
    public E get(int index);

    public boolean contains(E e);
    public int search(E e);
}

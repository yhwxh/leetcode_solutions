package data_sturcture;

/**
 * 私有队列接口（自己设计的）
 * @param <E>
 */
public interface QueuePrivate<E> {
    public void enqueue(E e);

    public E dequeue();

    public E getFront();

    public int getSize();

    public boolean isEmpty();
}

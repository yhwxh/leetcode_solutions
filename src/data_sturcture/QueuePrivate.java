package data_sturcture;

/**
 * 私有队列接口（自己设计的）
 * @param <E>
 */
public interface QueuePrivate<E> {
    void enqueue(E e);

    E dequeue();

    E getFront();

    int getSize();

    boolean isEmpty();
}

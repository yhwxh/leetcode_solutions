package data_sturcture;

public class DynamicArray<E> {

    private E[] data;
    public int size;

    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public DynamicArray() {
        this(20);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal argument");
        }
        // 扩容：当列表中元素数量达到容量大小时
        if (size == data.length) {
            resize(getCapacity() * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
    public void addLast(E e){
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal argument");
        }
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        // 采用lazy的方式代替Eager的方式，防止复杂度震荡
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            // 当容量为1/4的时候再去缩容，而不是在容量为1/2的时候
            resize(getCapacity() / 2);
        }
        return null;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal argument");
        }
        data[index] = e;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return data[index];
    }

    public E getFirst(){
        return get(0);
    }
    public E getLast(){
        return get(size-1);
    }

    public boolean contains(E e) {
        for (E val : data) {
            if (e.equals(val)) {
                return true;
            }
        }
        return false;
    }

    public int search(E e) {
        for (int i = 0; i < getSize(); i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicArray arr = new DynamicArray();
        for (int i = 0; i < 10; i++) {
            arr.add(0, i);
            System.out.println(arr.toString());
            if (i % 3 == 0) {
                arr.remove(1);
                System.out.println(arr.toString());
            }
        }
    }
}

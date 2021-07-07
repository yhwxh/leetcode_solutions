package data_sturcture;

public class ArrayListImplement<E> implements ArrayList<E> {

    private E[] data;
    public int size;

    public ArrayListImplement(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }
    public ArrayListImplement(){
        this(20);
    }


    @Override
    public void add(E e) {

    }

    @Override
    public E remove(E e) {
        return null;
    }

    @Override
    public void set(int index, E e) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public int search(E e) {
        return 0;
    }

    private void resize(int newCapacity){

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }
}

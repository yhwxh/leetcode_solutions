package data_sturcture.interfaces;

public interface UF {
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
    int getSize();
}

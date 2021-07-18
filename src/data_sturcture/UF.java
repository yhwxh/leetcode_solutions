package data_sturcture;

public interface UF {
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
    int getSize();
}

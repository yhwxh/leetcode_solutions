package data_sturcture;

import java.util.Arrays;

public class UnionFind implements UF {
    /** quick find 实现方案，基于数组
     * 并查集：Union Find：
     *      一种由孩子指向父亲的数据结构，它可以高效的解决这样一类问题：连接问题（Connectivity Problem）
     *      比如，它可以快速判断一个网络（比如社交网络）中节点间连接的状态
     *      另外，它是数学中集合类的实现（并查集的并，就是集合概念上的并）
     *
     * 我们不需要知道并查集中的每个元素的基本数据是什么（这个由具体业务决定），我们只需要给每个元素一个编号id就可以
     * 而每个元素存储的是其数据所属集合的编号，所以，对于 isConnected 操作，如果查找值的id下编号一样，那么两者就属于同一个集合
     */
    // 存储所有元素对应的集合编号，数组中每个元素代表一个集合编号，数组的索引表示真实元素的索引
    private int[] ids;

    public UnionFind(int size) {
        ids = new int[size];
        // 初始的时候，每个元素所属的集合编号都不一样，他们都是独立的，当进行union的时候才可能合并为同一个集合
        for (int i=0; i<size; i++) {
            ids[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        /**
         * 所谓的 union 操作就是将需要合并的两个元素对应的集合id同质化
         * 具体同质化成两个元素中哪一个对应的集合id都无所谓，只要同质化来就行
         * 而且，一旦两个元素对应集合同质化，原来各自所属的集合中所有元素对应的集合ID都要同时改变
         * 所以，我们需要遍历整个数组来完成
         */
        if (find(p) == find(q)){
            return;
        }
        for (int i = 0; i < ids.length; i++){
            // 判断第i个元素对应的集合ID是否和q对应的集合ID一样
            if (ids[i] == find(q)){  // 这里判断是否和q对应的集合id一样也可以，只要同质化就行
                ids[i] = find(p);
            }
        }

    }

    @Override
    public int getSize() {
        return ids.length;
    }

    private int find(int p){
        /**
         * 查找元素 p 对应元素的集合编号
         */
        if (p < 0 || p >= ids.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        return ids[p];
    }

}

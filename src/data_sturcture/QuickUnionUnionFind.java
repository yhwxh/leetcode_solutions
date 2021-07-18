package data_sturcture;

import data_sturcture.interfaces.UF;

public class QuickUnionUnionFind implements UF {
    /**
     * 并查集的通用解决方案：quick union 方案
     * 基于树（是逻辑上的树，严格说是森林）的实现是这样的
     *      1、初始时，每个元素都指向自己
     *      2、合并时，一个元素所在的树的父节点指向另一个元素所在树的父节点
     *         父节点就是指向自己的节点
     *      3、每棵树中的节点都属于同一个集合，不同树上的节点属于不同的集合
     *         所以，这里没有显式的集合表示，一棵树就是一个不同的集合
     *         但是，我们一般将每个节点所在树的根节点作为集合的编码
     *      4、当两个元素遍历到自己所在树的根节点时，如果根节点相同，则属于同一集合，否则为不同集合
     */
    // 存储了每个元素指向下一个元素的指针
    private int[] parents;

    // 记录每棵树节点个数，以便根据在合并的时候根据树的大小决定怎么合并
    // sz[i] 表示以 i 为根节点的集合中元素个数
    private int[] sz;  // 由于并查集中有多棵树（森林），所以用一个数组来存放

    // 基于树size的优化也不一定是最优的，因为可能会将深度大的合并到深度小的树上，更合理的是按树的深度（rank）去优化
    private int[] rank;  // rank[i] 表示以 i 为根节点的树的深度

    public QuickUnionUnionFind(int size){
        parents = new int[size];
        // 初始时，sz的大小也是size，因为每个节点是一棵树
//        sz = new int[size];
        rank = new int[size];
        for (int i=0; i<size; i++) {
            // 初始每个元素指向自己
            parents[i] = i;
//            sz[i] = 1;
            rank[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        // 先看看 p 和 q 的跟节点是否一样
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }

        // 两个集合合并：让 p 的跟节点指向 q的跟节点
//        if (sz[pRoot] <= sz[qRoot]) {
        if (rank[pRoot] < rank[qRoot]) {
            // 让少的去指向多的，这样树的深度不会太大
            parents[pRoot] = qRoot;
//            sz[qRoot] += sz[pRoot];
        }else if (rank[pRoot] < rank[qRoot]){
            parents[qRoot] = parents[pRoot];
        } else { // 以上两种情况都不改变树的深度，所以不需要维护树的深度，当两个树深度相等的时候才改变树的深度
            parents[qRoot] = parents[pRoot];  // 这里谁挂谁上无所谓，主要是后面要维护树的深度
//            sz[pRoot] += sz[qRoot];
            rank[pRoot] += 1;
        }
    }

    @Override
    public int getSize() {
        return parents.length;
    }

    private int find(int p){
        /**
         * 查找元素p对应的集合编号
         * O(h)复杂度，h为树的高度
         */
        // 遍历 p 所在的树：如果p不是指向自己（跟节点）就继续遍历
        while (p != parents[p]){ // parent(p) 表示p的父节点
            // path compress 路径压缩：将树的高度降低，从而提升并查集的性能
            parents[p] = parents[parents[p]]; // 每find一次就会对树做一次压缩，方便后续的性能
            // 将指针p移动到p节点的父节点
            p = parents[p];
        }
        // 最后返回p所在树的跟节点（每棵树的跟节点可以表示该节点所属集合的编码）
        return p;
    }


}

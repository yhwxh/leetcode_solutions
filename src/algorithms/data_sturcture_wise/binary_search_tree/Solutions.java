package algorithms.data_sturcture_wise.binary_search_tree;

import java.util.*;

public class Solutions {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode(int[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("");
            }
            this.val = arr[0];
            TreeNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                if (i < cur.val) {
                    cur.left = new TreeNode(i);
                } else if (i > cur.val) {
                    cur.right = new TreeNode(i);
                }
            }
        }
    }
    /**
     * LeetCode 94: 二叉树的中序遍历 【简单】
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * <p>
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     * <p>
     * 输入：root = []
     * 输出：[]
     * <p>
     * 输入：root = [1]
     * 输出：[1]
     * <p>
     * 输入：root = [1,2]
     * 输出：[2,1]
     * <p>
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) {
            return res;
        }
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }

    /**
     * LeetCode 104: 二叉树的最大深度，也就是从根节点到叶子节点最长路径的长度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明:叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *          3
     *         / \
     *        9  20
     *          /  \
     *         15   7
     * 返回其最大深度：3
     *
     * 解题思路：递归
     *  1、判断当前正在遍历树的左右子树最大深度
     *  2、由于我们遍历的是当前节点左右子树的最大深度，所以当前节点对应树的最大深度要加1，即加上当前节点的一个深度
     *  3、本质上，该递归过程相当于一层一层的从下往上叠加（从最底层，不断往最大层上加1）
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        // 记得加上当前节点的最大深度
        return Math.max(leftMaxDepth, rightMaxDepth)+1;
    }

    /**
     * LeetCode 111: 二叉树的最小深度，即从跟节点到叶子节点到最小路径
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     *
     * 解题思路：不是简单的把最大路径的max换成min就可以了
     *  1、注意题意：叶子节点是指没有子节点的节点，所以，对于这种情况 root = [2,null,3,null,4,null,5,null,6]，如果简单的取min，就相当于把根节点当作了叶子节点
     *  2、正确思路是：
     *      当当前节点有左右子树时，跟最大深度逻辑一样，将max换成min就可以
     *      当当前节点有右子树，直接返回右子树的深度
     *      当当前节点有左子树，直接返回左子树的深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null)return 0;
        if (root.left != null && root.right != null) {
            int leftMinDepth = minDepth(root.left);
            int rightMinDepth = minDepth(root.right);
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        } else if (root.left != null && root.right == null){
            return minDepth(root.left) +1;
        } else {
            return minDepth(root.right) + 1;
        }
    }

    /**
     * LeetCode 226：反转二叉树
     * 反转一颗二叉树
     *
     * 示例：
     * 输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     *
     * 输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     * 解题思路：
     *  1、递归反转当前遍历节点的左右子树
     *  2、反转完当前节点的左右子树后，交换左右子树的位置
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 递归终止条件
        if(root == null) return null;
        // 对左子树反转
        invertTree(root.left);
        // 对右子树反转
        invertTree(root.right);
        // 反转完左右子树，再将左右子树调换位置（这里才是函数的核心操作，上面递归至少探底，并没有有意义的实际动作）
//        swap(root.left, root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        return root;
    }

    /**
     * LeetCode 100: 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 示例 1：
     * 输入：p = [1,2,3], q = [1,2,3]
     * 输出：true
     *
     * 解题思路：递归
     *  1、递归判断左右子树是否相等
     *  2、判断左右子树的时候要分情况讨论
     *      2.1 左子树为空，右子树为空 => 此时两树相同，返回true
     *      2.2 当左子树为空，右子树不为空；左子树不为空，右子树为空 => 左右子树不同，返回false
     *      2.3 左右子树都不为空的时候，递归探底
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) return true;

        // 当左子树为空，右子树不为空；左子树不为空，右子树为空
        if (p ==null || q == null) return false;

        // 需要在当前节点相等，且都有左右子树的情况下，对左右子树进行递归探底。如果相等，会碰到终止条件，返回true
        if (p.val == q.val){
            // 左子树相同，右子树也相同才相同
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    /**
     * LeetCode 101: 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *  
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     * 解题思路：递归（此题类似 leetcode 100）
     *  1、递归的对比两颗子树是否是对称的（左子树跟右子树比较）
     *  2、判断左右子树的时候要分情况讨论
     *     2.1 左子树为空，右子树为空 => 此时两树对称，返回true
     *     2.2 当左子树为空，右子树不为空；左子树不为空，右子树为空 => 左右子树不对称，返回false
     *     2.3 左右子树都不为空的时候，递归探底
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return compareLeftRight(root.left, root.right);
    }
    private boolean compareLeftRight(TreeNode left, TreeNode right){
        if (left==null && right==null) return true;
        if (left == null || right == null) return false;
        if (left.val == right.val){
            return compareLeftRight(left.left, right.right) && compareLeftRight(left.right, right.left);
        } else {
            return false;
        }
    }

    /**
     * LeetCode 173: 二叉搜索树迭代器 【中等】
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。
     * BST 的根节点 root 会作为构造函数的一部分给出。
     * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
     * 你可以假设next()调用总是有效的，也就是说，当调用 next()时，BST 的中序遍历中至少存在一个下一个数字。
     * <p>
     * 示例：
     *          7
     *         / \
     *        3  15
     *          /  \
     *          9   20
     * 输入
     * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
     * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
     * 输出
     * [null, 3, 7, true, 9, true, 15, true, 20, false]
     * <p>
     * 解释
     * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
     * bSTIterator.next();    // 返回 3
     * bSTIterator.next();    // 返回 7
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 9
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 15
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 20
     * bSTIterator.hasNext(); // 返回 False
     *
     * 解题思路：主要是在构造函数中通过中序遍历记录下元素顺序
     *  1、声明一个 int 指针，指向当前访问元素所在的索引
     *  2、构造函数中调用中序遍历，记录二叉树中序遍历的元素顺序
     *  3、如果当前索引没有超出元素个数，则可以继续访问下一个元素
     *
     * @param
     * @return
     */
    private class BSTIterator {
        // 记录下一个要访问元素的索引
        private int index;
        private List<Integer> valRecord;
        public BSTIterator(TreeNode root) {
            index = 0;
            valRecord = new ArrayList<>();
            // 按中序遍历顺序记录下所有节点的元素
            inOrder(root);
        }

        public int next() {
            // 每次调用 next ，返回当前指针指向的元素，然后右移一位当前指针
            return valRecord.get(index++);
        }

        public boolean hasNext() {
            // 当指针指向列表尾部的时候返回false,最终指针指向索引等于size
            return index < valRecord.size();
        }
        private void inOrder(TreeNode root){
            if (root == null) return;
            inOrder(root.left);
            valRecord.add(root.val);
            inOrder(root.right);
        }
    }

    // contains 函数
    public boolean contains(TreeNode root, int target){
        if (root == null){
            return false;
        }
        if (root.val == target) return true;
        if (root.left!=null){
            return contains(root.left, target);
        }
        if (root.right != null){
            return contains(root.right, target);
        }
        return false;
    }

    /**
     * LeetCode 112: 路径总和 【简单】
     * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
     * 叶子节点 是指没有子节点的节点。
     *
     * 解题思路：递归【对于二叉树中，对于叶子节点的判断要注意是有陷阱的】
     * 1、确定终止条件：左右子树为空时，判断当前节点的值是否是要找的值
     * 2、递归判断左右子树是否能找到一个满足条件的路径
     *
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        // 注意递归终止条件是判断是否是叶子节点，不是简单的节点为空就行了（有可能某一个子树为空，但不是叶子节点）
        if (root.left == null && root.right == null) return root.val==targetSum;

        // 不是叶子节点就继续判断左右子树是否能找到满足条件的路径
        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }

    /**
     * LeetCode 404: 左叶子之和
     * 计算给定二叉树的所有左叶子之和。
     *
     * 示例：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     *
     * 解题思路：递归
     * 1、终止条件为左右子树为空,本题特别的是还得多判断下是否是左叶子节点，此时返回该节点的值
     * 2、否则继续递归找叶子节点，将子树的叶子节点的值累加上来
     * 3、这里需要注意的是，在终止条件判断的时候，如果直接return 节点的值，会出现提前退出的情况，所以需要一个全局变量来记录返回值
     * @param root
     * @return
     */
    int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        // 这里需要注意的是：除了判断是叶子节点，还得确定是左叶子节点
        if (root.left!=null){  // 其实这里需要看到叶子节点及其父节点
            if(root.left.left == null && root.left.right == null){
                // 这里要返回左节点的值, 如果直接return，这里会提前终止，所以需要一个全局变量来存储结果
                res += root.left.val;
            }
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return res;
    }

    /**
     * LeetCode 257: 二叉树所有路径 【简单】
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 说明:叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 输入:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * 输出: ["1->2->5", "1->3"]
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * 解题思路：递归，注意每次递归的返回值比较复杂
     *  1、终止条件：当节点为叶子节点时，返回叶子节点的值
     *  2、递归寻找左右子树的路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // res保存每棵树的路径，而且，随着递归过程结束（从下往上），res中保存的路径由小变大
        // 这里 res 工作的过程是：每轮递归创建个空的，然后将本轮子树的路径拷贝过来，再补上本轮节点的值
        List<String> res = new ArrayList<>();
        if (root==null) return res;
        // 递归终止条件
        if (root.left == null && root.right == null){
            res.add(String.valueOf(root.val));
            // 这里要return一下，不然上一轮的递归拿不到下一轮递归的结果
            return res;
        }
        // 递归：触底反弹
        List<String> leftPath = binaryTreePaths(root.left);  // 对左子树递归
        // 遍历上次递归子树的结果，生成当前子树的路径（这里需要注意的是，leftPath存的是当前子树的子树的路径）
        for (int i = 0; i < leftPath.size(); i++) {  // 这里访问的节点跟上面递归访问的节点差一个代差
            res.add(String.valueOf(root.val) + "->" + leftPath.get(i));
        }

        // 同理，对右子树递归
        List<String> rightPath = binaryTreePaths(root.right);  // 对左子树递归
        for (int i = 0; i < rightPath.size(); i++) {
            res.add(String.valueOf(root.val) + "->" + rightPath.get(i));
        }

        // 当递归反弹到root节点后，相应左右子树生成的路径也add到了 res 中，最后 return res就保存了所有路径的字符串
        return res;
    }

    /**
     * LeetCode 113：路径总和II 【中等】
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * 示例：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     * 解题思路：
     *  1、
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> finalRes = new ArrayList<>();
        List<List<Integer>> allPaths = getPaths(root);
        for (List<Integer> list:allPaths){
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum+=list.get(i);
            }
            if (sum == targetSum){
                finalRes.add(list);
            }
        }
        return finalRes;
    }

    private List<List<Integer>> getPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        // 递归终止条件：叶子节点的时候
        if (root.left == null && root.right == null){
            List<Integer> path = new ArrayList<>();
            path.add(root.val);
            res.add(path);
            return res;
        }
        List<List<Integer>> leftPath = getPaths(root.left);
        for (int i = 0; i < leftPath.size(); i++) {
            leftPath.get(i).add(0,root.val);
            res.add(leftPath.get(i));
        }
        List<List<Integer>> rightPaht = getPaths(root.right);
        for (int i = 0; i < rightPaht.size(); i++) {
            rightPaht.get(i).add(0,root.val);
            res.add(rightPaht.get(i));
        }
        return res;
    }


    /**
     * LeetCode 102: 二叉树的层序遍历
     * @param root
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();

        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            List<Integer> subList = new ArrayList<>();
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode cur = queue.poll();
                subList.add(cur.val);
                if (cur.left !=null){
                    queue.add(cur.left);
                }
                if (cur.right != null){
                    queue.add(cur.right);
                }
            }
            res.add(subList);
        }

        return res;
    }

    /**
     * LeetCode 654: 最大二叉树
     *
     * @param nums
     */
    public TreeNode maxBST(int[] nums){
        if(nums == null || nums.length==0) return null;
        // 队列的作用是用来记录每棵子树最大值左侧的节点
        Deque<TreeNode> aux = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int curVal = nums[i];
            TreeNode cur = new TreeNode(curVal);
            // 把左侧所有小的节点生成在左子树上
            while (!aux.isEmpty() && aux.peek().val < curVal){
                cur.left = aux.pop();
            }
            // 因为从左到右遍历，所以后面节点都是右子树上的节点
            if(!aux.isEmpty()){
                aux.peek().right = cur;
            }
            aux.push(cur);
        }
        if (aux.isEmpty()){
            return null;
        } else {
            return aux.removeLast();
        }
    }


    public static void main(String[] args) {
        Solutions s = new Solutions();
        TreeNode bst = s.getTestInstance(new int[]{1, 2, 3});

        System.out.println(s.inorderTraversal(bst));
    }

    // 构造测试用例使用
    public TreeNode getTestInstance(int[] arr) {
        return new TreeNode(arr);
    }
}

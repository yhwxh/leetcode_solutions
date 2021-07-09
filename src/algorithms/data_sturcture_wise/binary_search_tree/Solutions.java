package algorithms.data_sturcture_wise.binary_search_tree;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    /**
     * LeetCode 94: 二叉树的中序遍历
     *      给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     *
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     * 输入：root = []
     * 输出：[]
     *
     * 输入：root = [1]
     * 输出：[1]
     *
     * 输入：root = [1,2]
     * 输出：[2,1]
     *
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

        TreeNode(int[] arr){
            if (arr == null || arr.length == 0){
                throw new IllegalArgumentException("");
            }
            this.val = arr[0];
            TreeNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                if (i < cur.val){
                    cur.left = new TreeNode(i);
                } else if (i > cur.val){
                    cur.right = new TreeNode(i);
                }
            }
        }
    }
    // 构造测试用例使用
    public TreeNode getTestInstance(int[] arr){
        return new TreeNode(arr);
    }

    public static void main(String[] args) {
        Solutions s = new Solutions();
        TreeNode bst = s.getTestInstance(new int[]{1,2,3});

        System.out.println(s.inorderTraversal(bst));
    }
}

# 涉及到的题目和数据结构

### 题目
- 1、【简单】 LeetCode 349: [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) 【借用 Set 或 Map】
- 2、【简单】 LeetCode 350: [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) 【借用 Set 或 Map】
- 3、【简单】 LeetCode 94: [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) 【BST 的递归遍历：当前节点】
- 4、【简单】 LeetCode 104: [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) 【BST：未实现】
- 5、【中等】 LeetCode 173: [二叉搜索树迭代器](https://leetcode-cn.com/problems/binary-search-tree-iterator/) 【BST：未实现】
- 6、【简单】 LeetCode 203: [移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/) 【链表遍历 OR 递归：前继节点、后继节点】
- 7、【简单】 LeetCode 804: [唯一的摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words/) 【借用 Set】
- 8、【中等】 LeetCode 347: [前K个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/) 【优先队列】
- 9、【简单】 LeetCode 303: [区域和检查(数组不可变,即不涉及线段树的更新操作)](https://leetcode-cn.com/problems/range-sum-query-immutable/) 【线段树】
- 10、【简单】 LeetCode 20: [有效括号](https://leetcode-cn.com/problems/valid-parentheses/) 【栈】
- 11、【中等】 LeetCode 22: [生成括号（生成所有有效括号）](https://leetcode-cn.com/problems/generate-parentheses/) 【字符串、动态规划：未实现】
- 12、【简单】 LeetCode 557: [反转字符串中的单词 III](https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/) 【双指针：未实现】
- 13、【简单】 LeetCode 344: [反转字符串](https://leetcode-cn.com/problems/reverse-string/) 【双指针、递归：未实现】
- 14、【简单】 LeetCode 541: [反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/) 【双指针、字符串：未实现】
- 15、【中等】 LeetCode 930: [和相同的二元子数组](https://leetcode-cn.com/problems/binary-subarrays-with-sum/) 【滑动窗口】
- 16、【简单】 LeetCode 283: [移动零](https://leetcode-cn.com/problems/move-zeroes/) 【双指针】
- 17、【简单】 LeetCode 27: [移除元素](https://leetcode-cn.com/problems/remove-element/) 【双指针】
- 18、【简单】 LeetCode 26: [删除有序数组中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) 【双指针】
- 19、【中等】 LeetCode 80: [删除有序数组中的重复元素II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) 【双指针+慢指针】
- 20、【中等】 LeetCode 2: [两数相加](https://leetcode-cn.com/problems/add-two-numbers/) 【链表遍历】
- 21、【简单】 LeetCode 14: [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/) 【字符串】
- 22、【简单】 LeetCode 122: [买卖股票的最佳时机II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) 【数组、贪心、动态规划】
- 23、【中等】 LeetCode 75： [颜色分类](https://leetcode-cn.com/problems/sort-colors/) 【计数排序、三路快排】
- 24、【简单】 LeetCode 88: [合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/) 【归并排序子过程】
- 25、【中等】LeetCode 215: [数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) 【快速排序子过程：partition操作】
- 26、【简单】LeetCode 167: [两数之和II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 【碰撞指针】
- 27、【简单】LeetCode 125: [验证回文串](https://leetcode-cn.com/problems/valid-palindrome/) 【双指针：未实现】
- 28、【困难】剑指Offer 51: [数组中的逆序对](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/) 【归并排序】
- 29、【困难】LeetCode 629: [k个逆序数组](https://leetcode-cn.com/problems/k-inverse-pairs-array/) 【动态规划】
- 30、【简单】LeetCode 206: [反转链表](https://leetcode-cn.com/problems/reverse-linked-list/) 【链表遍历：前继节点、当前节点、后继节点】
- 31、【简单】LeetCode 92: [反转链表II：对指定区间进行反转](https://leetcode-cn.com/problems/reverse-linked-list-ii/) 【链表遍历前继节点、当前节点、后继节点+头尾节点】
- 32、【简单】LeetCode 141: [判断链表是否有环](https://leetcode-cn.com/problems/linked-list-cycle/) 【快慢指针:快一步：未实现】
- 33、【中等】LeetCode 142: [判断链表是否有环II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) 【快慢指针+Map 或者 对每个节点打上索引:未实现】  
- 33、【简单】剑指Offer 29 : [顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/) 【未实现】
- 34、【简单】LeetCode 1572: [矩阵对角线元素的和](https://leetcode-cn.com/problems/matrix-diagonal-sum/) 【数组遍历】
- 35、【中等】LeetCode 61: [旋转链表：向右挪动k个](https://leetcode-cn.com/problems/rotate-list/) 【先记录链表节点个数，尾指头，然后在 sie-k处断开】
- 36、【中等】leetCode 209: [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) 【滑动窗口】
- 37、【中等】leetCode 3: [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) 【滑动窗口】

### 数据结构  
- 动态数组：
  * DynamicArray
- 链表：
  * LinkedList
- 栈
  * ArrayStack
  * LinkedListStack
- 队列
  * ArrayQueue
  * LinkedListQueue
  * LoopQueue  
- 二分搜索树
  * BinarySearchTree
- 堆
  * MaxHeap
- Map:
  * LinkedListMap
  * BSTMap
- Set: 
  * LinkedListSet
  * BSTSet
- 优先队列
  * PriQueue
- 线段树:
  * SegmentTree
- Trie：
  * Trie
- 并查集：
  * UnionFind
  * QuickUnionUnionFind  
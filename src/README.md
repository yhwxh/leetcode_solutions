# 涉及到的题目和数据结构

### 题目
- 1、【简单】 LeetCode 349: [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) 【借用 Set 或 Map】
- 2、【简单】 LeetCode 350: [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) 【借用 Set 或 Map】
- 3、【简单】 LeetCode 94: [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) 【BST 的递归遍历：当前节点】
- 4、【简单】 LeetCode 104: [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) 【BST：<font color=red>未实现</font>】
- 5、【中等】 LeetCode 173: [二叉搜索树迭代器](https://leetcode-cn.com/problems/binary-search-tree-iterator/) 【BST：<font color=red>未实现</font>】
- 6、【简单】 LeetCode 203: [移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/) 【链表遍历 OR 递归：前继节点、后继节点】
- 7、【简单】 LeetCode 804: [唯一的摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words/) 【借用 Set】
- 8、【中等】 LeetCode 347: [前K个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/) 【优先队列】
- 9、【简单】 LeetCode 303: [区域和检查(数组不可变,即不涉及线段树的更新操作)](https://leetcode-cn.com/problems/range-sum-query-immutable/) 【线段树】
- 10、【简单】 LeetCode 20: [有效括号](https://leetcode-cn.com/problems/valid-parentheses/) 【借用 栈】
- 11、【中等】 LeetCode 22: [生成括号（生成所有有效括号）](https://leetcode-cn.com/problems/generate-parentheses/) 【递归：确定好左右括号添加的原则】
- 12、【简单】 LeetCode 557: [反转字符串中的单词 III](https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/) 【双指针：确定好每个词的边界】
- 13、【简单】 LeetCode 344: [反转字符串](https://leetcode-cn.com/problems/reverse-string/) 【双指针、递归】
- 14、【简单】 LeetCode 541: [反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/) 【双指针、字符串：确定好边界和移动间隔】
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
- 25、【中等】 LeetCode 215: [数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) 【快速排序子过程：partition操作】
- 26、【简单】 LeetCode 167: [两数之和II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 【碰撞指针】
- 27、【简单】 LeetCode 125: [验证回文串](https://leetcode-cn.com/problems/valid-palindrome/) 【双指针：注意边界检查】
- 28、【困难】 剑指Offer 51: [数组中的逆序对](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/) 【归并排序】
- 29、【困难】 LeetCode 629: [k个逆序数组](https://leetcode-cn.com/problems/k-inverse-pairs-array/) 【动态规划】
- 30、【简单】 LeetCode 206: [反转链表](https://leetcode-cn.com/problems/reverse-linked-list/) 【链表遍历：需找到 前继节点、当前节点、后继节点 才能完成一个节点的反转】
- 31、【简单】 LeetCode 92: [反转链表II：对指定区间进行反转](https://leetcode-cn.com/problems/reverse-linked-list-ii/) 【链表遍历前继节点、当前节点、后继节点+头尾节点】
- 32、【简单】 LeetCode 141: [判断链表是否有环](https://leetcode-cn.com/problems/linked-list-cycle/) 【快慢指针:快一步就可以】
- 33、【中等】 LeetCode 142: [判断链表是否有环II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) 【借助 Set 或者 快慢指针并利用带环链表相遇后的性质：同时移动慢指针和新指针，会在入环节点相遇】  
- 33、【简单】 剑指Offer 29 : [顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/) 【边界问题】
- 34、【简单】 LeetCode 1572: [矩阵对角线元素的和](https://leetcode-cn.com/problems/matrix-diagonal-sum/) 【数组遍历：边界问题】
- 35、【中等】 LeetCode 61: [旋转链表：向右挪动k个](https://leetcode-cn.com/problems/rotate-list/) 【先记录链表节点个数，尾指头，然后在 size-k 处断开】
- 36、【中等】 LeetCode 209: [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) 【滑动窗口】
- 37、【中等】 LeetCode 3: [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) 【滑动窗口】
- 38、【简单】 LeetCode 232: [用栈实现队列](https://leetcode-cn.com/problems/implement-queue-using-stacks/) 【需要用到两个栈在每次压栈和每次出栈的时候在两个栈之间倒换下数据】
- 39、【简单】 LeetCode 225: [用对列实现栈](https://leetcode-cn.com/problems/implement-queue-using-stacks/) 【需要两个队列，一个辅助队列保证后加入的元素在前面】
- 40、【简单】 LeeCode 703: [返回数据流中第K大的元素](https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/) 【借助 优先队列：维护一个大小为K的最小堆 OR 最大堆】
- 41、【中等】 LeetCode 24: [两两交换链表中的节点：两两反转链表](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) 【链表遍历：需要找到 前继节点、当前节点、后继节点 才能完成两个节点的反转】
- 42、【简单】 LeetCode 242: [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) 【借助 数组 OR Map】
- 43、【简单】 LeetCode 202: [快乐数](https://leetcode-cn.com/problems/happy-number/) 【借助 Set OR Map】
- 44、【简单】 LeetCode 290: [单词规律](https://leetcode-cn.com/problems/word-pattern/) 【借助两个 Map】
- 44、【简单】 LeetCode 205: [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 45、【中等】 LeetCode 451: [根据字符出现频率排序](https://leetcode-cn.com/problems/sort-characters-by-frequency/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 46、【简单】 LeetCode 1: [两数之和](https://leetcode-cn.com/problems/two-sum/) 【借助 Set OR Map】
- 47、【中等】 LeetCode 15: [三数之和](https://leetcode-cn.com/problems/3sum/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 48、【中等】 LeetCode 18: [四数之和](https://leetcode-cn.com/problems/4sum/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 49、【中等】 LeetCode 16: [最接近的三树之和](https://leetcode-cn.com/problems/3sum-closest/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 50、【中等】 LeetCode 454: [四数相加II](https://leetcode-cn.com/problems/4sum-ii/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 51、【中等】 LeetCode 49: [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 52、【中等】 LeetCode 447: [回旋镖数量](https://leetcode-cn.com/problems/number-of-boomerangs/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 53、【困难】 LeetCode 149: [直线上最多的点数](https://leetcode-cn.com/problems/max-points-on-a-line/) 【借助 Set OR Map：<font color=red>未实现</font>】
- 54、【困难】 LeetCode 239: [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/) 【借助 优先队列 或者 普通队列：<font color=red>未实现</font>】
- 55、【困难】 LeetCode 480: [滑动窗口中位数](https://leetcode-cn.com/problems/sliding-window-median/) 【借助 优先队列 或者 普通队列：<font color=red>未实现</font>】
- 56、【困难】 剑指Offer 59-1: [滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/) 【借助 优先队列 或者 普通队列：<font color=red>未实现</font>】
- 57、【中等】 LeetCode 74: [搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/) 【数组遍历：需要点trick 或者 二分搜索】
- 58、【中等】 LeetCode 240: [搜索二维矩阵II（矩阵中点元素）](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/) 【数组遍历：需要点trick】
- 59、【中等】 LeetCode 189: [反转数值](https://leetcode-cn.com/problems/rotate-array/) 【类似反转链表：先整体反转，再分段反转】
- 60、【简单】 LeetCode 219: [存在重复元素II](https://leetcode-cn.com/problems/contains-duplicate-ii/) 【借助 Set】
- 61、【中等】 LeetCode 220: [存在重复元素III](https://leetcode-cn.com/problems/contains-duplicate-iii/) 【借助 Set 并需要trick】
- 62、【中等】 LeetCode 19: [删除链表的倒数第N个元素](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) 【链表+双指针】
- 63、【简单】 LeetCode 70: [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) 【动态规划 或者 记忆化搜索】
- 64、【中等】 LeetCode 120: [三角形的最短路径](https://leetcode-cn.com/problems/triangle/) 【动态规划】
- 65、【中等】 LeetCode 64: [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/) 【动态规划】

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
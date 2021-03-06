# 涉及到的题目和数据结构

### 题目
- 1、【简单】 LeetCode 349: [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) 【借用 Set：重复元素只返回一个，所以set就可以】
- 2、【简单】 LeetCode 350: [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) 【借用Map：重复元素返回个数最少的，需要记录次数】
- 3、【简单】 LeetCode 94: [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) 【BST 的递归遍历：当前节点】
- 4、【简单】 LeetCode 104: [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) 【二叉树：递归计算左右子树最大深度】
- 5、【中等】 LeetCode 173: [二叉搜索树迭代器](https://leetcode-cn.com/problems/binary-search-tree-iterator/) 【BST：主要是要在构造函数中实现中序遍历，并且有个指针记录下一个元素的索引】
- 6、【简单】 LeetCode 203: [移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/) 【链表遍历 OR 递归：前继节点、后继节点】
- 7、【简单】 LeetCode 804: [唯一的摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words/) 【借用 Set】
- 8、【中等】 LeetCode 347: [前K个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/) 【优先队列】
- 9、【简单】 LeetCode 303: [区域和检查(数组不可变,即不涉及线段树的更新操作)](https://leetcode-cn.com/problems/range-sum-query-immutable/) 【线段树】
- 10、【简单】 LeetCode 20: [有效括号](https://leetcode-cn.com/problems/valid-parentheses/) 【借用 栈】
- 11、【中等】 LeetCode 22: [<font color=green>生成括号（生成所有有效括号）</font>](https://leetcode-cn.com/problems/generate-parentheses/) 【递归：确定好左右括号添加的原则】
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
- 22、【简单】 LeetCode 122(121): [<font color=green>买卖股票的最佳时机II</font>](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) 【数组、贪心、动态规划】
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
- 33、【简单】 剑指Offer 29 : [<font color=green>顺时针打印矩阵</font>](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/) 【边界问题】
- 34、【简单】 LeetCode 1572: [矩阵对角线元素的和](https://leetcode-cn.com/problems/matrix-diagonal-sum/) 【数组遍历：边界问题】
- 35、【中等】 LeetCode 61: [旋转链表：向右挪动k个](https://leetcode-cn.com/problems/rotate-list/) 【先记录链表节点个数，尾指头，然后在 size-k 处断开】
- 36、【中等】 LeetCode 209: [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) 【滑动窗口】
- 37、【中等】 LeetCode 3: [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) 【滑动窗口:需要一个256长度的数组，记录每个字符是否出现过，】
- 38、【简单】 LeetCode 232: [用栈实现队列](https://leetcode-cn.com/problems/implement-queue-using-stacks/) 【需要用到两个栈在每次压栈和每次出栈的时候在两个栈之间倒换下数据】
- 39、【简单】 LeetCode 225: [用对列实现栈](https://leetcode-cn.com/problems/implement-queue-using-stacks/) 【需要两个队列，一个辅助队列保证后加入的元素在前面】
- 40、【简单】 LeeCode 703: [返回数据流中第K大的元素](https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/) 【借助 优先队列：维护一个大小为K的最小堆（第K个最大值，就是K个元素中第最小的那个，所以是最小堆）】
- 41、【中等】 LeetCode 24: [两两交换链表中的节点：两两反转链表](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) 【链表遍历：需要找到 前继节点、当前节点、后继节点 才能完成两个节点的反转】
- 42、【简单】 LeetCode 242: [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) 【借助 数组 OR Map】
- 43、【简单】 LeetCode 202: [快乐数](https://leetcode-cn.com/problems/happy-number/) 【借助 Set OR Map】
- 44、【简单】 LeetCode 290: [单词规律](https://leetcode-cn.com/problems/word-pattern/) 【借助两个 Map】
- 44、【简单】 LeetCode 205: [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 45、【中等】 LeetCode 451: [根据字符出现频率排序](https://leetcode-cn.com/problems/sort-characters-by-frequency/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 46、【简单】 LeetCode 1: [两数之和](https://leetcode-cn.com/problems/two-sum/) 【借助 Set OR Map】
- 47、【中等】 LeetCode 15: [三数之和](https://leetcode-cn.com/problems/3sum/) 【先排序，再双指针】
- 48、【中等】 LeetCode 18: [四数之和](https://leetcode-cn.com/problems/4sum/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 49、【中等】 LeetCode 16: [最接近的三树之和](https://leetcode-cn.com/problems/3sum-closest/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 50、【中等】 LeetCode 454: [四数相加II](https://leetcode-cn.com/problems/4sum-ii/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 51、【中等】 LeetCode 49: [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) 【借助 Set OR Map ：<font color=red>未实现</font>】
- 52、【中等】 LeetCode 447: [回旋镖数量](https://leetcode-cn.com/problems/number-of-boomerangs/) 【借助Map ：记录每个数据点距离其他点的距离，以及该距离下有多少个数据点】
- 53、【困难】 LeetCode 149: [直线上最多的点数](https://leetcode-cn.com/problems/max-points-on-a-line/) 【借助 Set OR Map：<font color=red>未实现</font>】
- 54、【困难】 LeetCode 239: [<font color=green>滑动窗口最大值</font>](https://leetcode-cn.com/problems/sliding-window-maximum/) 【借助 优先队列 或者 双端队列：关键在于怎么维护窗口中的最大值：不必保证队列必须k个元素，只要队列中最大值是窗口覆盖范围内的就行】
- 55、【困难】 LeetCode 480: [滑动窗口中位数](https://leetcode-cn.com/problems/sliding-window-median/) 【借助 优先队列 或者 双端队列：<font color=red>未实现</font>】
- 56、【困难】 剑指Offer 59-1: [滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/) 【借助 优先队列 或者 双端队列：同 LeetCode 239】
- 57、【中等】 LeetCode 74: [搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/) 【数组遍历：需要点trick 或者 二分搜索】
- 58、【中等】 LeetCode 240: [搜索二维矩阵II（矩阵中点元素）](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/) 【数组遍历：需要点trick】
- 59、【中等】 LeetCode 189: [反转数值](https://leetcode-cn.com/problems/rotate-array/) 【类似反转链表：先整体反转，再分段反转】
- 60、【简单】 LeetCode 219: [存在重复元素II](https://leetcode-cn.com/problems/contains-duplicate-ii/) 【借助 Set】
- 61、【中等】 LeetCode 220: [存在重复元素III](https://leetcode-cn.com/problems/contains-duplicate-iii/) 【借助 Set 并需要trick】
- 62、【中等】 LeetCode 19: [删除链表的倒数第N个元素](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) 【链表+双指针：移动固定大小的窗口来找到要删除的节点】
- 63、【简单】 LeetCode 70: [<font color=green>爬楼梯：两步&三步</font>](https://leetcode-cn.com/problems/climbing-stairs/) 【动态规划 或者 记忆化搜索】
- 64、【中等】 LeetCode 120: [<font color=green>三角形的最短路径</font>](https://leetcode-cn.com/problems/triangle/) 【动态规划：一行一行的更新每行中每个元素的状态】
- 65、【中等】 LeetCode 64: [<font color=green>最小路径和</font>](https://leetcode-cn.com/problems/minimum-path-sum/) 【动态规划：一行一行的更新每行中每个元素的状态】
- 66、【中等】 LeetCode 343: [<font color=green>整数拆分</font>](https://leetcode-cn.com/problems/integer-break/) 【动态规划】
- 67、【中等】 LeetCode 279: [完全平方数](https://leetcode-cn.com/problems/perfect-squares/) 【动态规划：<font color=red>未实现</font>】
- 68、【中等】 LeetCode 91: [解码方法](https://leetcode-cn.com/problems/decode-ways/) 【动态规划：<font color=red>未实现</font>】
- 69、【中等】 LeetCode 279: [完全平方数](https://leetcode-cn.com/problems/perfect-squares/) 【动态规划：<font color=red>未实现</font>】
- 70、【中等】 LeetCode 62: [不同路径](https://leetcode-cn.com/problems/unique-paths/) 【动态规划：<font color=red>未实现</font>】
- 71、【中等】 LeetCode 63: [不同路径II](https://leetcode-cn.com/problems/unique-paths-ii/) 【动态规划：<font color=red>未实现</font>】
- 72、【简单】 LeetCode 111: [二叉树最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) 【二叉树：递归判断当前节点的左右子树的深度，并需要分情况讨论当前节点各种可能子树的情况】
- 73、【简单】 LeetCode 226: [<font color=green>反转二叉树</font>](https://leetcode-cn.com/problems/invert-binary-tree/) 【二叉树：递归反转左右子树，再交换左右子树的位置】
- 74、【简单】 LeetCode 100: [相同的树](https://leetcode-cn.com/problems/same-tree/) 【二叉树：递归判断左右子树是否相同，分情况讨论是否相同】
- 75、【简单】 LeetCode 101: [<font color=green>对称二叉树</font>](https://leetcode-cn.com/problems/symmetric-tree/) 【二叉树：递归判断左右两颗子树是否对称，分情况讨论】
- 76、【简单】 LeetCode 404: [左叶子之和](https://leetcode-cn.com/problems/sum-of-left-leaves/) 【二叉树：递归寻找左右子树的左叶子节点，注意终止条件的判断】
- 77、【简单】 LeetCode 257: [二叉树所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)  【二叉树：递归查找左右子树的路径，注意终止条件的判断，注意递归中变量的先后关系和层级关系】
- 78、【中等】 LeetCode 113: [<font color=green>路径总和II</font>](https://leetcode-cn.com/problems/path-sum-ii/)  【二叉树：递归寻找所有路径，然后寻找满足条件路径】
- 79、【困难】 LeetCode 42: [<font color=green>接雨水</font>](https://leetcode-cn.com/problems/trapping-rain-water/)  【双指针：需要明确什么决定某个位置的水位】
- 80、【中等】 LeetCode 5: [<font color=green>最长回文子串</font>](https://leetcode-cn.com/problems/longest-palindromic-substring/) 【双指针：需要将每个字符作为中心，向两边遍历找最长回文，而且需要分奇偶情况】
- 81、【中等】 LeetCode 300: [<font color=green>最长递增子序列</font>](https://leetcode-cn.com/problems/longest-increasing-subsequence/) 【动态规划：状态为以每个元素结尾的数组的最长递增子序列，转移为当前元素之前的数组的最长子序列的最大值+1，返回状态中的最大值】
- 82、【中等】 LeetCode 322: [<font color=green>零钱兑换</font>](https://leetcode-cn.com/problems/coin-change/) 【动态规划：状态为每种金额的最优解，当每个金额可分解，且有剩余金额的最优解时，当前金额最优解就是所有可能中的最小值，返回最后一个状态】
- 83、【中等】 LeetCode 46: [<font color=green>全排列</font>](https://leetcode-cn.com/problems/permutations/) 【回溯：每次判断每个元素是否加入子序列，并在每次添加后，递归下一个"个数（排了几个元素）"】
- 84、【中等】 LeetCode 77: [组合](https://leetcode-cn.com/problems/combinations/) 【回溯：由于组合不考虑顺序，前面考虑过的元素不会在后面考虑，所以每次遍历从已经考虑的元素后一个开始，每次遍历递归下一个元素】
- 85、【中等】 LeetCode 78: [<font color=green>子集</font>](https://leetcode-cn.com/problems/subsets/) 【回溯：】 
- 86、【简单】 LeetCode 53: [<font color=green>最大子序和</font>](https://leetcode-cn.com/problems/maximum-subarray/) 【动态规划：此动态规划的状态不用O(n)的数组，而是记录当前最大子序列的和，如果当前最大字序和大于0，序列还能扩展，否则，序列重新开始】
- 87、【中等】 LeetCode 102: [<font color=green>二叉树层序遍历</font>](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) 【二叉树：广度优先遍历，记录每层的元素个数（即队列的大小）】
- 88、【简单】 LeetCode 21：[<font color=green>合并两个有序链表</font>](https://leetcode-cn.com/problems/merge-two-sorted-lists/) 【链表遍历：注意指针的定义】
- 89、【简单】 LeetCode 69: [<font color=green>x的平方根</font>](https://leetcode-cn.com/problems/sqrtx/) 【二分查找&牛顿法：迭代公式：x = (x+input/x)/2】
- 90、【中等】 LeetCode 17.14: [最小的K个数](https://leetcode-cn.com/problems/smallest-k-lcci/) 【优先队列：最小堆的前k个元素】
- 91、【中等】 LeetCode 98: [<font color=green>验证二叉（分）搜索树</font>](https://leetcode-cn.com/problems/validate-binary-search-tree/) 【二分搜索树，中序遍历】
- 92、【中等】 LeetCode 33: [<font color=green>搜索旋转排序数组</font>](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) 【最优解：二分搜索，在mid处将数组一份为二，判断两部分是否有序，对每一部分判断目标值在两部分中的哪一部分】
- 93、【中等】 LeetCode 654:[<font color=green>最大二叉树</font>](https://leetcode-cn.com/problems/maximum-binary-tree/)  【最优解：需要用到一个队列来存储所有节点】
- 94、【中等】 LeetCode 1143:[<font color=green>最长公共子序列</font>](https://leetcode-cn.com/problems/longest-common-subsequence/) 【动态规划：状态为两个字符串当前索引处的最长公共子串，转移方程分两种情况：字符相等（上个状态+1）和不相等（上两个状态的最大值），返回最后一个】
- 95、【中等】 LeetCode 1345:[<font color=green>跳跃游戏III</font>](https://leetcode-cn.com/problems/jump-game-iv/) 【最优解：BFS】
- 96、【困难】 LeetCode 4:[<font color=green>寻找两个正序数组的中位数</font>](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) 【变换思路：查找第k个元素】
- 97、【困难】 LeetCode 41:[<font color=gren>缺失的第一个正数</font>](https://leetcode-cn.com/problems/first-missing-positive/) 【】
 

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
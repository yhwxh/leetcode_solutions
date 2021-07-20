package data_sturcture;

import java.util.TreeMap;

/**
 * 多叉树Trie：专门的、真正的为字典设计的数据结构
 * 专门为处理字符串而设计的
 * 查询每个条目（term）的时间复杂度与有多少个term无关，而与单词的字母个数有关
 * <p>
 * trie 将单词拆成每个字母，而不是将每个单词看作一个整体，
 * 当我们从根节点遍历一次Trie就会得道一个单词
 * 每个节点有26个指向下个节点的指针
 */

public class Trie/*<E>*/ {      // 这里使用了泛型，这样可以不仅用于英文的字符
    private class Node {
        char c; // 不用存当前节点存的字符是什么，只要知到该节点指向了哪个字符就行(在 next 中保存了)
        boolean isWord;     // 当前节点是否是一个单词的结尾
        // 此处指向下一个节点的指针是一个Map，这是为了实现动态结果，即，每个节点后继节点不一定是 26 个，而是若干个
        // Character 是当前节点指向的下一个节点中存放的字符（别忘了，是多叉树），Node 是下一个节点
        TreeMap<Character, Node> next;

        Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        Node() {
            this(false);
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            return ret.toString();
        }

    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    // 向 Trie 中添加一个单词，添加的是字符串不是字符，具体加入的时候会把字符串拆成字符
    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next.get(c) == null) {   // 不包括这个字符就创建
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);  // 往下一个节点走
        }
        // 遍历到单词的最后一个字符，将 isword 改为true
        if (!cur.isWord) {   // 需要判断当前节点是否是结尾，不是的话才会是真正的添加
            cur.isWord = true;
            size++;
        }
    }

    // add 的递归写法
    private void add(Node node, String word) {
        if (word == null) {
            return;
        } else {
            char curCharacter = word.charAt(0);
            if (word.length() == 1) {
                if (node.next.get(word.charAt(0)) == null) {
                    node.next.put(curCharacter, new Node());
                }
                return;
            }
            add(node.next.get(curCharacter), word.substring(1));
        }
    }

    // 从 Trie 中查询一个单词
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 虽然遍历完，word 中每个字符都有，但是最后那个字符不是结尾符，照样，Trie 中的这个单词不是要查找的单词
        return cur.isWord;
    }

    // 前缀搜索
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 与 contains 不同，这里不关心是不是结尾字符
        return true;
    }

    // Trie 的简单(只有通配符 .)模式匹配问题：比如，“a..b" 其中 “.” 表示任意字符
    public boolean patternSearch(String pattern) {
        return match(root, pattern, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            // 如果是通配符，说明不确定匹配哪个字符，也就不确定走哪个分支，所以我们要遍历所有分支，任何一个分支能匹配上就返回 true
            for (char nextChar : node.next.keySet()) {
                // 这样判断，就相当于跳过了当前的通配符字符，去后面的所有分支上找下一个字符
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            // 如果所有分支上都没有找到，返回 false
            return false;
        }
    }
}

// 把 Trie 当作一个 Map 来使用（以上的操作都是把 Trie 当作集合来用的）
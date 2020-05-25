package tree;

import java.util.TreeMap;

public class Trie {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
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

    /**
     * Time Complexity: O(w), 'w' is the length of the word.
     */
    public void add(String word) {
        if (word == null) {
            throw new IllegalArgumentException("The word mustn't be null.");
        }
        if (word.length() == 0) {
            return;
        }

        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        // 防止添加重复元素
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * Time Complexity: O(w), 'w' is the length of the word.
     */
    public boolean contains(String word) {
        if (word == null) {
            return false;
        }

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * Time Complexity: O(w), 'w' is the length of the prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    /**
     * A word could contain the dot character '.' to represent any one letter.
     * Worst time complexity: O(n), 'n' is the number of the nodes.
     */
    public boolean fuzzySearch(String word) {
        if (word == null) {
            return false;
        }
        return fuzzySearch(root, word, 0);
    }

    private boolean fuzzySearch(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);

        if (c == '.') {
            for (char nextChar: node.next.keySet()) {
                if (fuzzySearch(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (node.next.get(c) == null) {
                return false;
            }
            return fuzzySearch(node.next.get(c), word, index + 1);
        }
    }

}

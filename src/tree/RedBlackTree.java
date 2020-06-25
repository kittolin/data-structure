package tree;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RedBlackTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(logN)
     */
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }
        return getNode(root, key) != null;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(logN)
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }
        Node node = getNode(root, key);
        return node == null? null: node.value;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(logN)
     */
    public void set(K key, V newValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist.");
        }
        node.value = newValue;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(logN)
     */
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }
        root = add(root, key, value);
        // Root node is black
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    private boolean isRed(Node node) {
        // Leaf node is black
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    /**
     *   node                             x
     *  /   \     leftRotate            /  \
     * T1   x   -------------->      node  T3
     *     / \                      /   \
     *    T2 T3                    T1   T2
     */
    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     *     node                         x
     *    /   \     rightRotate       /  \
     *   x    T2  -------------->   y   node
     *  / \                             /  \
     * y  T1                           T1  T2
     */
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


}

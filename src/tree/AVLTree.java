package tree;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     *        y                              x
     *       / \                           /   \
     *      x  T4    rightRotate(y)       z     y
     *     / \     ------------------>   / \   / \
     *    z  T3                         T1 T2 T3 T4
     *   / \
     * T1  T2
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }
    /**
     *    y                                x
     *   / \                             /   \
     * T1   x        leftRotate(y)      y     z
     *     / \     ---------------->   / \   / \
     *   T2   z                       T1 T2 T3 T4
     *       / \
     *     T3  T4
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            node = rightRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            node = leftRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }

        return node;
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

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return balance(node);
    }

    private Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(logN)
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else if (node.left == null) {
            retNode = node.right;
            node.right = null;
            size --;
        } else if (node.right == null) {
            retNode = node.left;
            node.left = null;
            size --;
        } else {
            Node successor = getMin(node.right);
            successor.right = remove(node.right, successor.key);
            successor.left = node.left;
            node.left = node.right = null;
            retNode = successor;
        }

        if (retNode == null) {
            return null;
        }

        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        return balance(retNode);
    }

    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrderTraverse(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0 ) {
                return false;
            }
        }
        return true;
    }

    private void inOrderTraverse(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }

        inOrderTraverse(node.left, keys);
        keys.add(node.key);
        inOrderTraverse(node.right, keys);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

}

package map;

public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node root;
    private int size;

    public BinarySearchTreeMap() {
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Node root, K key) {
        if (root == null) {
            return null;
        }

        if(key.compareTo(root.key) < 0) {
            return getNode(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            return getNode(root.right, key);
        } else {
            return root;
        }
    }

    /**
     * Average time complexity: O(logN)
     * worst time complexity: O(N)
     */
    @Override
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Add failed. Key cannot be null.");
        }
        root = add(root, key, value);
    }

    private Node add(Node root, K key, V value) {
        if (root == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(root.key) < 0) {
            root.left = add(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = add(root.right, key, value);
        } else {
            root.value = value;
        }

        return root;
    }

    /**
     * Average time complexity: O(logN)
     * worst time complexity: O(N)
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Remove failed. Key cannot be null");
        }

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node root, K key) {
        if (root == null) {
            return null;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
            return root;
        }
        if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
            return root;
        }

        if (root.left == null) {
            Node retNode = root.right;
            root.right = null;
            size --;
            return retNode;
        }
        if (root.right == null) {
            Node retNode = root.left;
            root.left = null;
            size --;
            return retNode;
        }

        Node successor = getMin(root.right);
        successor.right = removeMin(root.right);
        successor.left = root.left;
        root.left = root.right = null;
        return successor;
    }

    private Node getMin(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return getMin(root.left);
    }

    private Node removeMin(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            Node retNode = root.right;
            root.right = null;
            size --;
            return retNode;
        }
        root.left = removeMin(root.left);
        return root;
    }

    /**
     * Average time complexity: O(logN)
     * worst time complexity: O(N)
     */
    @Override
    public void set(K key, V newValue) {
        if (key == null) {
            throw new IllegalArgumentException("Set failed. Key cannot be null.");
        }

        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist.");
        }
        node.value = newValue;
    }

    /**
     * Average time complexity: O(logN)
     * worst time complexity: O(N)
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        Node node = getNode(root, key);
        return node == null? null: node.value;
    }

    /**
     * Average time complexity: O(logN)
     * worst time complexity: O(N)
     */
    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        return getNode(root, key) != null;
    }

}

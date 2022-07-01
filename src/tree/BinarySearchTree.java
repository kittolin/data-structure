package tree;

import queue.LinkedListQueue;
import stack.LinkedListStack;
import queue.Queue;
import stack.Stack;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
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
     * Worst time complexity: O(N)
     */
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Add failed. Invalid element.");
        }
        root = add(root, e);
    }

    private Node add(Node root, E e) {
        if (root == null) {
            size ++;
            return new Node(e);
        }

        if (e.compareTo(root.e) < 0) {
            root.left = add(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            root.right = add(root.right, e);
        }

        return root;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    public boolean contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Invalid element.");
        }
        return contains(root, e);
    }

    private boolean contains(Node root, E e) {
        if (root == null) {
            return false;
        }

        if (e.compareTo(root.e) < 0) {
            return contains(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            return contains(root.right, e);
        } else {
            return true;
        }
    }

    /**
     * Time complexity: O(N)
     */
    public E[] preOrderTraverse() {
        ArrayList<E> arrayList = new ArrayList<>();
        preOrderTraverse(root, arrayList);

        E[] array = (E[])new Comparable[size];
        for (int i = 0; i < size; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    private void preOrderTraverse(Node root, ArrayList<E> arrayList) {
        if (root == null) {
            return;
        }

        arrayList.add(root.e);
        preOrderTraverse(root.left, arrayList);
        preOrderTraverse(root.right, arrayList);
    }

    /**
     * Time complexity: O(N)
     */
    public E[] inOrderTraverse() {
        ArrayList<E> arrayList = new ArrayList<>();
        inOrderTraverse(root, arrayList);

        E[] array = (E[])new Comparable[size];
        for (int i = 0; i < size; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    private void inOrderTraverse(Node root, ArrayList<E> arrayList) {
        if (root == null) {
            return;
        }

        inOrderTraverse(root.left, arrayList);
        arrayList.add(root.e);
        inOrderTraverse(root.right, arrayList);
    }

    /**
     * Time complexity: O(N)
     */
    public E[] postOrderTraverse() {
        ArrayList<E> arrayList = new ArrayList<>();
        postOrderTraverse(root, arrayList);

        E[] array = (E[])new Comparable[size];
        for (int i = 0; i < size; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    private void postOrderTraverse(Node root, ArrayList<E> arrayList) {
        if (root == null) {
            return;
        }

        postOrderTraverse(root.left, arrayList);
        postOrderTraverse(root.right, arrayList);
        arrayList.add(root.e);
    }

    /**
     * Time complexity: O(N)
     */
    public E[] preOrderTraverseNotRecursive() {
        E[] array = (E[])new Comparable[size];
        if (root == null) {
            return array;
        }

        Stack<Node> stack = new LinkedListStack<>();
        stack.push(root);
        int index = 0;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            array[index ++] = cur.e;

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }

        }

        return array;
    }

    /**
     * Time complexity: O(N)
     */
    public E[] levelOrderTraverse() {
        E[] array = (E[])new Comparable[size];
        if (root == null) {
            return array;
        }

        Queue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        int index = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.dequeue();
            array[index ++] = cur.e;

            if (cur.left != null) {
                queue.enqueue(cur.left);
            }
            if (cur.right != null) {
                queue.enqueue(cur.right);
            }
        }

        return array;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    public E getMin() {
        if(size == 0) {
            throw new NoSuchElementException("Binary search tree is empty.");
        }
        return getMin(root).e;
    }

    private Node getMin(Node root) {
        if (root.left == null) {
            return root;
        }

        return getMin(root.left);
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    public E getMax() {
        if(size == 0) {
            throw new NoSuchElementException("Binary search tree is empty.");
        }
        return getMax(root).e;
    }

    private Node getMax(Node root) {
        if (root.right == null) {
            return root;
        }

        return getMax(root.right);
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    public E removeMin() {
        E min = getMin();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node root) {
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
     * Worst time complexity: O(N)
     */
    public E removeMax() {
        E max = getMax();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node root) {
        if (root.right == null) {
            Node retNode = root.left;
            root.left = null;
            size --;
            return retNode;
        }

        root.right = removeMax(root.right);
        return root;
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    public void removeElement(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Remove failed. Invalid element.");
        }
        root = removeElement(root, e);
    }

    private Node removeElement(Node root, E e) {
        if (root == null) {
            return null;
        }

        if (e.compareTo(root.e) < 0) {
            root.left = removeElement(root.left, e);
            return root;
        }
        if (e.compareTo(root.e) > 0) {
            root.right = removeElement(root.right, e);
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
//        size ++;
        successor.left = root.left;
        root.left = root.right = null;
//        size --;
        return successor;
    }

    /**
     * Time complexity: O(1)
     */
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        genBSTString(root, 0, res);
        return res.toString();
    }

    private void genBSTString(Node root, int depth, StringBuilder res) {
        if (root == null) {
            res.append(genDepthString(depth)).append("NULL\n");
            return;
        }

        res.append(genDepthString(depth)).append(root.e).append("\n");
        genBSTString(root.left, depth + 1, res);
        genBSTString(root.right, depth + 1, res);
    }

    private String genDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}

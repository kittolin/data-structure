package heap;

import array.DynamicArray;

import java.util.NoSuchElementException;

public class MinHeap<E extends Comparable<E>> {

    private DynamicArray<E> array;

    public MinHeap(int capacity) {
        array = new DynamicArray<>(capacity);
    }

    public MinHeap() {
        this(10);
    }

    /**
     * Heapify. Loop from the last non-leaf element to the root.
     * Time complexity: O(n)
     */
    public MinHeap(E[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Construct failed. Argument 'arr' is null.");
        }

        array = new DynamicArray<>(arr);
        if (array.getSize() > 1) {
            for (int i = parent(array.getSize() - 1); i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * Time complexity: O(logN)
     */
    public void add(E e) {
        array.addLast(e);
        siftUp(array.getSize() - 1);
    }

    /**
     * Time complexity: O(1)
     */
    public E getMin() {
        if (array.isEmpty()) {
            throw new NoSuchElementException("Cannot getMin from an empty heap.");
        }
        return array.get(0);
    }

    /**
     * Time complexity: O(logN)
     */
    public E extractMin() {
        E ret = getMin();
        array.swap(0, array.getSize() - 1);
        array.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * Time complexity: O(logN)
     */
    public E replace(E e) {
        E ret = getMin();
        array.set(0, e);
        siftDown(0);
        return ret;
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Index-0 element doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        int leftChild = index * 2 + 1;
        return leftChild < array.getSize() ? leftChild : -1;
    }

    private int rightChild(int index) {
        int rightChild = index * 2 + 2;
        return rightChild < array.getSize() ? rightChild : -1;
    }

    private void siftUp(int index) {
        while (index > 0 && array.get(index).compareTo(array.get(parent(index))) < 0) {
            array.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * The parent element compares to the min element of its children.
     */
    private void siftDown(int index) {
        while (leftChild(index) < array.getSize()) {
            int min = leftChild(index);
            if (rightChild(index) < array.getSize() && rightChild(index) < leftChild(index)) {
                min = rightChild(index);
            }

            if (array.get(index).compareTo(array.get(min)) <= 0) {
                break;
            }

            array.swap(index, min);
            index = min;
        }
    }

}

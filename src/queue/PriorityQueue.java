package queue;

import heap.MinHeap;

import java.util.NoSuchElementException;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MinHeap<E> minHeap;

    public PriorityQueue(int capacity) {
        minHeap = new MinHeap<>(capacity);
    }

    public PriorityQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return minHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    /**
     * Time complexity: O(logN)
     */
    @Override
    public void enqueue(E e) {
        minHeap.add(e);
    }

    /**
     * Time complexity: O(logN)
     */
    @Override
    public E dequeue() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue.");
        }
        return minHeap.extractMin();
    }

    /**
     * Time complexity: O(1)
     */
    @Override
    public E getFront() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return minHeap.getMin();
    }

}

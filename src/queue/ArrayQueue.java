package queue;

import array.DynamicArray;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

    private DynamicArray<E> array;

    public ArrayQueue(int capacity) {
        array = new DynamicArray<>(capacity);
    }

    public ArrayQueue() {
        this(10);
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: capacity = %d, size = %d.", array.getCapacity(), array.getSize()));
        res.append("front [");
        for(int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if(i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    /**
     * Amortized time complexity: O(1)
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * Time complexity: O(n), can use loop queue to optimize to O(1).
     */
    @Override
    public E dequeue() {
        if(array.isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue.");
        }
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        if(array.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return array.getFirst();
    }

}

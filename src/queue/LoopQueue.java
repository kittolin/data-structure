package queue;

import java.util.NoSuchElementException;

public class LoopQueue<E> implements Queue<E> {

    private E[] array;
    private int front, tail;
    private int size;

    /**
     * front == tail means that queue is empty, (tail + 1) % array.length == front means that queue is full.
     * So waste space of an element.
     */
    public LoopQueue(int capacity) {
        array = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return array.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * Amortized time complexity: O(1)
     */
    @Override
    public void enqueue(E e) {
        if((tail + 1) % array.length == front) {
            resize(getCapacity() * 2);
        }

        array[tail] = e;
        tail = (tail + 1) % array.length;
        size ++;
    }

    /**
     * Amortized time complexity: O(1)
     */
    @Override
    public E dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue.");
        }

        E ret = array[front];
        array[front] = null;  // loitering object
        front = (front + 1) % array.length;
        size --;

        if(size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    /**
     *  Time complexity: O(1)
     */
    @Override
    public E getFront() {
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return array[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: capacity = %d, size = %d.\n", getCapacity(), getSize()));
        res.append("front [");
        for(int i = front; i != tail; i = (i + 1) % array.length) {
            res.append(array[i]);
            if((i + 1) % array.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    private void resize(int newCapacity) {
        if(newCapacity <= size) {
            throw new IllegalArgumentException("Resize failed. Require newCapacity > size.");
        }
        System.out.println(String.format("Resize %s to %s.", getCapacity(), newCapacity));

        E[] newArray = (E[])new Object[newCapacity + 1];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[(i + front) % array.length];
        }
        array = newArray;
        front = 0;
        tail = size;
    }

}

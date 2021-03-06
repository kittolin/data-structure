package stack;

import array.DynamicArray;

import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {

    private DynamicArray<E> arr;

    public ArrayStack(int capacity) {
        arr = new DynamicArray<>(capacity);
    }

    public ArrayStack() {
        this(10);
    }

    public int getCapacity() {
        return arr.getCapacity();
    }

    @Override
    public int getSize() {
        return arr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    /**
     * Amortized time complexity: O(1)
     */
    @Override
    public void push(E e) {
        arr.addLast(e);
    }

    /**
     * Amortized time complexity: O(1)
     */
    @Override
    public E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return arr.removeLast();
    }

    /**
     * Time complexity: O(1)
     */
    @Override
    public E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return arr.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack: capacity = %d, size = %d\n", getCapacity(), getSize()));
        res.append("[");
        for(int i = 0; i < getSize(); i++) {
            res.append(arr.get(i));
            if(i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

}

package stack;

import linkedlist.LinkedList;

import java.util.EmptyStackException;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * Time complexity: O(1)
     */
    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    /**
     * Time complexity: O(1)
     */
    @Override
    public E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return linkedList.removeFirst();
    }

    /**
     * Time complexity: O(1)
     */
    @Override
    public E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack: size = %d\n", getSize()));
        res.append("top ");
        res.append(linkedList);
        return res.toString();
    }

}

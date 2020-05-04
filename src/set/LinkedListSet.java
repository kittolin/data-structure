package set;

import linkedlist.LinkedList;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet() {
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
     * Time complexity: O(n)
     */
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    /**
     * Time complexity: O(n)
     */
    @Override
    public void add(E e) {
        if (!contains(e)) {
            linkedList.addFirst(e);
        }
    }

    /**
     * Time complexity: O(n)
     */
    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

}

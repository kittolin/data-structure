package linkedlist;

public class LinkedList<E> {

    private class Node {

        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null) {
            res.append(cur).append("->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    /**
     * Time Complexity: O(n)
     */
    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Invalid index.");
        }

        Node prev = dummyHead;
        for(int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size ++;
    }

    /**
     * Time Complexity: O(1)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * Time Complexity: O(n)
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * Time Complexity: O(n)
     */
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Invalid index.");
        }

        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    /**
     * Time Complexity: O(1)
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * Time Complexity: O(n)
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * Time Complexity: O(n)
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while(cur != null) {
            if(cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    /**
     * Time Complexity: O(n)
     */
    public void set(int index , E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Invalid index.");
        }

        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    /**
     * Time Complexity: O(n)
     */
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Invalid index.");
        }

        Node prev = dummyHead;
        for(int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;  // loitering object
        size --;

        return ret.e;
    }

    /**
     * Time Complexity: O(1)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Time Complexity: O(n)
     */
    public E removerLast() {
        return remove(size - 1);
    }

    /**
     * Time Complexity: O(n)
     */
    public void removeElement(E e) {
        Node prev = dummyHead;
        while(prev.next != null) {
            if(prev.next.e.equals(e)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                size --;
                break;
            }
            prev = prev.next;
        }
    }

    /**
     * @author: Kitto
     * Time Complexity: O(n)
     */
    public void removeElements(E e) {
        Node prev = dummyHead;
        while(prev.next != null) {
            if(prev.next.e.equals(e)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                size --;
            } else {
                prev = prev.next;
            }
        }
    }


    public void removeElementsRecursively(E e) {
        removeElementsRecursively(dummyHead.next, e);
    }

    private Node removeElementsRecursively(Node head, E e) {
        if(head == null) {
            return null;
        }

        head.next = removeElementsRecursively(head.next, e);
        if(head.e.equals(e)) {
            size --;
            return head.next;
        }
        return head;
    }

}

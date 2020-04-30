package linkedlist;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList<Student> linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList<>();
        linkedList.addLast(new Student(1, 90));
        linkedList.addLast(new Student(2, 95));
        linkedList.addLast(new Student(2, 95));
    }

    @Test
    public void testGetSize() {
        assertEquals(3, linkedList.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(linkedList.toString());
    }

    @Test
    public void testAdd() {
        linkedList.add(1, new Student(3, 100));
        assertEquals(4, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testAddFirst() {
        linkedList.addFirst(new Student(3, 100));
        assertEquals(4, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testAddLast() {
        linkedList.addLast(new Student(3, 100));
        assertEquals(4, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testGet() {
        Student student = linkedList.get(1);
        assertEquals(2, student.getNumber());
    }

    @Test
    public void testGetFirst() {
        Student student = linkedList.getFirst();
        assertEquals(1, student.getNumber());
    }

    @Test
    public void testGetLast() {
        Student student = linkedList.getLast();
        assertEquals(2, student.getNumber());
    }

    @Test
    public void testContains() {
        assertTrue(linkedList.contains(new Student(2, 95)));
        assertFalse(linkedList.contains(new Student(3, 100)));
    }

    @Test
    public void testSet() {
        linkedList.set(1, new Student(3, 100));
        assertEquals(3, linkedList.get(1).getNumber());
    }

    @Test
    public void testRemove() {
        Student student = linkedList.remove(1);
        assertEquals(2, student.getNumber());
        assertEquals(2, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testRemoveFirst() {
        Student student = linkedList.removeFirst();
        assertEquals(1, student.getNumber());
        assertEquals(2, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testRemoverLast() {
        Student student = linkedList.removerLast();
        assertEquals(2, student.getNumber());
        assertEquals(2, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testRemoveElement() {
        linkedList.removeElement(new Student(3, 100));
        assertEquals(3, linkedList.getSize());
        System.out.println(linkedList.toString());

        linkedList.removeElement(new Student(2, 95));
        assertEquals(2, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testRemoveElements() {
        linkedList.removeElements(new Student(3, 100));
        assertEquals(3, linkedList.getSize());
        System.out.println(linkedList.toString());

        linkedList.removeElements(new Student(2, 95));
        assertEquals(1, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

    @Test
    public void testRemoveElementsRecursively() {
        linkedList.removeElementsRecursively(new Student(3, 100));
        assertEquals(3, linkedList.getSize());
        System.out.println(linkedList.toString());

        linkedList.removeElementsRecursively(new Student(2, 95));
        assertEquals(1, linkedList.getSize());
        System.out.println(linkedList.toString());
    }

}

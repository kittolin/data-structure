package stack;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import java.util.EmptyStackException;
import java.util.Random;

import static org.junit.Assert.*;

public class LinkedListStackTest {

    private LinkedListStack<Student> linkedListStack;

    @Before
    public void setUp() throws Exception {
        linkedListStack = new LinkedListStack<>();
        linkedListStack.push(new Student(1, 90));
        linkedListStack.push(new Student(2, 95));
    }

    @Test
    public void testGetSize() {
        assertEquals(2, linkedListStack.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(linkedListStack.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(linkedListStack.toString());
    }

    @Test
    public void testPush() {
        linkedListStack.push(new Student(3, 90));
        assertEquals(3, linkedListStack.getSize());
        System.out.println(linkedListStack.toString());
    }

    @Test(expected = EmptyStackException.class)
    public void testPop() throws EmptyStackException {
        Student student = linkedListStack.pop();
        assertEquals(2, student.getNumber());
        assertEquals(1, linkedListStack.getSize());
        System.out.println(linkedListStack.toString());

        student = linkedListStack.pop();
        assertEquals(1, student.getNumber());
        assertEquals(0, linkedListStack.getSize());
        System.out.println(linkedListStack.toString());

        linkedListStack.pop();
        fail("Pop operation has executed, but the linkedlist stack is empty.");
    }

    @Test(expected = EmptyStackException.class)
    public void testPeek() throws EmptyStackException {
        Student student = linkedListStack.peek();
        assertEquals(2, student.getNumber());
        assertEquals(2, linkedListStack.getSize());

        while(!linkedListStack.isEmpty()) {
            linkedListStack.pop();
        }

        linkedListStack.peek();
        fail("Peek operation has executed, but the linkedlist stack is empty.");
    }

    /**
     * LinkedListStack contains many new operations which takes long.
     */
    @Test
    public void testPerformance() {
        int opCount = 1000000;
        Random random = new Random();

        for(int i = 0; i < opCount; i++) {
            linkedListStack.push(new Student(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE)));
        }
        while(!linkedListStack.isEmpty()) {
            linkedListStack.pop();
        }
    }

}

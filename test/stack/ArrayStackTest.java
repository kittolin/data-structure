package stack;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class ArrayStackTest {

    private ArrayStack<Student> arrayStack;

    @Before
    public void setUp() throws Exception {
        arrayStack = new ArrayStack<>(3);
        arrayStack.push(new Student(1, 90));
        arrayStack.push(new Student(2, 95));
    }

    @Test
    public void testGetCapacity() {
        assertEquals(3, arrayStack.getCapacity());
    }

    @Test
    public void testGetSize() {
        assertEquals(2, arrayStack.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(arrayStack.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(arrayStack.toString());
    }

    @Test
    public void testPush() {
        arrayStack.push(new Student(3, 90));
        assertEquals(3, arrayStack.getCapacity());
        assertEquals(3, arrayStack.getSize());
        System.out.println(arrayStack.toString());

        arrayStack.push(new Student(4, 100));
        assertEquals(6, arrayStack.getCapacity());
        assertEquals(4, arrayStack.getSize());
        System.out.println(arrayStack.toString());
    }

    @Test(expected = EmptyStackException.class)
    public void testPop() throws EmptyStackException {
        Student student = arrayStack.pop();
        assertEquals(2, student.getNumber());
        assertEquals(3, arrayStack.getCapacity());
        assertEquals(1, arrayStack.getSize());
        System.out.println(arrayStack.toString());

        student = arrayStack.pop();
        assertEquals(1, student.getNumber());
        assertEquals(1, arrayStack.getCapacity());
        assertEquals(0, arrayStack.getSize());
        System.out.println(arrayStack.toString());

        arrayStack.pop();
        fail("Pop operation has executed, but the array stack is empty.");
    }

    @Test(expected = EmptyStackException.class)
    public void testPeek() throws EmptyStackException {
        Student student = arrayStack.peek();
        assertEquals(2, student.getNumber());
        assertEquals(3, arrayStack.getCapacity());
        assertEquals(2, arrayStack.getSize());

        while(!arrayStack.isEmpty()) {
            arrayStack.pop();
        }

        arrayStack.peek();
        fail("Peek operation has executed, but the array stack is empty.");
    }

}

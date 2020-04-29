package queue;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class LinkedListQueueTest {

    private LinkedListQueue<Student> linkedListQueue;

    @Before
    public void setUp() throws Exception {
        linkedListQueue = new LinkedListQueue<>();
        linkedListQueue.enqueue(new Student(1, 90));
        linkedListQueue.enqueue(new Student(2, 95));
    }

    @Test
    public void testGetSize() {
        assertEquals(2, linkedListQueue.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(linkedListQueue.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(linkedListQueue.toString());
    }

    @Test
    public void testEnqueue() {
        linkedListQueue.enqueue(new Student(3, 90));
        assertEquals(3, linkedListQueue.getSize());
        System.out.println(linkedListQueue.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeue() throws NoSuchElementException {
        Student student = linkedListQueue.dequeue();
        assertEquals(1, student.getNumber());
        assertEquals(1, linkedListQueue.getSize());
        System.out.println(linkedListQueue.toString());

        student = linkedListQueue.dequeue();
        assertEquals(2, student.getNumber());
        assertEquals(0, linkedListQueue.getSize());
        System.out.println(linkedListQueue.toString());

        linkedListQueue.dequeue();
        fail("Dequeue operation has executed, but the linkedlist queue is empty.");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFront() throws NoSuchElementException {
        Student student = linkedListQueue.getFront();
        assertEquals(1, student.getNumber());
        assertEquals(2, linkedListQueue.getSize());

        while(!linkedListQueue.isEmpty()) {
            linkedListQueue.dequeue();
        }

        linkedListQueue.getFront();
        fail("Front operation has executed, but the linkedlist queue is empty.");
    }

    /**
     * LinkedListQueue contains many new operations which takes long.
     */
    @Test
    public void testDequeuePerformance() {
        int opCount = 10000000;
        Random random = new Random();

        for(int i = 0; i < opCount; i++) {
            linkedListQueue.enqueue(new Student(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE)));
        }
        while(!linkedListQueue.isEmpty()) {
            linkedListQueue.dequeue();
        }
    }

}

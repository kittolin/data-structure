package queue;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    private ArrayQueue<Student> arrayQueue;

    @Before
    public void setUp() throws Exception {
        arrayQueue = new ArrayQueue<>(3);
        arrayQueue.enqueue(new Student(1, 90));
        arrayQueue.enqueue(new Student(2, 95));
    }

    @Test
    public void testGetCapacity() {
        assertEquals(3, arrayQueue.getCapacity());
    }

    @Test
    public void testGetSize() {
        assertEquals(2, arrayQueue.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(arrayQueue.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(arrayQueue.toString());
    }

    @Test
    public void testEnqueue() {
        arrayQueue.enqueue(new Student(3, 90));
        assertEquals(3, arrayQueue.getCapacity());
        assertEquals(3, arrayQueue.getSize());
        System.out.println(arrayQueue.toString());

        arrayQueue.enqueue(new Student(4, 100));
        assertEquals(6, arrayQueue.getCapacity());
        assertEquals(4, arrayQueue.getSize());
        System.out.println(arrayQueue.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeue() throws NoSuchElementException {
        Student student = arrayQueue.dequeue();
        assertEquals(1, student.getNumber());
        assertEquals(3, arrayQueue.getCapacity());
        assertEquals(1, arrayQueue.getSize());
        System.out.println(arrayQueue.toString());

        student = arrayQueue.dequeue();
        assertEquals(2, student.getNumber());
        assertEquals(1, arrayQueue.getCapacity());
        assertEquals(0, arrayQueue.getSize());
        System.out.println(arrayQueue.toString());

        arrayQueue.dequeue();
        fail("Dequeue operation has executed, but the array queue is empty.");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFront() throws NoSuchElementException {
        Student student = arrayQueue.getFront();
        assertEquals(1, student.getNumber());
        assertEquals(3, arrayQueue.getCapacity());
        assertEquals(2, arrayQueue.getSize());

        while(!arrayQueue.isEmpty()) {
            arrayQueue.dequeue();
        }

        arrayQueue.getFront();
        fail("Front operation has executed, but the array queue is empty.");
    }

    @Test
    public void testDequeuePerformance() {
        int opCount = 100000;
        Random random = new Random();

        for(int i = 0; i < opCount; i++) {
            arrayQueue.enqueue(new Student(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE)));
        }
        while(!arrayQueue.isEmpty()) {
            arrayQueue.dequeue();
        }
    }

}

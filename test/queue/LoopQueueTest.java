package queue;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class LoopQueueTest {

    private LoopQueue<Student> loopQueue;

    @Before
    public void setUp() throws Exception {
        loopQueue = new LoopQueue<>(3);
        loopQueue.enqueue(new Student(1, 90));
        loopQueue.enqueue(new Student(2, 95));
    }

    @Test
    public void testGetCapacity() {
        assertEquals(3, loopQueue.getCapacity());
    }

    @Test
    public void testGetSize() {
        assertEquals(2, loopQueue.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(loopQueue.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(loopQueue.toString());
    }

    @Test
    public void testEnqueue() {
        loopQueue.enqueue(new Student(3, 90));
        assertEquals(3, loopQueue.getCapacity());
        assertEquals(3, loopQueue.getSize());
        System.out.println(loopQueue.toString());

        loopQueue.enqueue(new Student(4, 100));
        assertEquals(6, loopQueue.getCapacity());
        assertEquals(4, loopQueue.getSize());
        System.out.println(loopQueue.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeue() throws NoSuchElementException {
        Student student = loopQueue.dequeue();
        assertEquals(1, student.getNumber());
        assertEquals(3, loopQueue.getCapacity());
        assertEquals(1, loopQueue.getSize());
        System.out.println(loopQueue.toString());

        student = loopQueue.dequeue();
        assertEquals(2, student.getNumber());
        assertEquals(1, loopQueue.getCapacity());
        assertEquals(0, loopQueue.getSize());
        System.out.println(loopQueue.toString());

        loopQueue.dequeue();
        fail("Dequeue operation has executed, but the array queue is empty.");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFront() throws NoSuchElementException {
        Student student = loopQueue.getFront();
        assertEquals(1, student.getNumber());
        assertEquals(3, loopQueue.getCapacity());
        assertEquals(2, loopQueue.getSize());

        while(!loopQueue.isEmpty()) {
            loopQueue.dequeue();
        }

        loopQueue.getFront();
        fail("Front operation has executed, but the array queue is empty.");
    }

    @Test
    public void testDequeuePerformance() {
        int opCount = 100000;
        Random random = new Random();

        for(int i = 0; i < opCount; i++) {
            loopQueue.enqueue(new Student(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE)));
        }
        while(!loopQueue.isEmpty()) {
            loopQueue.dequeue();
        }
    }

}

package array;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pojo.Student;

import java.util.Random;

import static org.junit.Assert.*;

public class DynamicArrayTest {

    private DynamicArray<Student> dynamicArray;

    @Before
    public void setUp() throws Exception {
        Student[] students = {
            new Student(1, 90),
            new Student(2, 95),
            new Student(1, 90),
            new Student(4, 100)
        };
        dynamicArray = new DynamicArray<Student>(students);
    }

    @Test
    public void testGetCapacity() {
        assertEquals(4, dynamicArray.getCapacity());
    }

    @Test
    public void testGetLength() {
        assertEquals(4, dynamicArray.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(dynamicArray.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(dynamicArray.toString());
    }

    @Test
    public void testAdd() {
        dynamicArray.add(1, new Student(5, 70));
        assertEquals(8, dynamicArray.getCapacity());
        assertEquals(5, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());
    }

    @Test
    public void testAddFirst() {
        dynamicArray.addFirst(new Student(5, 70));
        assertEquals(8, dynamicArray.getCapacity());
        assertEquals(5, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());
    }

    @Test
    public void testAddLast() {
        dynamicArray.addLast(new Student(5, 70));
        assertEquals(8, dynamicArray.getCapacity());
        assertEquals(5, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());
    }

    @Test
    public void testAddArray() {
        Random random = new Random();
        Student[] students = new Student[100];
        for(int i = 0; i < students.length; i++) {
            students[i] = new Student(random.nextInt(1000), random.nextInt(100));
        }
        dynamicArray.add(students);
        assertEquals(108, dynamicArray.getCapacity());
        assertEquals(104, dynamicArray.getSize());
    }

    @Test
    public void testSet() {
        dynamicArray.set(2, new Student(3, 91));
        System.out.println(dynamicArray.toString());
    }

    @Test
    public void testGet() {
        Student student = dynamicArray.get(2);
        assertEquals(1, student.getNumber());
        assertEquals(90, student.getScore());
    }

    @Test
    public void testGetFirst() {
        Student student = dynamicArray.getFirst();
        assertEquals(1, student.getNumber());
        assertEquals(90, student.getScore());
    }

    @Test
    public void testGetLast() {
        Student student = dynamicArray.getLast();
        assertEquals(4, student.getNumber());
        assertEquals(100, student.getScore());
    }

    @Test
    public void testFind() {
        assertEquals(-1, dynamicArray.find(new Student(1, 91)));
        assertEquals(0, dynamicArray.find(new Student(1, 90)));
    }

    @Test
    public void testFindAll() {
        int[] indexes = dynamicArray.findAll(new Student(1, 90));
        assertEquals(2, indexes.length);
        indexes = dynamicArray.findAll(new Student(1, 91));
        assertEquals(0 ,indexes.length);
    }

    @Test
    public void testContains() {
        assertFalse(dynamicArray.contains(new Student(1 ,91)));
        assertTrue(dynamicArray.contains(new Student(1, 90)));
    }

    @Test
    public void testRemove() {
        dynamicArray.remove(2);
        assertEquals(4, dynamicArray.getCapacity());
        assertEquals(3, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());

        dynamicArray.remove(0);
        assertEquals(4, dynamicArray.getCapacity());
        assertEquals(2, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());

        dynamicArray.remove(0);
        assertEquals(2, dynamicArray.getCapacity());
        assertEquals(1, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());

        dynamicArray.remove(0);
        assertEquals(1, dynamicArray.getCapacity());
        assertEquals(0, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());
    }

    @Test
    public void testRemoveFirst() {
        Student student = dynamicArray.removeFirst();
        assertEquals(1, student.getNumber());
        assertEquals(90, student.getScore());
        assertEquals(4, dynamicArray.getCapacity());
        assertEquals(3, dynamicArray.getSize());
    }

    @Test
    public void testRemoveLast() {
        Student student = dynamicArray.removeLast();
        assertEquals(4, student.getNumber());
        assertEquals(100, student.getScore());
        assertEquals(4, dynamicArray.getCapacity());
        assertEquals(3, dynamicArray.getSize());
    }

    @Test
    public void testRemoveElement() {
        int index = dynamicArray.removeElement(new Student(1, 91));
        assertEquals(-1, index);
        assertEquals(4, dynamicArray.getCapacity());
        assertEquals(4, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());

        index = dynamicArray.removeElement(new Student(1, 90));
        assertEquals(0 ,index);
        assertEquals(4, dynamicArray.getCapacity());
        assertEquals(3, dynamicArray.getSize());
        System.out.println(dynamicArray.toString());
    }

    @Ignore
    public void testRemoveAllElements() {
        dynamicArray.removeAllElements(new Student(1, 90));
        System.out.println(dynamicArray.toString());
    }

}

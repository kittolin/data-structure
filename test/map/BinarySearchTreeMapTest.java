package map;

import org.junit.Before;
import org.junit.Test;
import pojo.Student;

import java.util.Random;

import static org.junit.Assert.*;

public class BinarySearchTreeMapTest {

    private Map<Integer, Student> map;

    @Before
    public void setUp() throws Exception {
        map = new BinarySearchTreeMap<>();
        map.add(1, new Student(1, 100));
        map.add(2, new Student(2, 95));
        map.add(3, new Student(3, 90));
    }

    @Test
    public void testGetSize() {
        assertEquals(3, map.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(map.isEmpty());
    }

    @Test
    public void testAdd() {
        assertEquals(new Student(3, 90), map.get(3));
        map.add(3, new Student(3, 91));
        assertEquals(3, map.getSize());
        assertEquals(new Student(3, 91), map.get(3));

        map.add(4, new Student(4, 85));
        assertEquals(4, map.getSize());
    }

    @Test
    public void testRemove() {
        assertNull(map.remove(4));
        assertEquals(3, map.getSize());

        assertEquals(new Student(3, 90), map.remove(3));
        assertEquals(2, map.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSet() throws IllegalArgumentException {
        assertEquals(new Student(3, 90), map.get(3));
        map.set(3, new Student(3, 91));
        assertEquals(new Student(3, 91), map.get(3));

        map.set(4, new Student(4, 85));
        fail("Set operation has executed, but the key doesn't exist.");
    }

    @Test
    public void testGet() {
        assertNull(map.get(4));
        assertEquals(new Student(1, 100), map.get(1));
        assertEquals(new Student(2, 95), map.get(2));
    }

    @Test
    public void testContains() {
        assertFalse(map.contains(4));
        assertTrue(map.contains(3));
    }

    @Test
    public void testAddPerformance() {
        int opCount = 100000;
        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            Student student = new Student(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE));
            map.add(random.nextInt(Integer.MAX_VALUE), student);
        }

        System.out.println(map.getSize());
    }

}

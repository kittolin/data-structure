package set;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class LinkedListSetTest {

    private Set<Integer> set;

    @Before
    public void setUp() throws Exception {
        set = new LinkedListSet<>();
        set.add(30);
        set.add(20);
        set.add(50);
    }

    @Test
    public void testGetSize() {
        assertEquals(3, set.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(set.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(set.contains(40));
        assertTrue(set.contains(30));
    }

    @Test
    public void testAdd() {
        set.add(30);
        assertEquals(3, set.getSize());

        assertFalse(set.contains(40));
        set.add(40);
        assertTrue(set.contains(40));
        assertEquals(4, set.getSize());
    }

    @Test
    public void testRemove() {
        set.remove(40);
        assertEquals(3, set.getSize());

        set.remove(30);
        assertEquals(2, set.getSize());
    }

    @Test
    public void testAddPerformance() {
        int opCount = 100000;
        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            set.add(random.nextInt(Integer.MAX_VALUE));
        }

        System.out.println(set.getSize());
    }

}

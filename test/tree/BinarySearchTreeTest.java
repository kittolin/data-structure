package tree;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> binarySearchTree;

    @Before
    public void setUp() throws Exception {
        binarySearchTree = new BinarySearchTree<>();
        int[] elements = {30, 20, 50, 15, 25, 70, 18, 60, 80};
        for (int element : elements) {
            binarySearchTree.add(element);
        }
    }

    @Test
    public void testGetSize() {
        assertEquals(9, binarySearchTree.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(binarySearchTree.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(binarySearchTree.toString());
    }

    @Test
    public void testAdd() {
        binarySearchTree.add(30);
        assertEquals(9, binarySearchTree.getSize());

        binarySearchTree.add(10);
        assertEquals(10, binarySearchTree.getSize());

        binarySearchTree.add(40);
        assertEquals(11, binarySearchTree.getSize());

        System.out.println(binarySearchTree.toString());
    }

    @Test
    public void testContains() {
        assertFalse(binarySearchTree.contains(40));
        assertTrue(binarySearchTree.contains(25));
    }

    @Test
    public void testPreOrderTraverse() {
        Integer[] expecteds = {30, 20, 15, 18, 25, 50, 70, 60, 80};
        assertArrayEquals(expecteds, binarySearchTree.preOrderTraverse());
    }

    @Test
    public void testInOrderTraverse() {
        Integer[] expecteds = {15, 18, 20, 25, 30, 50, 60, 70, 80};
        assertArrayEquals(expecteds, binarySearchTree.inOrderTraverse());
    }

    @Test
    public void testPostOrderTraverse() {
        Integer[] expecteds = {18, 15, 25, 20, 60, 80, 70, 50, 30};
        assertArrayEquals(expecteds, binarySearchTree.postOrderTraverse());
    }

    @Test
    public void testPreOrderTraverseNotRecursive() {
        Integer[] expecteds = {30, 20, 15, 18, 25, 50, 70, 60, 80};
        assertArrayEquals(expecteds, binarySearchTree.preOrderTraverseNotRecursive());
    }

    @Test
    public void testLevelOrderTraverse() {
        Integer[] expecteds = {30, 20, 50, 15, 25, 70, 18, 60, 80};
        assertArrayEquals(expecteds, binarySearchTree.levelOrderTraverse());
    }

    @Test
    public void testGetMin() {
        assertEquals(new Integer(15), binarySearchTree.getMin());
    }

    @Test
    public void testGetMax() {
        assertEquals(new Integer(80), binarySearchTree.getMax());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveMin() throws NoSuchElementException {
        assertEquals(new Integer(15), binarySearchTree.removeMin());
        assertEquals(8, binarySearchTree.getSize());
        assertEquals(new Integer(18), binarySearchTree.getMin());

        assertEquals(new Integer(18), binarySearchTree.removeMin());
        assertEquals(7, binarySearchTree.getSize());
        assertEquals(new Integer(20), binarySearchTree.getMin());

        System.out.println(binarySearchTree.toString());

        binarySearchTree.clear();
        binarySearchTree.removeMin();
        fail("RemoveMin operation has executed, but the binary search tree is empty.");
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveMax() throws NoSuchElementException {
        assertEquals(new Integer(80), binarySearchTree.removeMax());
        assertEquals(8, binarySearchTree.getSize());
        assertEquals(new Integer(70), binarySearchTree.getMax());

        assertEquals(new Integer(70), binarySearchTree.removeMax());
        assertEquals(7, binarySearchTree.getSize());
        assertEquals(new Integer(60), binarySearchTree.getMax());

        System.out.println(binarySearchTree.toString());

        binarySearchTree.clear();
        binarySearchTree.removeMax();
        fail("RemoveMax operation has executed, but the binary search tree is empty.");
    }

    @Test
    public void testRemoveElement() {
        binarySearchTree.removeElement(40);
        assertEquals(9, binarySearchTree.getSize());

        binarySearchTree.removeElement(20);
        assertEquals(8, binarySearchTree.getSize());
        System.out.println(binarySearchTree.toString());

        binarySearchTree.removeElement(50);
        assertEquals(7, binarySearchTree.getSize());
        System.out.println(binarySearchTree.toString());

        binarySearchTree.removeElement(30);
        assertEquals(6, binarySearchTree.getSize());
        System.out.println(binarySearchTree.toString());

        binarySearchTree.removeElement(25);
        assertEquals(5, binarySearchTree.getSize());
        System.out.println(binarySearchTree.toString());

        assertEquals(new Integer(15), binarySearchTree.getMin());
        assertEquals(new Integer(80), binarySearchTree.getMax());
    }

    @Test
    public void testClear() {
        binarySearchTree.clear();
        assertEquals(0, binarySearchTree.getSize());
        assertTrue(binarySearchTree.isEmpty());
    }

}

package set;

import tree.BinarySearchTree;

public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> binarySearchTree;

    public BinarySearchTreeSet() {
        binarySearchTree = new BinarySearchTree<>();
    }

    @Override
    public int getSize() {
        return binarySearchTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return binarySearchTree.isEmpty();
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    @Override
    public boolean contains(E e) {
        return binarySearchTree.contains(e);
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    @Override
    public void add(E e) {
        binarySearchTree.add(e);
    }

    /**
     * Average time complexity: O(logN)
     * Worst time complexity: O(N)
     */
    @Override
    public void remove(E e) {
        binarySearchTree.removeElement(e);
    }

}

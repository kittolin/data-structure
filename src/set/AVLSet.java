package set;

import tree.AVLTree;

public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element mustn't be null.");
        }
        return avl.contains(e);
    }

    @Override
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element mustn't be null.");
        }
        avl.add(e, null);
    }

    @Override
    public void remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element mustn't be null.");
        }
        avl.remove(e);
    }

}

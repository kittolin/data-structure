package tree;

public class SegmentTree<E> {

    private E[] data;   // 实际存储的数据
    private E[] tree;   // 构建的线段树
    private Merger<E> merger;

    /**
     * 如果 n = 2 ^ k, 则只需要 2n 的空间
     * 最坏情况下, 即 n = 2 ^ k + 1, 则需要 4n 的空间
     */
    public SegmentTree(E[] arr, Merger<E> merger) {
        if (arr == null || merger == null) {
            throw new IllegalArgumentException("Array or merger mustn't be null.");
        }
        if (arr.length == 0) {
            throw new IllegalArgumentException("Required array.length > 0");
        }

        data = (E[])new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     *  Time complexity: O(4n) = O(n)
     */
    private void buildSegmentTree(int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }

        // int mid = (left + right) / 2;
        int mid = (right - left) / 2 + left;  // 防止整数溢出
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, left, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, right);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Invalid index.");
        }
        return data[index];
    }

    /**
     * Time Complexity: O(logN)
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Invalid index.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int left, int right, int queryL, int queryR) {
        if (left == queryL && right == queryR) {
            return tree[treeIndex];
        }

        int mid = (right - left) / 2 + left;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryR <= mid) {
            return query(leftTreeIndex, left, mid, queryL, queryR);
        }
        if (queryL > mid) {
            return query(rightTreeIndex, mid + 1, right, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, left, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, right, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * Time Complexity: O(logN)
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Invalid index");
        }

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int left, int right, int index, E e) {
        if (left == right) {
            tree[treeIndex] = e;
            return;
        }

        int mid = (right - left) / 2 + left;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index <= mid) {
            set(leftTreeIndex, left, mid, index, e);
        } else {
            set(rightTreeIndex, mid + 1, right, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("NULL");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

}

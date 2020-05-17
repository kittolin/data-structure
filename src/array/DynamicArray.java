package array;

import java.util.ArrayList;

public class DynamicArray<E> {

    private E[] arr;
    private int size;

    public DynamicArray(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Construct failed. Require capacity > 0.");
        }

        arr = (E[])new Object[capacity];
        size = 0;
    }

    public DynamicArray(E[] arr) {
        this(arr.length);
        add(arr);
    }

    public DynamicArray() {
        this(10);
    }

    public int getCapacity() {
        return arr.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Dynamic Array: capacity = %d, size = %d\n", arr.length, size));
        res.append("[");
        for(int i = 0; i < size; i++) {
            res.append(arr[i]);
            if(i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * Expand or shrinkage capacity dynamically
     */
    private void resize(int newCapacity) {
        if(newCapacity <= size) {
            throw new IllegalArgumentException("Resize failed. Require newCapacity > size.");
        }
        System.out.println(String.format("Resize %d to %d.", arr.length, newCapacity));

        E[] newArr = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * Time complexity: O(n)
     */
    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Invalid index.");
        }

        if(size == arr.length) {
            resize(2 * arr.length);
        }

        for(int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = e;

        size++;
    }

    /**
     * Time complexity: O(n)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * Amortized time complexity: O(1)
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * @author: Kitto
     * Time complexity: O(n)
     */
    public void add(E[] arr) {
        if(arr == null) {
            throw new IllegalArgumentException("Add failed. Argument 'arr' is null.");
        }

        // if arr.length >>> capacity, method "resize" will be called several times.
        // So resize firstly.
        if(arr.length > this.arr.length - size) {
            resize(2 * this.arr.length + arr.length);
        }

        for(int i = 0; i < arr.length; i++) {
            addLast(arr[i]);
        }
    }

    /**
     * Time complexity: O(1)
     */
    public void set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Invalid index.");
        }
        arr[index] = e;
    }

    /**
     * Time complexity: O(1)
     */
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Invalid index.");
        }
        return arr[index];
    }

    /**
     * Time complexity: O(1)
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * Time complexity: O(1)
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * Time complexity: O(n)
     */
    public int find(E e) {
        for(int i = 0; i < size; i++) {
            if(arr[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @author: Kitto
     * Time complexity: O(n)
     */
    public int[] findAll(E e) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            if(arr[i].equals(e)) {
                list.add(i);
            }
        }

        int[] ret = new int[list.size()];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    /**
     * Time complexity: O(n)
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * Time complexity: O(n)
     */
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Invalid index.");
        }

        E ret = arr[index];
        for(int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }

        size--;
        arr[size] = null;  // loitering objects != memory leak

        // lazy 方式防止复杂度震荡
        if (size == arr.length / 4 && arr.length / 2 != 0) {
            resize(arr.length / 2);
        }

        return ret;
    }

    /**
     * Time complexity: O(n)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Amortized time complexity: O(1)
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * Time complexity: O(n)
     */
    public int removeElement(E e) {
        int index = find(e);
        if(index != -1) {
            remove(index);
        }
        return index;
    }

    /**
     * @author: Kitto
     * Time complexity: O(n²) FIXME The indexes have changed after remove the first matched element.
     */
    public int[] removeAllElements(E e) {
        int[] indexes = findAll(e);
        for (int index: indexes) {
            remove(index);
        }
        return indexes;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Invalid index.");
        }

        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

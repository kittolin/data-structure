package main.array;

import java.util.ArrayList;

public class DynamicArray<E> {

    private E[] arr;
    private int length;

    public DynamicArray(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Construct failed. Require capacity > 0.");
        }

        arr = (E[])new Object[capacity];
        length = 0;
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

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Dynamic Array: capacity = %d, length = %d\n", arr.length, length));
        res.append(String.format("%s%s%s", "[", String.join(", ", (String[])arr), "]"));
        return res.toString();
    }

    /**
     * 动态扩缩容
     */
    private void resize(int newCapacity) {
        if(newCapacity <= length) {
            throw new IllegalArgumentException("Resize failed. Require newCapacity > length.");
        }

        E[] newArr = (E[])new Object[newCapacity];
        for(int i = 0; i < length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * O(n)
     */
    public void add(int index, E e) {
        if(index < 0 || index > length) {
            throw new IllegalArgumentException("Add failed. Invalid index.");
        }

        if(length == arr.length) {
            resize(2 * arr.length);
        }

        for(int i = length - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = e;

        length ++;
    }

    /**
     * O(n)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * amortized time complexity: O(1)
     */
    public void addLast(E e) {
        add(length, e);
    }

    /**
     * O(n)
     */
    public void add(E[] arr) {
        if(arr == null) {
            throw new IllegalArgumentException("Add failed. Argument 'arr' is null.");
        }

        // if arr.length >>> capacity, method "resize" will be called several times.
        // So resize firstly.
        if(arr.length > this.arr.length - length) {
            resize(2 * this.arr.length + arr.length);
        }

        for(int i = 0; i < arr.length; i++) {
            addLast(arr[i]);
        }
    }

    /**
     * O(1)
     */
    public void set(int index, E e) {
        if(index < 0 || index >= length) {
            throw new IllegalArgumentException("Set failed. Invalid index.");
        }
        arr[index] = e;
    }

    /**
     * O(1)
     */
    public E get(int index) {
        if(index < 0 || index >= length) {
            throw new IllegalArgumentException("Get failed. Invalid index.");
        }
        return arr[index];
    }

    /**
     * O(1)
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * O(1)
     */
    public E getLast() {
        return get(length - 1);
    }

    /**
     * O(n)
     */
    public int find(E e) {
        for(int i = 0; i < length; i++) {
            if(arr[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * O(n)
     */
    public int[] findAll(E e) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            if(arr[i].equals(e)) {
                list.add(i);
            }
        }

        Integer[] temp = (Integer[])list.toArray();
        int[] ret = new int[temp.length];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = temp[i];
        }
        return ret;
    }

    /**
     * O(n)
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * O(n)
     */
    public E remove(int index) {
        if(index < 0 || index >= length) {
            throw new IllegalArgumentException("Remove failed. Invalid index.");
        }

        E ret = arr[index];
        for(int i = index; i < length - 1; i++) {
            arr[i] = arr[i + 1];
        }

        length --;
        arr[length] = null;  // loitering objects != memory leak

        // lazy 方式防止复杂度震荡
        if (length == arr.length / 4 && arr.length / 2 != 0) {
            resize(arr.length / 2);
        }

        return ret;
    }

    /**
     * O(n)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * amortized time complexity: O(1)
     */
    public E removeLast() {
        return remove(length - 1);
    }

    /**
     * O(n)
     */
    public int removeElement(E e) {
        int index = find(e);
        if(index != -1) {
            remove(index);
        }
        return index;
    }

    /**
     * O(n²) TODO
     */
    public int[] removeAllElements(E e) {
        int[] indexes = findAll(e);
        for (int index: indexes) {
            remove(index);
        }
        return indexes;
    }

}

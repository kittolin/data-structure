package array;

public class DynamicArray<E> {

    private E[] arr;
    private int length;

    public DynamicArray(int capacity) {
        arr = (E[])new Object[capacity];
        length = 0;
    }

    public DynamicArray(E[] arr) {
        this(arr.length);

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
        res.append(String.format("Dynamic Array: capacity = %d, length = %d\n", getCapacity(), getLength()));
        res.append(String.format("%s%s%s", "[", String.join(", ", (String[])arr), "]"));
        return res.toString();
    }

}

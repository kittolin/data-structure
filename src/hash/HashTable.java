package hash;

import java.util.TreeMap;

public class HashTable<K extends Comparable<K>, V> {

    private static final int[] CAPACITIES = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157,
            98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917,
            25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
    };
    private static final int UPPER = 10;
    private static final int LOWER = 2;

    private TreeMap<K, V>[] table;
    private int size;
    private int capacity;
    private int capacityIndex;

    public HashTable() {
        capacityIndex = 0;
        capacity = CAPACITIES[capacityIndex];
        size = 0;
        table = new TreeMap[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize(int newCapacity) {
        TreeMap<K, V>[] newTable = new TreeMap[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new TreeMap<>();
        }

        capacity = newCapacity;
        for (int i = 0; i < table.length; i++) {
            TreeMap<K, V> map = table[i];
            for (K key: map.keySet()) {
                newTable[hash(key)].put(key, map.get(key));
            }
        }

        table = newTable;
    }

    public int getSize() {
        return size;
    }

    /**
     * Amortized time complexity: O(1)
     */
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }

        TreeMap<K, V> map = table[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size ++;
            if (size >= UPPER * capacity && capacityIndex + 1 < CAPACITIES.length) {
                capacityIndex ++;
                resize(CAPACITIES[capacityIndex]);
            }
        }
    }

    /**
     * Amortized time complexity: O(1)
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }

        V ret = null;
        TreeMap<K, V> map = table[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size --;
            if (size < LOWER * capacity && capacityIndex - 1 >= 0) {
                capacityIndex --;
                resize(CAPACITIES[capacityIndex]);
            }
        }
        return ret;
    }

    /**
     * Time complexity: O(1)
     */
    public void set(K key, V newValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }

        TreeMap<K, V> map = table[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "doesn't exist.");
        }
        map.put(key, newValue);
    }

    /**
     * Time complexity: O(1)
     */
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }
        return table[hash(key)].containsKey(key);
    }

    /**
     * Time complexity: O(1)
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key mustn't be null.");
        }
        return table[hash(key)].get(key);
    }

}

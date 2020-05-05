package map;

public interface Map<K, V> {

    int getSize();
    boolean isEmpty();
    void add(K key, V value);
    V remove(K key);
    void set(K key, V newValue);
    V get(K key);
    boolean contains(K key);

}

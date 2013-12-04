package aufgabe07;

import java.util.Iterator;

/**
 * User: Julian
 * Date: 04.12.13
 * Time: 16:40
 */
public interface SortedDictionary<K extends Comparable<K>, V> {
    /**
     * @return Element with lowest key-value. When Dictionary is empty, 'NULL' is returned
     */
    V min();

    /**
     * @return Element with heighest key-value. When Dictionary is empty, 'NULL' is returned
     */
    V max();

    /**
     * Puts a value into the dictionary. The value gets sorted regarding to the key. If the
     * Key is already in use the old value gets overwritten with this value
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * removes a given key. If the key does not exist, nothing happens
     * @param key
     */
    void remove(K key);

    /**
     * retarded brother of iterator
     * @return ...
     */
    Iterator<V> reverseIterator();

    /**
     * Finds the current value for a given key
     * @param key
     * @return the found value for the given key, if the key does not exists, 'NULL' is returned
     */
    V get(K key);
}

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
     *
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     *
     * @param key
     */
    void remove(K key);

    /**
     * retarded brother of iterator
     * @return ...
     */
    Iterator<V> reverseIterator();
}

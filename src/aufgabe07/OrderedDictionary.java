package aufgabe07;

/**
 * User: Julian
 * Date: 03.12.13
 * Time: 18:28
 */
public interface OrderedDictionary<K, V> {

    void put(K key, V value);

    V get(K key);

    void delete(K key);

}

package aufgabe07;

/**
 * User: Julian
 * Date: 03.12.13
 * Time: 18:39
 */
public class DictionaryImpl<K,V> implements OrderedDictionary<K, V> {

    private final int MAX_READABLE_CHAR = 126;
    private final int MIN_READABLE_CHAR = 33;

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void delete(K key) {

    }

    private enum NodeType{
        Normal,
        PlusInfty,
        MinusInfty
    }

    private class Node{


    }

}

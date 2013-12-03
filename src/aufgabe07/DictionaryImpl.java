package aufgabe07;

/**
 * User: Julian
 * Date: 03.12.13
 * Time: 18:39
 */
public class DictionaryImpl<M> implements OrderedDictionary<String, M> {

    private final int MAX_READABLE_CHAR = 126;
    private final int MIN_READABLE_CHAR = 33;

    @Override
    public void put(String key, M value) {

    }

    @Override
    public M get(String key) {
        return null;
    }

    @Override
    public void delete(String key) {

    }

    private enum NodeType {
        Root,
        Gateway,
        Value
    }


    private class Node{
        public M Value;
        public Node Left;
        public Node Right;
        private Node[] children;

    }

}

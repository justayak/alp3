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

    class Node{
        public M Value;
        public final char Key;
        public final int Position;
        private Node[] children;
        public boolean isLeaf(){
            return this.Value != null;
        }
        public Node(char key){
            this.Key = key;
            this.Position = key - MIN_READABLE_CHAR;
            this.children = (Node[]) new Object[(MAX_READABLE_CHAR - MIN_READABLE_CHAR)];
        }

    }
}

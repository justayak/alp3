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

    private class Node{
        public M Value;
        public final char Key;
        public final int Position;
        private Node[] children;
        public Node Left;
        public Node right;
        public boolean isLeaf(){
            return this.Value != null;
        }
        public Node(char key){
            this.Key = key;
            this.Position = key - MIN_READABLE_CHAR;
            this.children = (Node[]) new Object[(MAX_READABLE_CHAR - MIN_READABLE_CHAR)];
        }
        public M get(String key){
            return this.get(key, 0);
        }
        private M get(String key, int index){
            if (key.length() <= index) return null;
            Node m = this.children[key.charAt(index) - MIN_READABLE_CHAR];
            if (m == null) return null;
            return m.get(key, index + 1);
        }
        public void add(String key, M value){
            this.add(key, 0, value);
        }
        private void add(String key, int index, M value){
            if (key.length() == index - 1){
                this.Value = value;
            }else if(key.length() > index) {

            }
            throw new RuntimeException("joa ne, sollt nicht passiern..");
        }

    }
}

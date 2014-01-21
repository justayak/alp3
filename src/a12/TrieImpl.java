package a12;

/**
 * Unser Trie erlaubt nur Kleinbuchstaben des lateinischen Alphabets btw
 * User: Julian
 * Date: 21.01.14
 * Time: 17:01
 */
public class TrieImpl<T> implements Trie<T> {

    private TrieNode<T> root;

    public TrieImpl() {
        this.root = new TrieNode<T>();
    }

    @Override
    public Trie<T> put(String s, T v) throws ShitIsAlreadyThereException {
        if (s.length() == 0) {
            if (this.root.value == null) this.root.value = v;
            else throw new ShitIsAlreadyThereException();
        } else {
            this.root.put(s, 0, v);
        }
        return this;
    }

    @Override
    public T get(String s) throws ShitNotThereException {
        if (s.length() == 0) {
            if (this.root.value == null) throw new ShitNotThereException();
            else return this.root.value;
        } else {
            return this.root.get(s, 0);
        }
    }

    @Override
    public Trie<T> remove(String s) throws ShitNotThereException {
        if(s.length() == 0){
            if(this.root.value == null) throw new ShitNotThereException();
            else this.root.value = null;
        }else this.root.remove(s,0);
        return this;
    }

    @Override
    public String toString(){
        return this.root.toString();
    }

    /**
     * Rekursive Hilfsklasse. Es ist nur möglich, einen String mit Kleinbuchstaben zu speichern!
     *
     * @param <T>
     */
    private class TrieNode<T> {
        T value = null;
        char character;
        int childrenCount = 0;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.character = c;
        }

        public boolean isDead(){
            return this.childrenCount == 0 && this.value == null;
        }

        public void put(String s, int pos, T v) throws ShitIsAlreadyThereException {
            if (s.length() == pos) {
                if (this.value == null) this.value = v;
                else throw new ShitIsAlreadyThereException();
            } else {
                int p = s.charAt(pos) - 'a';
                if (this.children[p] == null) {
                    this.children[p] = new TrieNode<T>(s.charAt(pos));
                    this.childrenCount += 1;
                }
                this.children[p].put(s, pos + 1, v);
            }
        }

        public T get(String s, int pos) throws ShitNotThereException {
            if (s.length() == pos) {
                if (this.value == null) throw new ShitNotThereException();
                else return this.value;
            } else {
                int p = s.charAt(pos) - 'a';
                if (this.children[p] == null) throw new ShitNotThereException();
                else return (T) this.children[p].get(s, pos + 1);
            }
        }

        /**
         * @return {Boolean} wenn {TRUE}, dann sind wir ein "Leaf" ohne Value, d.h. wir können komplett
         * gelöscht werden!
         * @throws ShitNotThereException
         */
        public boolean remove(String s, int pos) throws ShitNotThereException {
            if (s.length() == pos) {
                if (this.value == null) throw new ShitNotThereException();
                else{
                    this.value = null;
                    return this.isDead();
                }
            } else {
                int p = s.charAt(pos) - 'a';
                if (this.children[p] == null) throw new ShitNotThereException();
                else {
                    if(this.children[p].remove(s, pos + 1)){
                        // Der vorherige Knoten war ein Blatt: lösche ihn Komplett!
                        this.children[p] = null;
                        this.childrenCount -= 1;
                        return this.isDead();
                    }
                    // Wir haben noch Verzweigungen, die diese Knoten benutzen! Der Knoten
                    // selbst darf also nicht gelöscht werden
                    return false;
                }
            }
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(TrieNode node : this.children){
                if(node != null){
                    sb.append(node.character);
                    if(node.value != null) sb.append("*");
                    sb.append("[");
                    sb.append(node.toString());
                    sb.append("]");
                }
            }
            return sb.toString();
        }
    }
}

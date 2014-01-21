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
        this.root.put(s, 0, v);
        return this;
    }

    @Override
    public T get(String s) throws ShitNotThereException {
        return (T) this.root.get(s, 0).value;
    }

    @Override
    public Trie<T> remove(String s) throws ShitNotThereException {
        this.root.remove(s, 0);
        return this;
    }

    @Override
    public T succ(String s) throws ShitNotThereException {
        return this.root.succ(s);
    }

    @Override
    public String toString() {
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
        TrieNode<T>[] children = new TrieNode[26];
        TrieNode<T> parent;

        private TrieNode() {
        }

        public TrieNode(char c, TrieNode<T> parent) {
            this.character = c;
            this.parent = parent;
        }

        public boolean isDead() {
            return this.childrenCount == 0 && this.value == null;
        }

        public void put(String s, int pos, T v) throws ShitIsAlreadyThereException {
            if (s.length() == pos) {
                if (this.value == null) this.value = v;
                else throw new ShitIsAlreadyThereException();
            } else {
                int p = s.charAt(pos) - 'a';
                if (this.children[p] == null) {
                    this.children[p] = new TrieNode<T>(s.charAt(pos), this);
                    this.childrenCount += 1;
                }
                this.children[p].put(s, pos + 1, v);
            }
        }

        public TrieNode get(String s, int pos) throws ShitNotThereException {
            if (s.length() == pos) {
                if (this.value == null) throw new ShitNotThereException();
                else return this;
            } else {
                int p = s.charAt(pos) - 'a';
                if (this.children[p] == null) throw new ShitNotThereException();
                else return this.children[p].get(s, pos + 1);
            }
        }

        public T succ(String s) throws ShitNotThereException {
            TrieNode<T> found = this.get(s, 0);
            if (found.childrenCount > 0) {
                // runtergehen..
                return found.nextMostLeftValue();
            } else {
                // hochgehen..
                TrieNode<T> node = found;
                while (node.parent != null && (node.parent.childrenCount == 1 || node.isRightMost())){
                    node = node.parent;
                }
                if(node.parent == null) return null; // Es gibt keinen succ!
                boolean takeNext = false;
                for(TrieNode<T> n : node.parent.children){
                    if(n != null){
                        if(n.character == node.character){
                            takeNext = true;
                        }else if(takeNext){
                            if (n.value == null) return n.nextMostLeftValue();
                            return n.value;
                        }
                    }
                }
            }
            return null;
        }

        /**
         * @return {Boolean} wenn {TRUE}, dann sind wir ein "Leaf" ohne Value, d.h. wir können komplett
         * gelöscht werden!
         * @throws ShitNotThereException
         */
        public boolean remove(String s, int pos) throws ShitNotThereException {
            if (s.length() == pos) {
                if (this.value == null) throw new ShitNotThereException();
                else {
                    this.value = null;
                    return this.isDead();
                }
            } else {
                int p = s.charAt(pos) - 'a';
                if (this.children[p] == null) throw new ShitNotThereException();
                else {
                    if (this.children[p].remove(s, pos + 1)) {
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
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (TrieNode node : this.children) {
                if (node != null) {
                    sb.append(node.character);
                    if (node.value != null) sb.append("*");
                    sb.append("[");
                    sb.append(node.toString());
                    sb.append("]");
                }
            }
            return sb.toString();
        }

        private T nextMostLeftValue(){
            TrieNode<T> child = this.leftMost();
            while (child.value == null) child = child.leftMost();
            return child.value;
        }

        /**
         * scheiß-funktion
         * @return
         */
        private boolean isRightMost(){
            if(this.parent != null) {
                if (this.parent.childrenCount == 1) return true;
                for(int i = parent.children.length - 1; i>0;i--){
                    if (parent.children[i] != null){
                        if(parent.children[i].character == this.character)return true;
                        else return false;
                    }
                }
            }
            return false;
        }

        /**
         * finde das kleinste (lexikalische) Child
         * @return
         */
        private TrieNode leftMost() {
            if (this.childrenCount == 0) return null;
            for(TrieNode node : this.children) if (node != null) return node;
            return null;
        }
    }
}

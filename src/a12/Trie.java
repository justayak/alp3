package a12;

/**
 * ++ method chaining yay ++
 * User: Julian
 * Date: 21.01.14
 * Time: 16:57
 */
public interface Trie<T> {
    Trie<T> put(String s, T v) throws ShitIsAlreadyThereException;
    T get(String s) throws ShitNotThereException;
    Trie<T> remove(String s) throws ShitNotThereException;
    T succ(String s) throws ShitNotThereException;

}

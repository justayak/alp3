package aufgabe07;

/**
 * User: Julian
 * Date: 03.12.13
 * Time: 18:28
 */
public interface OrderedDictionary<String, M> {

    void put(String key, M value);

    M get(String key);

    void delete(String key);

}

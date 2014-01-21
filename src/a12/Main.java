package a12;

/**
 * User: Julian
 * Date: 21.01.14
 * Time: 17:37
 */
public class Main {


    public static void main(String[]args) {
        try {

            Trie<String> trie = new TrieImpl<String>();

            trie.put("demo", "hallo");
            trie.put("demolo", "hallo123");
            trie.put("haus", "Timo");
            trie.put("hauq", "Tim");
            trie.put("hauf", "Julian");
            trie.put("haufen", "bla");

            System.out.println(trie.get("demo"));

            System.out.println(trie.toString());

            //trie.remove("haufen");

            System.out.println(trie.get("hauf"));

            System.out.println(trie.succ("hauf"));
            System.out.println(trie.succ("demolo"));

            System.out.println(trie.toString());


        } catch (ShitNotThereException e) {
            System.out.println("nop");
        } catch (ShitIsAlreadyThereException e) {
            System.out.println("nop2");
        }

    }


}

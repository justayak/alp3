package aufgabe07;

/**
 * Created with IntelliJ IDEA.
 * User: Julian
 * Date: 27.11.13
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main(String[]args){
        System.out.println("hiu");

        JSkipList<Integer, String> list = new JSkipList<Integer, String>();
        list.put(5, "fünf");
        list.put(9, "neun");
        list.put(2, "zwei");
        list.put(3, "drei");

    }

}

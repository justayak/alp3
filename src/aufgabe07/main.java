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

        SkipList<Integer, String> list = new SkipList<Integer, String>();
        list.put(5, "fünf");
        list.put(9, "neun");
        list.put(2, "zwei");
        list.put(3, "drei");

        System.out.println(list.get(3));

        list.put(3, "drei_1");

        for(int i = 10; i < 1000; i++){
            list.put(i, "hallo_" + i);
        }

        System.out.println(list.get(188));


    }

}

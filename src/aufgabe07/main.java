package aufgabe07;

import java.util.Iterator;

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

        for(int i = 0; i < 100; i++){
            list.put(i, "hallo_" + i);
        }

        for(int i = 10; i < 100; i++){
            list.put(i, "wooho_" + i);
        }

        list.put(5, "fünf");
        list.put(9, "neun");
        list.put(2, "zwei");
        list.put(3, "drei");

        System.out.println("min: " + list.min());
        System.out.println("max: " + list.max());

        for(int i = 10; i < 100; i+=10){
            list.remove(i);
        }

        System.out.println("iterate: ");
        for(String s : list){
            System.out.println(s);
        }

        System.out.println(list.get(-152));


        Iterator<String> itr = list.iterator();
        System.out.println("===========================");
        for(int i = 0; i < 10; i++){
            String str = itr.next();
            System.out.println(str);
        }


    }

}

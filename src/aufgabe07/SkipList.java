package aufgabe07;

import java.util.Random;
import java.util.Stack;

/**
 * User: Julian
 * Date: 04.12.13
 * Time: 14:30
 */
public class SkipList<K extends Comparable<K>, V> {

    private Node head;
    private Node tail;

    public SkipList() {
        this.createHeadTail();
    }


    public void put(K key, V value) {
        Stack<Node> Q = this.search(key);
        Node n = Q.pop();
        Node insert = new Node(key,value);
        this.insertAfterHorizontal(n, insert);
        while (coinToss()){
            if (Q.empty()){
                insert = newLine(insert);
            }else {
                n = Q.pop();
                insert = new Node(key, insert);
                this.insertAfterHorizontal(n, insert);
            }
        }
    }

    public void remove(K key){

    }

    private Node newLine(Node n){
        Node result = new Node(n.key, n);
        this.createHeadTail();
        this.insertAfterHorizontal(this.head, result);
        return result;
    }

    public V get(K key){
        Node n = this.search(key).pop();
        if (n.key.compareTo(key) == 0){
            return n.value;
        }
        return null;
    }

    private void createHeadTail(){
        if (this.head != null){
            Node oldhead = this.head;
            Node oldtail = this.tail;
            this.head = new Node(false);
            this.tail = new Node(true);
            this.head.down = oldhead;
            this.tail.down = oldtail;
        }else {
            this.head = new Node(false);
            this.tail = new Node(true);
        }
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    private void insertAfterHorizontal(Node e, Node insert){
        Node next = e.next;
        insert.next = next;
        insert.prev = e;
        e.next = insert;
        next.prev = insert;
    }


    private Stack<Node> search(K key){
        Node n = this.head;
        Stack<Node> Q = new Stack<Node>();
        while (true){
            if(n.next.isPlusInfinity || (n.next.key.compareTo(key) <0)){
                Q.push(n);
                if (n.down == null){
                    break;
                }else {
                    n = n.down;
                }
            } else{
                n = n.next;
            }
        }
        return Q;
    }

    private static Random rand = new Random();
    private static boolean coinToss() {
        return rand.nextInt(2) == 0;
    }

    private class Node {
        public V value;
        public K key;
        public final boolean isPlusInfinity;
        public final boolean isMinusInfinity;
        public Node next;
        public Node prev;
        public Node down;

        public Node(K key, V value) {
            this.isMinusInfinity = this.isPlusInfinity = false;
            this.key = key;
            this.value = value;
        }

        public Node(K key, Node down) {
            this.isMinusInfinity = this.isPlusInfinity = false;
            this.down = down;
            this.key = key;
        }

        public Node(boolean positive) {
            if (positive) {
                this.isPlusInfinity = true;
                this.isMinusInfinity = false;
            } else {
                this.isPlusInfinity = false;
                this.isMinusInfinity = true;
            }
        }
    }
}

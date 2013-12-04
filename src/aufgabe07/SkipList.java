package aufgabe07;

import java.util.Random;
import java.util.Stack;

/**
 * User: Julian
 * Date: 04.12.13
 * Time: 11:19
 */
public class SkipList<K extends Comparable<K>, V> {

    private Node head;
    private Node tail;

    public SkipList() {
        this.head = new Node(true);
        this.tail = new Node(false);
        this.head.Next = this.tail;
        this.tail.Prev = this.head;
    }

    public Stack<Node> search(K key) {
        Stack<Node> Q = new Stack<Node>();
        Node n = this.head;
        do {
            while (!(n.Type != NodeTypes.PlusInfinity) &&
                    n.Next.Key.compareTo(key) <= 0) {
                n = n.Next;
            }
            Q.push(n);
            n = n.Down;
        } while (n != null);
        return Q;
    }

    public void put(K key, V value) {
        Stack<Node> Q = this.search(key);
        Node n = Q.pop();
        if ((n.Type == NodeTypes.Normal) && n.Key.compareTo(key) == 0){
            n.Value = value;
            return;
        }
        Node right = n.Next;
        Node ins = new Node(key,value);
        ins.Next = right;
        right.Prev = ins;
        n.Next = ins;
        Node prevHead = this.head;
        Node prevTail = this.tail;
        while (coinToss()){
            if (Q.empty()){
                Node head = new Node(true);
                Node tail = new Node(false);
                Node me = new Node(key);
                head.Next = me;
                me.Prev = head;
                me.Next = tail;
                tail.Prev = me;
                head.Down = prevHead;
                tail.Down = prevTail;
                me.Down = ins;
                ins = me;
                prevHead = head;
                prevTail = tail;
                this.head = head;
                this.tail = tail;
            }else{
                n = Q.pop();
                Node me = new Node(key);
                me.Down = ins;
                ins = me;
                right = n.Next;
                n.Next = me;
                right.Prev = me;
                me.Prev = n;
                me.Next = right;
            }
        }
    }

    private static Random rand = new Random();
    private static boolean coinToss(){
        return rand.nextInt(2) == 0;
    }

    //======= ======= ======= ======= ======= =======
    //======= ======= ======= ======= ======= =======

    public enum NodeTypes {
        Normal,
        PlusInfinity,
        MinusInfinity
    }

    private class Node {
        public final K Key;
        public V Value;
        public Node Prev;
        public Node Next;
        public Node Down;
        public final NodeTypes Type;

        public Node(K key, V value) {
            this.Key = key;
            this.Value = value;
            this.Type = NodeTypes.Normal;
        }

        public Node(K key){
            this.Key = key;
            this.Type = NodeTypes.Normal;
        }

        public Node(boolean isLeft) {
            if (isLeft) this.Type = NodeTypes.MinusInfinity;
            else this.Type = NodeTypes.PlusInfinity;
            this.Key = null;
            this.Value = null;
        }
    }

}

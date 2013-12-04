package aufgabe07;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * User: Julian
 * Date: 04.12.13
 * Time: 14:30
 */
public class SkipList<K extends Comparable<K>, V> implements SortedDictionary<K, V>, Iterable<V> {

    private Node head;
    private Node tail;

    public SkipList() {
        this.createHeadTail();
    }

    @Override
    public V min() {
        Node min = this.bottom(this.head).next;
        if (min.isNormal) {
            return min.value;
        }
        return null;
    }

    @Override
    public V max() {
        Node max = this.bottom(this.tail).prev;
        if (max.isNormal) {
            return max.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        Stack<Node> Q = this.search(key);
        Node n = Q.pop();
        if (n.isNormal && n.key.compareTo(key) == 0) {
            n.value = value;
        } else {
            Node insert = new Node(key, value);
            this.insertAfterHorizontal(n, insert);
            while (coinToss()) {
                if (Q.empty()) {
                    insert = newLine(insert);
                } else {
                    n = Q.pop();
                    insert = new Node(key, insert);
                    this.insertAfterHorizontal(n, insert);
                }
            }
        }
    }

    @Override
    public void remove(K key) {
        Stack<Node> Q = this.search(key);
        Node n = Q.pop();
        if (n.isNormal && n.key.compareTo(key) == 0) {
            while (n != null && n.isNormal && n.key.compareTo(key) == 0) {
                this.removeNode(n);
                if (Q.empty()) n = null;
                else n = Q.pop();
            }
        } else {
            System.out.println("nop... nicht da");
        }
    }

    @Override
    public Iterator<V> reverseIterator() {
        final Node tail = this.bottom(this.tail);
        return new Iterator<V>() {
            Node n = tail;

            @Override
            public boolean hasNext() {
                return n.prev.isNormal;
            }

            @Override
            public V next() {
                n = n.prev;
                return n.value;
            }

            @Override
            public void remove() {
                throw new RuntimeException("dont touch me!!");
            }
        };
    }

    @Override
    public Iterator<V> iterator() {
        final Node head = this.bottom(this.head);
        return new Iterator<V>() {
            Node n = head;

            @Override
            public boolean hasNext() {
                return n.next.isNormal;
            }

            @Override
            public V next() {
                n = n.next;
                return n.value;
            }

            @Override
            public void remove() {
                throw new RuntimeException("dont touch me!!");
            }
        };
    }

    // =============================
    // === DIVERSE HILFSMETHODEN ===
    // =============================

    private Node bottom(Node n) {
        while (n.down != null) n = n.down;
        return n;
    }

    private void removeNode(Node n) {
        Node prev = n.prev;
        Node next = n.next;
        prev.next = next;
        next.prev = prev;
        n.down = null;
        n.prev = null;
        n.next = null;
    }

    private Node newLine(Node n) {
        Node result = new Node(n.key, n);
        this.createHeadTail();
        this.insertAfterHorizontal(this.head, result);
        return result;
    }

    public V get(K key) {
        Node n = this.search(key).pop();
        if (n.isNormal && n.key.compareTo(key) == 0) {
            return n.value;
        }
        return null;
    }

    private void createHeadTail() {
        if (this.head != null) {
            Node oldhead = this.head;
            Node oldtail = this.tail;
            this.head = new Node(false);
            this.tail = new Node(true);
            this.head.down = oldhead;
            this.tail.down = oldtail;
        } else {
            this.head = new Node(false);
            this.tail = new Node(true);
        }
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    private void insertAfterHorizontal(Node e, Node insert) {
        Node next = e.next;
        insert.next = next;
        insert.prev = e;
        e.next = insert;
        next.prev = insert;
    }


    private Stack<Node> search(K key) {
        Node n = this.head;
        Stack<Node> Q = new Stack<Node>();
        while (true) {
            if (n.next.isPlusInfinity || (n.next.key.compareTo(key) > 0)) {
                Q.push(n);
                if (n.down == null) {
                    break;
                } else {
                    n = n.down;
                }
            } else {
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
        public final boolean isNormal;
        public Node next;
        public Node prev;
        public Node down;

        public Node(K key, V value) {
            this.isMinusInfinity = this.isPlusInfinity = false;
            this.isNormal = true;
            this.key = key;
            this.value = value;
        }

        public Node(K key, Node down) {
            this.isMinusInfinity = this.isPlusInfinity = false;
            this.isNormal = true;
            this.down = down;
            this.key = key;
        }

        public Node(boolean positive) {
            this.isNormal = false;
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

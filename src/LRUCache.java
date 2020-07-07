import java.util.*;

/*
LC#146
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);       // returns 1
        cache.put(3,3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4,4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
        */

public class LRUCache {
    // doubly linked list
    private class Node {
        private int val;
        private Node pre = null;
        private Node next = null;

        public Node(int val) {
            this.val = val;
        }
    }

    private Map<Integer, Integer> m = new HashMap<>();
    private Map<Integer, Node> key2node = new HashMap<>();
    private Node head = new Node(-1);
    private Node tail = head;
    private int cap = 0;


    public LRUCache(int capacity) {
        // >0
        this.cap = capacity;
    }

    public int get(int key) {
        Integer value = m.get(key);
        if (value == null) {
            return -1;
        }
        adj(key);
        return value;
    }

    // put this key to the front
    private void adj(int key) {
        if (key2node.containsKey(key)) {
            Node node = key2node.get(key);
            remove(node);
            addFirst(node);
        } else {
            Node node = new Node(key);
            key2node.put(key, node); // dont forget to maintain this map too!
            addFirst(node);
        }
    }

    // remove node n, may or may not add it back
    private void remove(Node n) {
        // n non null
        Node pre = n.pre;
        Node next = n.next;
        pre.next = next;
        if (next != null) {
            next.pre = pre;
        }
        if (tail == n) {
            tail = pre;
        }
        n.pre = null;
        n.next = null;
    }

    // add the node after head
    private void addFirst(Node n) {
        // n non null
        Node oldNext = head.next;
        head.next = n;
        n.next = oldNext;
        n.pre = head;
        if (oldNext != null) {
            oldNext.pre = n;
        }
        if (tail == head) {
            tail = n;
        }
    }

    public void put(int key, int value) {
        if (m.containsKey(key)) {
            m.put(key, value);
            adj(key);
        } else {
            if (m.size() == cap) {
                m.remove(tail.val);
                key2node.remove(tail.val); // dont forget to maintain this map too!
                remove(tail);
            }
            m.put(key, value);
            adj(key);
        }
    }
}

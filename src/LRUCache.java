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
    // use two dummy nodes head and tail so that the list is between head and tail. greatly easing the code for edge cases
    private class Node {
        private int key;
        private int val;
        private Node pre = null;
        private Node next = null;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer, Node> m = new HashMap<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);
    private int cap = 0;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be >0");
        }
        this.cap = capacity;
        head.pre = tail;
        head.next = tail;
        tail.next = head;
        tail.pre = head;
    }

    private void remove(Node n) {
        Node pre = n.pre;
        Node next = n.next;
        pre.next = next;
        next.pre = pre;
        n.pre = null;
        n.next = null;
    }

    private void removeLast() {
        Node last = tail.pre;
        m.remove(last.key);
        remove(last);
    }

    private void addHead(Node n) {
        Node hn = head.next;
        n.next = hn;
        n.pre = head;
        hn.pre = n;
        head.next = n;
    }

    private void removeAndAddHead(Node n) {
        remove(n);
        addHead(n);
    }

    public int get(int key) {
        Node ext = m.get(key);
        if (ext == null) {
            return -1;
        }
        removeAndAddHead(ext);
        return ext.val;
    }

    public void put(int key, int value) {
        Node ext = m.get(key);
        if (ext != null) {
            ext.val = value;
            removeAndAddHead(ext);
            return;
        }
        Node newNode = new Node(key, value);
        addHead(newNode);
        m.put(key, newNode);
        if (m.size() > cap) {
            removeLast();
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}


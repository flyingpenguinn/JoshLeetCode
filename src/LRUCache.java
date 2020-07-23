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

    class Node {
        private int key;  // we MUST record key as well for easier removal in remove tail
        private int val;
        private Node prev;
        private Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int cap = 0;
    private Map<Integer, Node> m = new HashMap<>();

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.cap = capacity;
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }

    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public int get(int key) {
        Node v = m.get(key);
        if (v == null) {
            return -1;
        }
        removeAndAddHead(v);
        return v.val;
    }

    public void put(int key, int value) {
        Node v = m.get(key);
        if (v != null) {
            removeAndAddHead(v);
            v.val = value;
            return;
        }
        v = new Node(key, value);
        m.put(key, v);
        addHead(v); // just addhead, not remove and addhead
        if (m.size() > cap) {
            removeTail();
        }
    }

    private void removeAndAddHead(Node v) {
        remove(v);
        addHead(v);
    }

    private void remove(Node v) {
        Node prev = v.prev;
        Node next = v.next;
        prev.next = next;
        next.prev = prev;
        v.prev = null;
        v.next = null;
    }

    private void addHead(Node v) {
        Node headNext = head.next;
        head.next = v;
        v.prev = head;
        v.next = headNext;
        headNext.prev = v;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    private void removeTail() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        Node tailNode = tail.prev;
        m.remove(tailNode.key);
        remove(tailNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


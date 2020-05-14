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
    class DLNode {
        DLNode prev;
        DLNode next;
        int val;

        public DLNode(int val) {
            this.val = val;
        }
    }

    Map<Integer, DLNode> nodemap = new HashMap<>();
    Map<Integer, Integer> map = new HashMap<>();
    DLNode head = new DLNode(-1);
    DLNode tail = head; // point to the last element or head
    int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DLNode node = nodemap.get(key);
            recordUsage(node);
            return map.get(key);
        } else {
            return -1;
        }
    }

    private void recordUsage(DLNode node) {
        removeNode(node);
        addTail(node);
    }

    private void addTail(DLNode node) {
        tail.next = node;
        node.prev = tail;
        node.next = null;
        tail = node;
    }

    private void removeNode(DLNode node) {
        DLNode prev = node.prev;
        DLNode next = node.next;
        prev.next = next;
        if (next != null) {
            next.prev = prev;
        }
        node.prev = null;
        node.next = null;
        if (tail == node) {
            // if we remove the last need to move the tail
            tail = prev;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            DLNode node = nodemap.get(key);
            // record a usage
            recordUsage(node);
        } else {
            if (map.size() < cap) {
                map.put(key, value);
            } else {
                DLNode oldest = head.next;
                if (oldest != null) {
                    // if oldest == null cap == 0
                    removeNode(oldest);
                    map.remove(oldest.val);
                } else {
                    return;
                }
            }
            DLNode nt = new DLNode(key);
            addTail(nt);
            map.put(key, value);
            nodemap.put(key, nt);
        }
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(2, 1);
        lru.put(1, 1);
        lru.put(2, 3);
        lru.put(4, 1);

    }
}

class LRULib {
    LinkedHashMap<Integer, Integer> map;
    int capacity;

    public LRULib(int capacity) {
        // must makeit access order
        this.capacity = capacity;
        map = new LinkedHashMap<>(capacity, 0.75f, true);

    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        // only delete when map doesn't alraedy have keys, i.e. about to increase size
        if (!map.containsKey(key) && map.size() == capacity) {
            // linked hashmap can gurantee ordering of keyset iteration
            Iterator<Integer> keys = map.keySet().iterator();
            if (keys.hasNext()) {
                Integer oldestkey = keys.next();
                map.remove(oldestkey);
            }
        }
        map.put(key, value);
    }
}

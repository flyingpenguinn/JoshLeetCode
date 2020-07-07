import java.util.*;
/*
LC#460
/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.



Follow up:
Could you do both operations in O(1) time complexity?



Example:

LFUCache cache = new LFUCache( 2 )

        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);       // returns 1
        cache.put(3,3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4,4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
        */

class LFUCache {

    private Map<Integer, Integer> m = new HashMap<>(); // key/value
    private Map<Integer, Integer> f = new HashMap<>(); // key freq
    private Map<Integer, LinkedHashSet<Integer>> f2k = new HashMap<>(); // freq to the keys having this freq
    private int cap = 0;
    private int min = 0;

    public LFUCache(int capacity) {
        // cap could be 0 here!
        this.cap = capacity;
    }

    public int get(int key) {
        if (cap <= 0) {
            return -1;
        }
        Integer v = m.get(key);
        if (v == null) {
            return -1;
        }
        adj(key);
        return v;
    }

    public void put(int key, int value) {
        if (cap <= 0) {
            return;
        }
        Integer v = m.get(key);
        if (v != null) {
            m.put(key, value);
            adj(key);
        } else {
            if (m.size() == cap) {
                removeMin();
            }
            m.put(key, value);
            adj(key);
            min = 1;
        }
    }

    private void removeMin() {
        LinkedHashSet<Integer> keys = f2k.get(min);
        Integer targetKey = keys.iterator().next(); // in insertion order, older ones first
        keys.remove(targetKey); // remove from f2k
        m.remove(targetKey);
        f.remove(targetKey);
    }

    private void adj(int key) {
        // not handling m, just doing f and f2k maintainence
        int of = f.getOrDefault(key, 0);
        int nf = of + 1;
        f.put(key, nf);
        LinkedHashSet<Integer> oset = f2k.get(of);
        if (oset != null) {
            oset.remove(key);
            if (oset.isEmpty()) {
                f2k.remove(of);
                if (min == of) {
                    min = nf; // min can move up dont miss this part!
                }
            }
        }
        f2k.computeIfAbsent(nf, k -> new LinkedHashSet<>()).add(key);
    }
}



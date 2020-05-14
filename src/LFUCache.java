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


public class LFUCache {
    // use linked hashset for the lru part
    // as we always +1 or -1, we can change min easily. for newly added elements, min = 1.
    // newest freq = 1 is the main trick. because without this property it's hard to locate the 2nd smallest freq
    Map<Integer, Integer> m = new HashMap<>();
    // member to time
    Map<Integer, Integer> fm = new HashMap<>();
    // time to members
    Map<Integer, LinkedHashSet<Integer>> cm = new HashMap<>();
    int cap = 0;
    int mintime = 0;


    public LFUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (m.containsKey(key)) {
            Integer rt = m.get(key);
            addkey(key, rt);
            return rt;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }
        if (m.size() < cap || m.containsKey(key)) {
            addkey(key, value);
        } else {
            LinkedHashSet<Integer> set = cm.get(mintime);
            Integer toremove = set.iterator().next();
            removekey(toremove);
            addkey(key, value);
        }
    }

    // when removing we kick the whole thing out...
    private void removekey(Integer key) {
        m.remove(key);
        Integer ok = fm.get(key);
        fm.remove(key);
        LinkedHashSet<Integer> oset = cm.get(ok);
        oset.remove(key);
        if (oset.isEmpty()) {
            cm.remove(ok);
        }
        cm.put(ok, oset);
    }

    private void addkey(int key, int val) {
        m.put(key, val);
        Integer ok = fm.getOrDefault(key, 0);
        if (ok == 0) {
            // key: new element is always of freq 1
            mintime = 1;
        }
        fm.put(key, ok + 1);
        LinkedHashSet<Integer> oldset = cm.getOrDefault(ok, new LinkedHashSet<>());
        if (ok > 0) {
            // doesnt exist before
            oldset.remove(key);
            cm.put(ok, oldset);
        }
        if (oldset.isEmpty()) {
            cm.remove(oldset);
            if (ok == mintime) {
                mintime = ok + 1;
            }
        }
        LinkedHashSet<Integer> nset = cm.getOrDefault(ok + 1, new LinkedHashSet<>());
        nset.add(key);
        cm.put(ok + 1, nset);
    }


    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(3);
        lfu.put(2, 2);
        lfu.put(1, 1);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(2));
        lfu.put(3, 3);
        lfu.put(4, 4);
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(4));

    }
}


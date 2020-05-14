/*
LC#706
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);
hashMap.put(2, 2);
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found)

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
 */
public class DesignHashMap {

}

class MyHashMap {

    /**
     * Initialize your data structure here.
     */
    class Entry {
        int k;
        int v;
        Entry next = null;
        Entry prev = null;

        public Entry(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    // can't be as big as 1 million
    int n = 1000;
    // entry array, not a list of array
    Entry[] les = new Entry[n];

    public MyHashMap() {
        for (int i = 0; i < n; i++) {
            les[i] = new Entry(-1, -1);
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        Entry cur = getentry(key);
        if (cur != null) {
            cur.v = value;
            return;
        }
        int hk = key % n;
        Entry e = new Entry(key, value);
        // head insert
        Entry next = les[hk].next;
        les[hk].next = e;
        e.next = next;
        e.prev = les[hk];
        if (next != null) {
            next.prev = e;
        }
    }

    Entry getentry(int key) {
        int hk = key % n;
        Entry p = les[hk].next;
        while (p != null) {
            // must compare key
            if (p.k == key) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        Entry e = getentry(key);
        return e == null ? -1 : e.v;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        Entry e = getentry(key);
        if (e == null) {
            return;
        }
        Entry pre = e.prev;
        Entry next = e.next;
        pre.next = next;
        if (next != null) {
            next.prev = pre;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

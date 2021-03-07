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

    class Entry{
        private int k;
        private int v;
        private Entry next;
        private Entry prev;
        public Entry(int k, int v, Entry next, Entry prev){
            this.k = k;
            this.v = v;
            this.next = next;
            this.prev = prev;
        }
    }
    private final int bsize = 1024;
    private Entry[] bucket = new Entry[bsize];
    /** Initialize your data structure here. */
    public MyHashMap() {
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {

        Entry e = locate(key);
        if(e!= null){
            e.v = value;
            return;
        }
        int slot = getslot(key);
        Entry oldhead = bucket[slot];
        Entry newhead = new Entry(key, value, oldhead,null);
        if(oldhead != null){
            oldhead.prev = newhead;
        }
        bucket[slot] = newhead;
    }


    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Entry e = locate(key);
        return e!= null? e.v: -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        Entry e = locate(key);
        if(e==null){
            return;
        }
        Entry prev = e.prev;
        Entry next = e.next;
        int slot = getslot(key);
        if(prev==null){
            // first in bucket
            bucket[slot] = next;
            if(next != null){
                next.prev = null;
            }
        }else{
            prev.next = next;
            if(next != null){
                next.prev = prev;
            }
        }
    }


    private Entry locate(int key){
        int slot = getslot(key);
        Entry e = bucket[slot];
        while(e!= null && e.k != key){
            e = e.next;
        }
        if(e!= null && e.k == key){
            return e;
        }
        return null;
    }

    private int getslot(int key){
        return key % bsize;
    }
}


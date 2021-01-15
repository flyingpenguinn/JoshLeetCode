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

    private class Entry{
        private int k;
        private int v;
        private Entry next;
        private Entry prev;
        public Entry(int k, int v){
            this.k = k;
            this.v = v;
        }
    }
    private int n = 1024;
    private Entry[] b = new Entry[n];
    /** Initialize your data structure here. */
    public MyHashMap() {

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int mod = key % n;
        Entry found = find (key);
        if (found != null){
            found.v = value;
            return;
        }
        Entry e = new Entry(key, value);
        e.next = b[mod];
        if(b[mod] != null){
            b[mod].prev = e;
        }
        b[mod] = e;
    }

    private Entry find(int key){
        int mod = key % n;
        Entry e = b[mod];
        while(e!= null){
            if(e.k == key){
                return e;
            }
            e = e.next;
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Entry found = find (key);
        return found == null? -1: found.v;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int mod = key % n;
        Entry found = find(key);
        if(found==null){
            return;
        }
        if(found.prev == null){
            b[mod] = found.next;
        }else{
            found.prev.next = found.next;
        }
        if(found.next != null){
            found.next.prev = found.prev;
        }
    }
}


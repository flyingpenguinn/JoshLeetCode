public class DesignHashSet {
}


class MyHashSet {

    /**
     * Initialize your data structure here.
     */
    class Entry {
        int v;
        Entry next;

        public Entry(int v) {
            this.v = v;
        }
    }

    int size = 1024;
    Entry[] buckets = new Entry[size];

    public MyHashSet() {

    }

    public void add(int key) {
        Entry pre = find(key);
        if (pre != null) {
            return;
        } else {
            Entry head = getHead(key);
            Entry e = new Entry(key);
            if (head == null) {
                int hash = getHash(key);
                buckets[hash] = new Entry(-1);
                head = buckets[hash];
            }
            e.next = head.next;
            head.next = e;
        }

    }

    private Entry getHead(int key) {
        int hash = getHash(key);
        Entry head = buckets[hash];
        return head;
    }

    private int getHash(int key) {
        return key % size;
    }


    private Entry find(int key) {
        Entry p = getHead(key);
        if (p == null) {
            return null;
        }
        while (p.next != null) {
            if (p.next.v == key) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public void remove(int key) {
        Entry pre = find(key);
        if (pre == null) {
            return;
        }
        Entry cur = pre.next;
        pre.next = cur.next;
        cur.next = null;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        Entry pre = find(key);
        return pre != null;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
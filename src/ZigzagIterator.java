import base.Lists;

import java.util.*;

public class ZigzagIterator {
    // without iterators, pay attention to the usage of tried in hasNext
    int k = 2;
    int i = 0;
    int[] ps = new int[k];

    boolean cached = false;
    List<Integer>[] a = new ArrayList[k];

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        Arrays.fill(ps, -1);
        a[0] = v1;
        a[1] = v2;
    }

    public int next() {
        if (hasNext()) {
            cached = false;
            int rt = a[i].get(ps[i]);
            i = (i + 1) % k;
            return rt;
        }
        return -1;
    }

    public boolean hasNext() {
        if (cached) {
            return i != -1;
        }
        int tried = 0;
        while (tried < k && ps[i] + 1 >= a[i].size()) {
            i = (i + 1) % k;
            tried++;
        }
        if (tried == k) {
            i = -1;
            return false;
        } else {
            ps[i]++;
            cached = true;
            return true;
        }
    }
}

class ZigZgIteratorLinkedList {
    // use linked list iterators!
    LinkedList<Iterator> list;

    public ZigZgIteratorLinkedList(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if (!v1.isEmpty()) list.add(v1.iterator());
        if (!v2.isEmpty()) list.add(v2.iterator());
    }

    public int next() {
        Iterator poll = list.removeFirst();
        int result = (Integer) poll.next();
        if (poll.hasNext()) list.addLast(poll);
        return result;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}

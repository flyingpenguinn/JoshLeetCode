import base.Lists;

import java.util.*;

public class ZigzagIterator {

    private LinkedList<Iterator<Integer>> lists = new LinkedList<>();

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if (v1 != null && !v1.isEmpty()) {
            lists.add(v1.iterator());
        }
        if (v2 != null && !v2.isEmpty()) {
            lists.add(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> first = lists.pollFirst();
        int rt = first.next();
        if (first.hasNext()) {
            lists.addLast(first);
        }
        return rt;
    }

    public boolean hasNext() {
        return !lists.isEmpty();
    }
}


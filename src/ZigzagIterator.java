import base.Lists;

import java.util.*;

public class ZigzagIterator {
    // without iterators, pay attention to the usage of tried in hasNext
    // without iterators, pay attention to the usage of tried in hasNext
    private int cur = 0;
    private List<Deque<Integer>> dqs = new ArrayList<>();
    private int empty = 0; // how many empty deques do we have

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(v1);
        input.add(v2);
        for (List<Integer> list : input) {
            if (!list.isEmpty()) {
                dqs.add(new ArrayDeque<>(list));
            }
        }
        // note we only add non empty ones so dont need to move in the start
    }

    private void moveToNext() {
        if (dqs.get(cur).isEmpty()) {
            empty++;
        }
        if (empty == dqs.size()) {
            return; // already empty
        }
        // note we MUST MOVE so here must be cur+1 to start checking
        int j = (cur + 1) % dqs.size();
        while (dqs.get(j).isEmpty()) {
            j = (j + 1) % dqs.size();
        }
        cur = j;
    }

    public int next() {
        if (!hasNext()) {
            return -1;
        }
        int rt = dqs.get(cur).poll();
        moveToNext();
        return rt;
    }

    public boolean hasNext() {
        return empty < dqs.size();
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

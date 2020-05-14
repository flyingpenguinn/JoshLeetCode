import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

/*
LC#362
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
Follow up:
What if the number of hits per second could be very large? Does your design scale?
 */
public class DesignHitCounter {

}

class HitCounter {
    // treemap handles same timestamp multiple hits better
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    int all = 0;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {

    }

    public void hit(int ts) {
        all++;
        tm.put(ts, all);
    }

    public int getHits(int timestamp) {
        Integer pre = tm.floorKey(timestamp - 300);
        if (pre == null) {
            return all;
        } else {
            return all - tm.get(pre);
        }
    }
}

class HitCounterDeque {


    /**
     * Initialize your data structure here.
     */
    Deque<Integer> q = new ArrayDeque<>();

    public HitCounterDeque() {

    }

    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while (!q.isEmpty() && q.peek() <= timestamp - 300) {
            q.poll();
        }
        return q.size();
    }
}
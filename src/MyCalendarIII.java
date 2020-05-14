import base.ArrayUtils;

import java.util.*;

public class MyCalendarIII {


    public static void main(String[] args) {
        MyCalendarThreePriorityQueue mct = new MyCalendarThreePriorityQueue();
        int[][] converedArray = ArrayUtils.read("[[47,50],[1,10],[27,36],[40,47],[20,27],[15,23],[10,18],[27,36],[17,25],[8,17],[24,33],[23,28],[21,27],[47,50],[14,21],[26,32],[16,21],[2,7],[24,33],[6,13],[44,50],[33,39],[30,36],[6,15],[21,27],[49,50],[38,45],[4,12],[46,50],[13,21]]");
        for (int i = 0; i < converedArray.length; i++) {
            System.out.println(mct.book(converedArray[i][0], converedArray[i][1]));
        }
    }
}


class MyCalendarThree {
    public MyCalendarThree() {

    }

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int active = 0;
        int maxActive = 0;
        for (Integer k : map.keySet()) {
            active += map.get(k);
            maxActive = Math.max(maxActive, active);
        }
        return maxActive;
    }

}

class MyCalendarThreePriorityQueue {
    class Event {
        int start;
        int end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class EventStartComparator implements Comparator<Event> {

        @Override
        public int compare(Event o1, Event o2) {
            if (o1.start != o2.start) {
                return Integer.compare(o1.start, o2.start);
            } else if (o1.end != o2.end) {
                return Integer.compare(o1.end, o2.end);
            } else {
                // in case there rae duplicated elements, which is allowed in this test case
                return -1;
            }
        }
    }

    class EventEndComparator implements Comparator<Event> {

        @Override
        public int compare(Event o1, Event o2) {
            if (o1.end != o2.end) {
                return Integer.compare(o1.end, o2.end);
            } else if (o1.start != o2.start) {
                return Integer.compare(o1.start, o2.start);
            } else {
                // used by priority queue, not really important whether this is 0 or 1
                return 0;
            }
        }
    }

    TreeSet<Event> intervals = new TreeSet<>(new EventStartComparator());

    public int book(int start, int end) {
        Event curEvent = new Event(start, end);
        intervals.add(curEvent);
        if (intervals.size() == 1) {
            return 1;
        }
        // sort by end point, small to big
        PriorityQueue<Event> pq = new PriorityQueue<>(new EventEndComparator());

        Iterator<Event> it = intervals.iterator();
        pq.offer(it.next());
        int needed = 1;
        while (it.hasNext()) {
            Event cur = it.next();
            while (!pq.isEmpty() && pq.peek().end <= cur.start) {
                pq.poll();
            }
            // whatever in queue right now has conflicts with this one and we need conf room here
            if (!pq.isEmpty()) {
                needed = Math.max(needed, pq.size() + 1);
            }
            pq.offer(cur);
        }

        return needed;
    }
}
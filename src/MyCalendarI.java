import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyCalendarI {

    public static void main(String[] args) {
        MyCalendar mc = new MyCalendar();
        mc.book(47,50);
        mc.book(33,41);
    }
}

// almost similar code to my calendar ii
class MyCalendar {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int active = 0;
        for (Integer k : map.keySet()) {
            active += map.get(k);
            if (active == 2) {
                // unwind our change before
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                if (map.get(start) == 0) {
                    map.remove(start); // to accelerate it
                }
                return false;
            }
        }
        return true;
    }
}

class MyCalendarAlternative {

    class Event {
        int start;
        int end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class EventComparator implements Comparator<Event> {

        @Override
        public int compare(Event o1, Event o2) {
            if (o1.start != o2.start) {
                return Integer.compare(o1.start, o2.start);
            } else if (o1.end != o2.end) {
                return Integer.compare(o1.end, o2.end);
            } else {
                return 0;
            }
        }
    }

    TreeSet<Event> events = new TreeSet<>(new EventComparator());

    public MyCalendarAlternative() {

    }

    public boolean book(int start, int end) {
        Event ev = new Event(start, end);
        Event after = events.ceiling(ev);
        Event before = events.floor(ev);
        int startBound = -1;
        int endBound = -1;
        if (before != null) {
            startBound = before.end;
        }
        if (after != null) {
            endBound = after.start;
        }
        if (startBound != -1 && start < startBound) {
            return false;
        }
        if (endBound != -1 && end > endBound) {
            return false;
        }
        events.add(ev);
        return true;
    }
}
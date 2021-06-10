import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyCalendarI {

    private class MyCalendar {

        private TreeMap<Integer, Integer> calendar;
        // non overlapping, can use this since each start will only have one end

        public MyCalendar() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer lowerStart = calendar.lowerKey(end);
            if (lowerStart == null || calendar.get(lowerStart) <= start) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }

}

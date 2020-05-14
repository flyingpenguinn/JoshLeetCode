import base.Interval;

import java.util.*;

public class MyCalendarII {
    public static void main(String[] args) {
        MyCalendarTwo mct = new MyCalendarTwo();
        System.out.println(mct.book(10, 20)); // returns true
        System.out.println(mct.book(50, 60)); // returns true
        System.out.println(mct.book(10, 40)); // returns true
        System.out.println(mct.book(5, 15)); // returns false
        System.out.println(mct.book(5, 10)); // returns true

    }
}

class MyCalendarTwo {

    // calendar interval problems can be solved by this trick of treemap start +1 end -1
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int s, int e) {
        int ns = map.getOrDefault(s, 0) + 1;
        map.put(s, ns);
        int ne = map.getOrDefault(e, 0) - 1;
        map.put(e, ne);
        int r = 0;
        for (Integer d : map.values()) {
            r += d;
            if (r == 3) {
                // unwind our change before
                if (ns == 1) {
                    map.remove(s);
                } else {
                    map.put(s, ns - 1);
                }
                if (ne == -1) {
                    map.remove(e);
                } else {
                    map.put(e, ne + 1);
                }
                return false;
            }
        }
        return true;
    }
}


class MyCalendarTwoFancierMap {
    // use a summap to remember the sum
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    TreeMap<Integer, Integer> sum = new TreeMap<>();

    public MyCalendarTwoFancierMap() {

    }

    public boolean book(int s, int e) {
        Integer lower = sum.lowerKey(s);
        int r = lower == null ? 0 : sum.get(lower);
        int or = r;
        Integer k = s;
        while (k != null && k < e) {
            r += tm.getOrDefault(k, 0);
            if (k == s) {
                r++;
            }
            if (r >= 3) {
                return false;
            }
            k = tm.higherKey(k);
        }
        tm.put(s, tm.getOrDefault(s, 0) + 1);
        tm.put(e, tm.getOrDefault(e, 0) - 1);
        k = s;
        r = or;
        while (k != null && k <= e) {
            r += tm.get(k);
            sum.put(k, r);
            k = tm.higherKey(k);
        }
        return true;
    }
}
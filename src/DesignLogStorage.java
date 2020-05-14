import java.util.*;


public class DesignLogStorage {
    public static void main(String[] args) {
        LogSystem ls = new LogSystem();
        ls.put(1, "2017:01:01:23:59:59");
        ls.put(2, "2017:01:01:22:59:59");
        ls.put(3, "2016:01:01:00:00:00");
        System.out.println(ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"));
        // return [1,2,3], because you need to return all logs within 2016 and 2017.
        System.out.println(ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
        // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
    }
}


class LogSystem {
    // the maxlimit/minlimit trick is important
    // also we don't really need to get the exact date diff from 2000-1-1, as long as order is maintained relatively we are good
    // every month can have 31 days due to the same reason above
    String[] indexs = {"Year", "Month", "Day", "Hour", "Minute", "Second"};
    // all convert to seconds. we dont care if the month stuff is accurate
    int[] multi = {60 * 60 * 24 * 31 * 12, 60 * 60 * 24 * 31, 60 * 60 * 24, 60 * 60, 60, 1};
    int[] maxlimit = {1, 12, 31, 23, 59, 59};
    int[] minlimit = {1, 1, 1, 0, 0, 0};

    Map<Integer, Integer> logs = new HashMap<>();

    public void put(int id, String timestamp) {
        logs.put(id, calc(timestamp));
    }

    private int calc(String timestamp) {
        String[] ts = timestamp.split(":");
        int r = 0;
        for (int i = 0; i < ts.length; i++) {
            int v = Integer.valueOf(ts[i]);
            r += v * multi[i];
        }
        return r;
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int index = find(gra);
        int start = 0;
        int end = 0;
        String[] ss = s.split(":");
        String[] es = e.split(":");
        int i = 0;
        for (; i < ss.length && i <= index; i++) {
            start += multi[i] * Integer.valueOf(ss[i]);
            end += multi[i] * Integer.valueOf(es[i]);
        }
        for (; i < ss.length; i++) {
            start += minlimit[i] * multi[i];
            end += maxlimit[i] * multi[i];
        }
        List<Integer> r = new ArrayList<>();
        for (int k : logs.keySet()) {
            Integer lv = logs.get(k);
            if (lv >= start && lv <= end) {
                r.add(k);
            }
        }
        return r;
    }

    private int find(String s) {
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
}
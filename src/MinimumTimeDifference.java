import java.util.*;

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() <= 1) {
            return 0;
        }
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < timePoints.size(); i++) {
            int converted = convert(timePoints.get(i));
            if (ts.contains(converted)) {
                return 0;
            }
            ts.add(converted);
        }
        Iterator<Integer> iterator = ts.iterator();
        int first = iterator.next();
        int cur = first;
        int mindiff = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            int next = iterator.next();
            mindiff = Math.min(mindiff, next - cur);
            cur = next;
        }
        mindiff = Math.min(first + 24 * 60 - cur, mindiff);
        return mindiff;
    }

    private int convert(String a) {
        String[] splitted = a.split(":");
        return 60 * Integer.valueOf(splitted[0]) + Integer.valueOf(splitted[1]);
    }
}

import base.ArrayUtils;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class MinMovesToMakeArrayComplementary {

    // here we use line sweeping. can also use binary search to look for good Rs and minus bad Ls
    public int minMoves(int[] a, int lim) {
        int n = a.length;
        int pairs = n / 2;
        List<Event> events = new ArrayList<>();
        Map<Integer, Integer> hits = new HashMap<>();
        for (int i = 0; i < pairs; i++) {
            int v1 = a[i], v2 = a[n - 1 - i];
            int minv = Math.min(v1, v2), maxv = Math.max(v1, v2);
            int L = 1 + minv, R = lim + maxv;
            events.add(new Event(L, 1));
            events.add(new Event(R + 1, -1));
            hits.put(v1 + v2, hits.getOrDefault(v1 + v2, 0) + 1);
        }
        Set<Integer> posSet = new HashSet<>();
        for (Event e : events) posSet.add(e.pos);
        posSet.addAll(hits.keySet());
        List<Integer> posList = new ArrayList<>(posSet);
        Collections.sort(posList);
        events.sort(Comparator.comparingInt(e -> e.pos));
        int idx = 0, active = 0, best = Integer.MAX_VALUE, base = 2 * pairs;
        for (int pos : posList) {
            while (idx < events.size() && events.get(idx).pos == pos) {
                active += events.get(idx).delta;
                idx++;
            }
            int hitCount = hits.getOrDefault(pos, 0);
            best = Math.min(best, base - active - hitCount);
        }
        return best;
    }

    private static class Event {
        int pos, delta;

        Event(int p, int d) {
            pos = p;
            delta = d;
        }

    }


    public static void main(String[] args) {
        System.out.println(new MinMovesToMakeArrayComplementary().minMoves(ArrayUtils.read1d("[3,1,1,1,2,3,2,3,1,3,2,1]"), 3));
    }

}

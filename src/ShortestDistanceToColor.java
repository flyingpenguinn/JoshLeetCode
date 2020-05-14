import base.ArrayUtils;

import java.util.*;

public class ShortestDistanceToColor {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            map.computeIfAbsent(colors[i], k -> new TreeSet<>()).add(i);
        }
        List<Integer> r = new ArrayList<>();
        for (int[] q : queries) {
            int color = q[1];
            int start = q[0];
            TreeSet<Integer> indexes = map.getOrDefault(color, new TreeSet<>());
            Integer ceil = indexes.ceiling(start);
            Integer floor = indexes.floor(start);
            if (ceil == null && floor == null) {
                r.add(-1);
            } else if (ceil == null) {
                r.add(start - floor);
            } else if (floor == null) {
                r.add(ceil - start);
            } else {
                r.add(Math.min(start - floor, ceil - start));
            }
        }
        return r;
    }

}

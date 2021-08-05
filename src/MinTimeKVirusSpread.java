import base.ArrayUtils;

import java.util.*;

public class MinTimeKVirusSpread {
    private boolean good(int[][] points, long day, int k) {
        int n = points.length;
        TreeMap<Long, TreeMap<Long, Long>> lines = new TreeMap<>(); // map is sorted by the key automatically (so no need for sorted)
        for (int i = 0; i < n; i++) {
            long x = points[i][0];
            long y = points[i][1];
            long lbx = x;
            long lby = y - day;
            long ubx = x - day;
            long uby = y;
            update(lines, lbx + lby, lby - lbx, 1);
            update(lines, ubx + uby, uby - ubx + 1, -1);
            lbx = x + day;
            lby = y;
            ubx = x;
            uby = y + day;
            update(lines, lbx + lby + 1, lby - lbx, -1);
            update(lines, ubx + uby + 1, uby - ubx + 1, 1);
        }
        TreeMap<Long, Long> r = new TreeMap<>();
        for (Long k1 : lines.keySet()) {
            Map<Long, Long> m = lines.get(k1);
            for (long k2 : m.keySet()) {
                update(r, k2, m.get(k2));
            }

            long c = 0;
            for (Long v : r.values()) {
                c += v;
                if (c >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    public int minDayskVariants(int[][] points, int k) {
        long l = 0;
        long u = 1000000000;
        while (l <= u) {
            long middle = l + (u - l) / 2;
            if (good(points, middle, k)) {
                u = middle - 1;
            } else l = middle + 1;
        }
        return (int) l;
    }


    private void update(TreeMap<Long, Long> m, long k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        m.put(k, nv);
    }

    private void update(TreeMap<Long, TreeMap<Long, Long>> m, long k1, long k2, int d) {
        TreeMap<Long, Long> inner = m.getOrDefault(k1, new TreeMap<>());
        long nv = inner.getOrDefault(k2, 0L) + d;
        inner.put(k2, nv);
        m.put(k1, inner);
    }

    public static void main(String[] args) {
        System.out.println(new MinTimeKVirusSpread().minDayskVariants(ArrayUtils.read("[[1,1],[6,1]]"), 2));
    }

}

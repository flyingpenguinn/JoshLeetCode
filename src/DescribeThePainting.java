import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DescribeThePainting {
    public List<List<Long>> splitPainting(int[][] isegments) {
        long[][] segments = new long[isegments.length][3];
        for (int i = 0; i < segments.length; i++) {
            for (int j = 0; j < 3; j++) {
                segments[i][j] = isegments[i][j];
            }
        }
        List<List<Long>> res = new ArrayList<>();
        TreeMap<Long, Long> m = new TreeMap<>();
        for (long[] seg : segments) {
            m.put(seg[0], m.getOrDefault(seg[0], 0L) + seg[2]);
            m.put(seg[1], m.getOrDefault(seg[1], 0L) - seg[2]);
        }
        long csum = 0;
        for (long point : m.keySet()) {
            long delta = m.get(point);
            csum += delta;
            if (!res.isEmpty()) {
                int rn = res.size();
                res.get(rn - 1).set(1, point);
            }
            if (csum != 0) {
                List<Long> cr = new ArrayList<>();
                cr.add(point);
                cr.add(-1L);
                cr.add(csum);
                res.add(cr);
            }
        }
        return res;
    }
}

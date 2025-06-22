import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FindMaxAreaOfTriangle {
    public long maxArea(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Map<Long, TreeSet<Long>> ys = new HashMap<>();
        Map<Long, TreeSet<Long>> xs = new HashMap<>();
        TreeSet<Long> allx = new TreeSet<>();
        TreeSet<Long> ally = new TreeSet<>();
        for (int i = 0; i < m; ++i) {
            long x = a[i][0];
            long y = a[i][1];
            ys.computeIfAbsent(y, k -> new TreeSet<>()).add(x);
            xs.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
            allx.add(x);
            ally.add(y);
        }

        long yres = solve(ys, ally);
        long xres = solve(xs, allx);
        long res = Math.max(yres, xres);
        if (res == 0) {
            return -1;
        }
        return res;
    }

    protected long solve(Map<Long, TreeSet<Long>> ys, TreeSet<Long> ally) {
        long res = -1;
        for (long y : ys.keySet()) {
            long minx = ys.get(y).first();
            long maxx = ys.get(y).last();
            if (minx == maxx) {
                continue;
            }
            long maxbase = maxx - minx;
            long d1 = Math.abs(ally.first() - y);
            long d2 = Math.abs(ally.last() - y);
            long cur = Math.max(d1 * maxbase, d2 * maxbase);
            res = Math.max(res, cur);
        }
        return res;
    }
}

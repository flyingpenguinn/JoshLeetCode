import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class MaxNumberOfInterSections {
    // count overlaps with some twist that
    // +1 -1 to accomodate double counting on end points
    // *2 so that optimal value happens at some integer not int he middle
    public int maxIntersectionCount(int[] y) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        int n = y.length;
        List<int[]> ints = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            int v1 = 2 * y[i - 1];
            int v2 = 2 * y[i] + (i == n - 1 ? 0 : y[i] > y[i - 1] ? -1 : 1);
            if (i == n - 1) {
                v2 = 2 * y[i];
            }
            int vmin = Math.min(v1, v2);
            int vmax = Math.max(v1, v2);
            //    System.out.println(vmin+"-->"+vmax);
            ints.add(new int[]{vmin, vmax});
        }
        Collections.sort(ints, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });

        for (int[] in : ints) {
            int v1 = in[0];
            int v2 = in[1];
            update(m, v1, 1);
            update(m, v2 + 1, -1);
        }
        // System.out.println(m);
        int csum = 0;
        int res = 0;
        for (int k : m.keySet()) {
            csum += m.get(k);
            res = Math.max(res, csum);
        }
        return res;
    }

    private void update(TreeMap<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        m.put(k, nv);
    }
}

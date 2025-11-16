import java.util.ArrayList;
import java.util.List;

public class CountStableSubarrays {
    public long[] countStableSubarrays(int[] a, int[][] queries) {
        int n = a.length;
        List<int[]> intervals = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && a[j] >= a[j - 1]) {
                ++j;
            }
            // i.. j-1
            intervals.add(new int[]{i, j - 1});
            i = j;
        }

        intervals.sort((x, y) -> Integer.compare(x[0], y[0]));
        long[] psum = new long[intervals.size()];
        for (i = 0; i < psum.length; ++i) {
            psum[i] = (i == 0 ? 0 : psum[i - 1]) + getsum(intervals.get(i)[0], intervals.get(i)[1]);
        }
        long[] res = new long[queries.length];
        int ri = 0;
        for (int[] q : queries) {
            int v1 = q[0];
            int v2 = q[1];
            int p1 = binary1(intervals, v1);
            int p2 = binary1(intervals, v2);
            long cur = Math.max(0, (p2 <= 0 ? 0 : psum[p2 - 1]) - (p1 < 0 ? 0 : psum[p1]));
            if (p1 != -1 && v1 <= intervals.get(p1)[1]) {
                int remp1start = Math.max(intervals.get(p1)[0], v1);
                int remp1end = Math.min(intervals.get(p1)[1], v2);
                long remp1 = getsum(remp1start, remp1end);
                cur += remp1;
            }
            if (p1 != p2 && p2 < n && v2 <= intervals.get(p2)[1]) {
                int remp2end = Math.min(v2, intervals.get(p2)[1]);
                int remp2start = Math.max(v1, intervals.get(p2)[0]);
                long remp2 = getsum(remp2start, remp2end);
                cur += remp2;
            }
            res[ri++] = cur;
        }
        return res;
    }

    private long getsum(long a, long b) {
        long len = b - a + 1;
        return (1 + len) * len / 2;
    }

    private int binary1(List<int[]> a, int t) {
        int n = a.size();
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid)[0] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}

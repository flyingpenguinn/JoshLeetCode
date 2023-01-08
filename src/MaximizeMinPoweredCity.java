import java.util.ArrayDeque;
import java.util.Deque;

public class MaximizeMinPoweredCity {
    public long maxPower(int[] a, int r, int k) {
        int n = a.length;
        long[] m = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            int left = Math.max(i - r, 0);
            int right = Math.min(i + r + 1, n);
            m[left] += a[i];
            m[right] -= a[i];
        }
        long[] p = new long[n];
        long accu = 0;
        for (int i = 0; i < n; ++i) {
            accu += m[i];
            p[i] = accu;
        }
        long l = 0;
        long u = (long) 1e15;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (possible(p, r, k, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    // if we need to plant at i, we let it benefit till i+2*r. basically, plant at i+r
    private boolean possible(long[] a, int r, long k, long t) {
        int n = a.length;
        Deque<long[]> q = new ArrayDeque<>();
        long accu = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] < t) {
                while (!q.isEmpty() && q.peekFirst()[1] < i - 2 * r) {
                    accu -= q.pollFirst()[0];
                }
                long di = t - a[i];
                di -= accu;
                if (di < 0) {
                    // dont need to plant anything in this case
                    continue;
                }
                if (k < di) {
                    return false;
                }
                k -= di;
                q.offerLast(new long[]{di, i});
                accu += di;
            }
        }

        return true;
    }
}

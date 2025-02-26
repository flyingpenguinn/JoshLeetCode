import java.util.Arrays;

public class MaxDistanceBetweenPointsOnASquare {
    // TODO
    // Map a boundary point (x,y) to a coordinate in [0, 4*side)
    private long mapPoint(int side, int x, int y) {
        // bottom: (x,0) -> t = x.
        // right: (side,y) -> t = side + y.
        // top: (x,side) -> t = 3*side - x.
        // left: (0,y) -> t = 4*side - y.
        if (y == 0) return x;
        if (x == side) return side + y;
        if (y == side) return 3L * side - x;
        return 4L * side - y;
    }

    // Given sorted 1D positions t (mapped from points) and candidate distance d,
    // check if we can select k points around the circle (perimeter L = 4*side)
    // so that every adjacent gap (in circular order) is at least d.
    private boolean canPlace(long[] t, int k, int side, int d) {
        int n = t.length;
        long L = 4L * side;

        // Build an "extended" array: ext[i] = t[i] for i in [0, n) and ext[i+n] = t[i] + L.
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = t[i];
            ext[i + n] = t[i] + L;
        }

        // For each possible starting index i (in the original sorted array)
        for (int i = 0; i < n; i++) {
            int count = 1;
            long pos = ext[i];
            int idx = i;

            // We only consider indices up to i+n (i.e., one full circle).
            for (int cnt = 1; cnt < k; cnt++) {
                long target = pos + d;
                // Find the first position that is >= target
                int nextIdx = Arrays.binarySearch(ext, idx + 1, i + n, target);
                if (nextIdx < 0) nextIdx = -nextIdx - 1; // Get the insertion point

                if (nextIdx >= i + n) {
                    count = -1; // not enough points available from this start
                    break;
                }
                idx = nextIdx;
                pos = ext[idx];
                count++;
            }

            // After selecting k points, check the wrap-around gap:
            // The gap from the last chosen point (at pos) to (first + L) must be at least d.
            if (count == k && (ext[i] + L - pos) >= d)
                return true;
        }
        return false;
    }

    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] t = new long[n];

        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            t[i] = mapPoint(side, x, y);
        }
        Arrays.sort(t);

        // Binary search candidate d in [0, 2*side].
        int lo = 0, hi = 2 * side, ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canPlace(t, k, side, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}



public class MaximizeMinGameScore {
    // TODO: try binary search myself
    public long maxScore(int[] points, int m) {
        int n = points.length;

        // If m < n, we can't meet the condition (the C++ code does the same check).
        if (m < n) {
            return 0;
        }

        return binarySearchMaxScore(points, m);
    }

    /**
     * This method checks whether it is possible to achieve at least `val`
     * under the operation constraints, using no more than `m` operations.
     */
    private boolean can(long val, int[] points, int m) {
        long total = 0, transfer = 0, skipAdd = 0;

        for (int point : points) {
            // If we already exceed the number of allowed operations, break early.
            if (total > m) {
                break;
            }

            // The number of times we need to multiply 'point' to reach at least 'val'.
            long necessary = (val + point - 1) / point;  // integer division ceiling for (val / point)

            if (transfer >= necessary) {
                // We have enough "transfer" from previous operations to meet 'val' without extra multiplications.
                transfer = 0;
                skipAdd++;
            } else {
                // Need to do new operations
                long p = transfer * point;  // partial product from the leftover transfer
                long ops = ( (val - p) + (point - 1) ) / point; // again, ceil division

                // According to the C++ logic, cost = 2*ops - 1 plus any skipAdd
                total += (2 * ops - 1);
                total += skipAdd;

                // Update transfer and skipAdd
                transfer = Math.max(ops - 1, 0);
                skipAdd = 0;
            }
        }

        return (total <= m);
    }

    /**
     * Performs a binary search for the maximum `val` (score) that can return true in `can(...)`.
     */
    private long binarySearchMaxScore(int[] points, int m) {
        long l = 1;
        long r = 1000000000000000000L; // 1e18
        long ans = 0;

        while (l <= r) {
            long mid = l + (r - l) / 2;

            if (can(mid, points, m)) {
                ans = mid;     // mid is possible, try for a higher value
                l = mid + 1;
            } else {
                r = mid - 1;   // mid is not possible, try lower
            }
        }

        return ans;
    }
}

public class MinCostToDeleteArrayBySubarrays {
    // LC 4123 (Weekly 529 Q4), redo 2026-11-24 with parity repair (first version: dp(i, parity), solved ~10 min after the contest).
    // Rewrite: each deletion pays its two endpoints -> ask which elements end up paying (swap the sum, M16).
    // Every deletion charges exactly 2 -> the paid count is even (invariant, M5); a[0] and a[n-1] are always endpoints (M3),
    // so the interior pays an even-size subset, and any even-size subset is achievable (nested deletions).
    // Min even-size subset sum = parity repair (same template as 1975): take all negatives; if their count is odd,
    // either drop the largest negative or add the smallest non-negative.
    public long minCost(int[] a) {
        int n = a.length;
        long neg = 0;
        long maxneg = (long) -1e18;
        long minpos = (long) 1e18;
        int negcount = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (a[i] < 0) {
                neg += a[i];
                maxneg = Math.max(maxneg, a[i]);
                ++negcount;
            } else {
                minpos = Math.min(minpos, a[i]);
            }
        }
        if (negcount % 2 == 0) {
            return a[0] + a[n - 1] + neg;
        } else {
            long way1 = neg - maxneg;
            long way2 = neg + minpos;
            long minben = Math.min(way1, way2);
            minben = Math.min(0, minben);
            return a[0] + a[n - 1] + minben;
        }
    }
}

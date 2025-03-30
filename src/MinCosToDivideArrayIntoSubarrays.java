public class MinCosToDivideArrayIntoSubarrays {

    public long minimumCost(int[] nums, int[] cost, int K) {
        int n = nums.length;

        // Build prefix sums (1-based)
        long[] sn = new long[n + 1];  // sn[i] = sum of nums[0..i-1]
        long[] sc = new long[n + 1];  // sc[i] = sum of cost[0..i-1]
        for (int i = 1; i <= n; i++) {
            sn[i] = sn[i - 1] + nums[i - 1];
            sc[i] = sc[i - 1] + cost[i - 1];
        }

        // We'll use a DP array f[i]: the minimum cost to partition the first i elements
        // (i.e., nums[0..i-1]) according to the rearranged cost formula.
        final long INF = (long) 1e18;
        long[] f = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            f[i] = INF;
        }
        f[0] = 0;

        // Fill f[i] in O(n^2)
        // for each i, we consider the "last cut" at position j in [0..i).
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // t = sn[i] * (sc[i] - sc[j]) + K * (sc[n] - sc[j])
                long t = sn[i] * (sc[i] - sc[j]) + (long) K * (sc[n] - sc[j]);
                f[i] = Math.min(f[i], f[j] + t);
            }
        }

        return f[n];
    }


}

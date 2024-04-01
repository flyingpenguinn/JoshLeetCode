public class CountLaternatingSubarrays {
    public long countAlternatingSubarrays(int[] a) {
        int n = a.length;
        long[] dp = new long[n];
        dp[0] = 1;
        long res = 1;
        for (int i = 1; i < n; ++i) {
            if (a[i] != a[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            res += dp[i];
        }
        return res;
    }
}

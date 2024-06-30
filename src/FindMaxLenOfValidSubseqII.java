public class FindMaxLenOfValidSubseqII {
    // book keeping method, not backpack ! note subseq related question can also be solved by book keeping method
    public int maximumLength(int[] a) {
        int k = 2;
        int n = a.length;
        int[][] dp = new int[n][k];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int mod = (a[i] + a[j]) % k;
                dp[i][mod] = Math.max(dp[i][mod], dp[j][mod] + 1);
                res = Math.max(res, dp[i][mod]);
            }
        }
        return res + 1;
    }
}

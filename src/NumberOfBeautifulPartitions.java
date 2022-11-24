public class NumberOfBeautifulPartitions {
    private String primes = "2357";
    private long mod = (long) (1e9 + 7);

    private boolean isPrime(char c) {
        return primes.indexOf(c) != -1;
    }

    public int beautifulPartitions(String s, int k, int minl) {
        int n = s.length();
        long[][] dp = new long[n + 1][k + 1];
        dp[n][0] = 1;
        char[] sc = s.toCharArray();
        for (int j = 1; j <= k; ++j) {
            long[] later = new long[n];
            long latersum = 0;
            for (int i = n - 1; i >= 0; --i) {
                int nindex = Math.max(i + 1, i + minl - 1);
                if (nindex < n) {
                    latersum += later[nindex];
                }
                if (!isPrime(sc[i])) {
                    later[i] = dp[i + 1][j - 1];
                } else {
                    long cur = latersum;
                    dp[i][j] = cur;
                    dp[i][j] %= mod;
                }
            }
        }
        return (int) dp[0][k];
    }
}

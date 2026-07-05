public class CountDistinctWaysToFormTarget {
    private static final long MOD = 1_000_000_007L;

    public int interleaveCharacters(String w1, String w2, String t) {
        int n1 = w1.length();
        int n2 = w2.length();

        long[][][] dp = new long[n1 + 1][n2 + 1][4];
        dp[0][0][0] = 1;

        for (int k = 0; k < t.length(); ++k) {
            char c = t.charAt(k);
            long[][][] next = new long[n1 + 1][n2 + 1][4];

            for (int j = 0; j <= n2; ++j) {
                for (int mask = 0; mask < 4; ++mask) {
                    long sum = 0;

                    for (int i = 0; i < n1; ++i) {
                        sum += dp[i][j][mask];
                        sum %= MOD;

                        if (w1.charAt(i) == c) {
                            next[i + 1][j][mask | 1] += sum;
                            next[i + 1][j][mask | 1] %= MOD;
                        }
                    }
                }
            }

            for (int i = 0; i <= n1; ++i) {
                for (int mask = 0; mask < 4; ++mask) {
                    long sum = 0;

                    for (int j = 0; j < n2; ++j) {
                        sum += dp[i][j][mask];
                        sum %= MOD;

                        if (w2.charAt(j) == c) {
                            next[i][j + 1][mask | 2] += sum;
                            next[i][j + 1][mask | 2] %= MOD;
                        }
                    }
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int i = 0; i <= n1; ++i) {
            for (int j = 0; j <= n2; ++j) {
                ans += dp[i][j][3];
                ans %= MOD;
            }
        }

        return (int) ans;
    }
}

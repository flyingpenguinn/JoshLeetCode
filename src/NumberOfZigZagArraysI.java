public class NumberOfZigZagArraysI {
    private long gap(long[] lsum, int start, int end) {
        if (start > end) {
            return 0;
        }
        long raw = lsum[end] - (start == 0 ? 0 : lsum[start - 1]);
        raw %= Mod;
        if (raw < 0) {
            raw += Mod;
        }
        return raw;
    }

    public int zigZagArrays(int n, int l, int r) {
        dp = new long[n + 1][3][r + 1];
        for (int i = l; i <= r; ++i) {
            for (int j = l; j <= r; ++j) {
                if (i == j) {
                    continue;
                }
                int dir = i > j ? 0 : 1;
                dp[n - 2][dir][i] += 1;
            }
        }
        long[][] lsum = new long[3][r + 1];
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j <= r; ++j) {
                lsum[i][j] = (j == 0 ? 0 : lsum[i][j - 1]) + dp[n - 2][i][j];
            }
        }
        long rres = 0;
        for (int i = n - 3; i >= 0; --i) {
            long[][] csum = new long[3][r + 1];
            for (int last = l; last <= r; ++last) {
                for (int dir = 0; dir <= 1; ++dir) {
                    long res = 0;

                    if (dir == 0) {
                        // last time we went bigger. this time go smaller
                        long later = gap(lsum[1], l, last - 1);
                        res += later;
                        res %= Mod;
                    } else {
                        long later = gap(lsum[0], last + 1, r);
                        res += later;
                        res %= Mod;
                    }

                    dp[i][dir][last] = res;
                    csum[dir][last] = (last == 0 ? 0 : csum[dir][last - 1]) + res;
                    if (i == 0) {
                        rres += res;
                        rres %= Mod;
                    }
                }
            }
            lsum = csum;
        }
        return (int) rres;
    }

    private long[][][] dp;

    private long Mod = (long) (1e9 + 7);
}

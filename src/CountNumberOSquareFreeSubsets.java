public class CountNumberOSquareFreeSubsets {
    // bitmask on prime subset. no prime can happen >=2 times because otherwise it forms a square number
    private int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    private long mod = (long) (1e9 + 7);
    private Long[][] dp;

    public int squareFreeSubsets(int[] a) {
        dp = new Long[a.length][(1 << 10)];
        return (int) solve(a, 0, 0) - 1;
    }

    private long solve(int[] a, int i, int st) {
        int n = a.length;
        if (i == n) {
            return 1;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        long way1 = solve(a, i + 1, st);
        int nst = st;
        boolean bad = false;
        for (int j = 0; j < primes.length; ++j) {
            int p = primes[j];
            int count = 0;
            int rawai = a[i];
            while (rawai % p == 0) {
                ++count;
                rawai /= p;
            }
            if (count == 0) {
                continue;
            } else if (count > 1) {
                bad = true;
                break;
            } else {
                if (((st >> j) & 1) == 1) {
                    bad = true;
                    break;
                }
            }
            nst |= (1 << j);
        }
        long way2 = bad ? 0 : solve(a, i + 1, nst);
        long cur = way1 + way2;
        cur %= mod;
        dp[i][st] = cur;
        return cur;
    }
}

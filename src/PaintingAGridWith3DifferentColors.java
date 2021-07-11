public class PaintingAGridWith3DifferentColors {
    // borderline dp. maintain the state of the last m (at most 5) grids. here we use base 3. can also use base 4 and make 2 digits corresponding to 1 grid.
    private int m = 0;
    private int n = 0;
    private Long[][][] dp;
    private long mod = 1000000007;

    public int colorTheGrid(int m, int n) {
        this.m = m;
        this.n = n;
        dp = new Long[243][m][n];
        return (int) solve(0, 0, 0);
    }

    long solve(int st, int i, int j) {
        if (i == 0 && j == n) {
            return 1;
        }
        if (dp[st][i][j] != null) {
            return dp[st][i][j];
        }
        int last = (i == 0) ? -1 : getlast(st);
        int first = j == 0 ? -1 : getfirst(st);
        long res = 0;
        for (int k = 0; k <= 2; k++) {
            if (k != last && k != first) {
                int nst = 0;
                if (j == 0) {
                    nst = st * 3 + k;
                } else {
                    nst = (st - firstonly(first, m - 1)) * 3 + k;
                }
                if (i + 1 < m) {
                    res += solve(nst, i + 1, j);
                    res %= mod;
                } else {
                    res += solve(nst, 0, j + 1);
                    res %= mod;
                }
            }
        }
        dp[st][i][j] = res;
        return res;
    }

    int getlast(int st) {
        return st % 3;
    }

    int getfirst(int st) {
        int dig = 0;
        int bits = 0;
        while (st > 0) {
            bits++;
            dig = st % 3;
            st = (st - dig) / 3;
        }
        return bits < m ? 0 : dig;
    }

    int firstonly(int first, int m) {
        int base = 1;
        while (m > 0) {
            base *= 3;
            m--;
        }
        return first * base;
    }
}

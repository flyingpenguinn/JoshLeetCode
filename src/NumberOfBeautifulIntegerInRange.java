public class NumberOfBeautifulIntegerInRange {
    private Integer[][][][] dp;

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        int v1 = solve(high, k);
        int v2 = solve(low, k);

        int res = v1 - v2;
        if (isbeauti(low, k)) {
            ++res;
        }
        return res;
    }

    private boolean isbeauti(int n, int k) {
        if (n % k != 0) {
            return false;
        }
        int ec = 0;
        int oc = 0;
        while (n > 0) {
            int d = n % 10;
            if (d % 2 == 1) {
                ++oc;
            } else {
                ++ec;
            }
            n /= 10;
        }
        return ec == oc;
    }

    private int solve(int t, int k) {
        final String st = String.valueOf(t);
        int len = st.length();
        int res = 0;
        for (int l = 1; l < len; ++l) {
            dp = new Integer[l + 1][2][l + 12][k + 1];
            res += solve2(st, l, 0, 1, 0, 0, k);
        }
        dp = new Integer[len + 1][2][len + 12][k + 1];
        res += solve2(st, len, 0, 0, 0, 0, k);
        return res;
    }

    // even - odd = diff
    private int solve2(String st, int n, int i, int sm, int diff, int rem, int k) {
        if (i == n) {
            return diff == 0 && rem == 0 ? 1 : 0;
        }
        int rdiff = diff + 11;
        if (dp[i][sm][rdiff][rem] != null) {
            return dp[i][sm][rdiff][rem];
        }
        int res = 0;
        int tc = st.charAt(i) - '0';
        int start = i == 0 ? 1 : 0;
        for (int d = start; d <= 9; ++d) {
            if (sm == 0 && d > tc) {
                break;
            }
            int nsm = sm;
            if (sm == 0 && d < tc) {
                nsm = 1;
            }
            int parity = d % 2;
            int ndiff = diff;
            if (parity == 0) {
                ndiff += 1;
            } else {
                ndiff -= 1;
            }
            int nrem = rem;
            int later = n - 1 - i;
            int contri = d * (int) Math.pow(10, later);
            int crem = contri % k;
            nrem += crem;
            nrem %= k;
            int cur = solve2(st, n, i + 1, nsm, ndiff, nrem, k);
            res += cur;
        }
        dp[i][sm][rdiff][rem] = res;
        return res;
    }
}

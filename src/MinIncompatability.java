import java.util.Arrays;

public class MinIncompatability {
    // dp[size] = dp[sth]+dp[size-sth]  when both size and sth can divide t
    // in the worst case we need to iterate through c16/8 = 12870 items and calc their subset. that's roughly 3^12 so we were able to AC
    // we could have used similar approach as "k subset sum" but here i fear status can't really represent what we have chosen in this cycle though it passes AC...
    private int Max = 1000000;

    public int minimumIncompatibility(int[] a, int k) {
        int n = a.length;
        if (n == k) {
            return 0;
        }
        int t = n / k;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Max);
        for (int i = ((1 << t) - 1); i < (1 << n); i++) {
            int bits = Integer.bitCount(i);
            if (bits == t) {
                if (!valid(i, a)) {
                    continue;
                }
                int min = Max;
                int max = 0;
                for (int j = 0; j < n; j++) {
                    if (((i >> j) & 1) == 1) {
                        min = Math.min(min, a[j]);
                        max = Math.max(max, a[j]);
                    }
                }
                dp[i] = max - min;
            } else if (bits % t == 0) {
                for (int j = i; j > 0; j = ((j - 1) & i)) {
                    int bitsj = Integer.bitCount(j);
                    if (bitsj % t == 0) {
                        int p1 = dp[j];
                        int p2 = dp[i - j];
                        dp[i] = Math.min(dp[i], p1 + p2);
                    }
                }
            }
        }
        int rt = dp[(1 << n) - 1];
        return rt >= Max ? -1 : rt;
    }

    private boolean valid(int st, int[] a) {
        int n = a.length;
        boolean[] seen = new boolean[17];
        for (int i = 0; i < n; i++) {
            if (((st >> i) & 1) == 1) {
                if (seen[a[i]]) {
                    return false;
                }
                seen[a[i]] = true;
            }
        }
        return true;
    }
}

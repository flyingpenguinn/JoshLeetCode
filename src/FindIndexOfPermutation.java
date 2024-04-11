import java.util.Arrays;

public class FindIndexOfPermutation {
    private long mod = (long) (1e9 + 7);
    private long[] dp;
    private int bit[];

    private int f(int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void u(int i) {
        int n = bit.length;
        while (i < n) {
            bit[i] += 1;
            i += i & (-i);
        }
    }

    public int getPermutationIndex(int[] a) {
        int n = a.length;
        dp = new long[n + 1];
        bit = new int[n + 1];
        Arrays.fill(dp, -1);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int ext = f(v - 1);
            long cur = (v - 1 - ext) * fact(n - 1 - i);
            res += cur;
            res %= mod;
            u(v);
        }
        return (int) (res);
    }

    private long fact(int n) {
        if (n == 0) {
            return 1L;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        long cur = 1L * n * fact(n - 1);
        cur %= mod;
        dp[n] = cur;
        return cur;
    }
}

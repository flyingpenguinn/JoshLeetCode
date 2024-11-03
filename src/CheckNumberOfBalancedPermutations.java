import java.util.Arrays;

public class CheckNumberOfBalancedPermutations {
    private final long Mod = (long) (1e9 + 7);
    private long[][][] dp;
    private int oddi = 0;
    private int eveni = 0;
    private long sum = 0;
    private final int nums = 10;

    public int countBalancedPermutations(String s) {
        int n = s.length();
        initfact();
        oddi = 0;
        eveni = 0;
        sum = 0;

        int[] count = new int[10];

        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            ++count[cind];
            if (i % 2 == 0) {
                ++eveni;
            } else {
                ++oddi;
            }
            sum += cind;
        }
        dp = new long[nums][800][oddi + 1];

        for (int i = 0; i < nums; ++i) {
            for (int j = 0; j < 800; ++j) {
                Arrays.fill(dp[i][j], -1);
            }

        }
        if (sum % 2 != 0) {
            return 0;
        }
        sum /= 2;
        long rt = solve(count, 0, 0, oddi);

        return (int) rt;
    }

    private long solve(int[] a, int i, int osum, int indexes) {
        if (indexes < 0) {
            return 0;
        }
        if (i == nums) {
            if (osum != sum || indexes != 0) {
                return 0;
            } else {
                long ep = fact[eveni];
                long op = fact[oddi];
                long res = ep * op;
                res %= Mod;
                return res;
            }
        }
        if (dp[i][osum][indexes] != -1) {
            return dp[i][osum][indexes];
        }
        long res = 0;
        for (int j = 0; j <= a[i]; ++j) {
            int nosum = osum + j * i;
            long cur = solve(a, i + 1, nosum, indexes - j);
            cur *= modinverse(fact[j]);
            cur %= Mod;
            int toodd = a[i] - j;
            cur *= modinverse(fact[toodd]);
            cur %= Mod;
            res += cur;
            res %= Mod;
        }
        dp[i][osum][indexes] = res;
        return res;
    }

    private long[] fact = new long[101];

    private void initfact() {
        fact[0] = 1;
        for (int i = 1; i <= 100; ++i) {
            fact[i] = fact[i - 1] * i;
            fact[i] %= Mod;
        }
    }

    private long modinverse(long a) {
        long m = Mod;
        long y = 0;
        long x = 1;
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) {
            x += Mod;
        }
        return x;
    }
}

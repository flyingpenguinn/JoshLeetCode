public class CountEffectiveSubsequence {
    // TODO
    public int countEffective(int[] nums) {
        int Mod = 1000000007;
        int n = nums.length;
        int tot = 0;
        for (int i = 0; i < n; ++i) {
            tot |= nums[i];
        }
        if (tot == 0) {
            return 0;
        }
        int[] bs = new int[20];
        int m = 0;
        for (int b = 0; b < 20; ++b) {
            if (((tot >> b) & 1) == 1) {
                bs[m] = b;
                ++m;
            }
        }
        int full = (1 << m) - 1;
        int size = 1 << m;
        int[] freq = new int[size];
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            int mask = 0;
            for (int j = 0; j < m; ++j) {
                int bit = bs[j];
                if (((v >> bit) & 1) == 1) {
                    mask |= 1 << j;
                }
            }
            ++freq[mask];
        }
        int[] dp = new int[size];
        for (int i = 0; i < size; ++i) {
            dp[i] = freq[i];
        }
        for (int b = 0; b < m; ++b) {
            int bit = 1 << b;
            for (int mask = 0; mask < size; ++mask) {
                if ((mask & bit) != 0) {
                    dp[mask] += dp[mask ^ bit];
                }
            }
        }
        long[] p2 = new long[n + 1];
        p2[0] = 1;
        for (int i = 1; i <= n; ++i) {
            p2[i] = (p2[i - 1] * 2) % Mod;
        }
        long ans = 0;
        for (int mask = 1; mask <= full; ++mask) {
            int pc = Integer.bitCount(mask);
            int zeros = dp[full ^ mask];
            long val = p2[zeros];
            if ((pc & 1) == 1) {
                ans += val;
            } else {
                ans -= val;
            }
            if (ans >= Mod) {
                ans -= Mod;
            } else if (ans < 0) {
                ans += Mod;
            }
        }
        ans %= Mod;
        if (ans < 0) {
            ans += Mod;
        }
        return (int) ans;
    }

}

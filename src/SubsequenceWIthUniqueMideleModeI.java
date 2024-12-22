import java.util.HashMap;
import java.util.Map;

public class SubsequenceWIthUniqueMideleModeI {
    // TODO: review this myself
    public int subsequencesWithMiddleMode(int[] nums) {
        int n = nums.length;
        final int MOD = (int) 1e9 + 7;

        // Precompute combinations
        long[][] f = new long[n + 1][6];
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
            for (int j = 1; j <= i && j <= 5; j++) {
                f[i][j] = (f[i - 1][j] + f[i - 1][j - 1]) % MOD;
            }
        }

        // Discretize nums
        Map<Integer, Integer> mp = new HashMap<>();
        int m = 0;
        for (int x : nums) mp.put(x, 1);
        for (Map.Entry<Integer, Integer> p : mp.entrySet()) {
            mp.put(p.getKey(), m++);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = mp.get(nums[i]);
        }

        // Count occurrences
        int[] tot = new int[m];
        for (int x : nums) tot[x]++;

        long ans = 0;
        int[] cnt = new int[m];

        // Iterate over middle number
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            cnt[x]++;
            int remx = tot[x] - cnt[x];

            // Case: middle number appears at least 3 times
            for (int l = 0; l <= 2; l++) {
                for (int r = 0; r <= 2; r++) {
                    if (l + r >= 2) {
                        ans = (ans + C(f, cnt[x] - 1, l) * C(f, remx, r) % MOD * C(f, i - cnt[x], 2 - l) % MOD * C(f, n - i - remx, 2 - r)) % MOD;
                    }
                }
            }

            if (cnt[x] >= 2) {
                // Case: middle number appears exactly once in the first two numbers
                long t = (i - cnt[x]) * C(f, n - i - remx, 2) % MOD;
                for (int y = 0; y < m; y++) {
                    if (x != y) {
                        int remy = tot[y] - cnt[y];
                        t = (t - cnt[y] * remy % MOD * C(f, n - i - remx - 1, 1) % MOD + MOD) % MOD;
                        t = (t - C(f, remy, 2) * (i - cnt[x]) % MOD + MOD) % MOD;
                        t = (t + cnt[y] * C(f, remy, 2) * 2 % MOD) % MOD;
                    }
                }
                ans = (ans + t * (cnt[x] - 1)) % MOD;
            }

            if (remx >= 1) {
                // Case: middle number appears exactly once in the last two numbers
                long t = C(f, i - cnt[x], 2) * (n - i - remx) % MOD;
                for (int y = 0; y < m; y++) {
                    if (x != y) {
                        int remy = tot[y] - cnt[y];
                        t = (t - cnt[y] * remy % MOD * C(f, i - cnt[x] - 1, 1) % MOD + MOD) % MOD;
                        t = (t - C(f, cnt[y], 2) * (n - i - remx) % MOD + MOD) % MOD;
                        t = (t + C(f, cnt[y], 2) * remy * 2 % MOD) % MOD;
                    }
                }
                ans = (ans + t * remx) % MOD;
            }
        }

        return (int) ans;
    }

    private long C(long[][] f, int a, int b) {
        if (a < b) return 0;
        return f[a][b];
    }

}

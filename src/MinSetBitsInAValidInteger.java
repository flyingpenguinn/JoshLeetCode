public class MinSetBitsInAValidInteger {
    // TODO sos dp
    public int minSetBits(int[] nums, int k) {
        int n = nums.length;
        int B = 17;
        int m = 1 << B;
        int full = m - 1;

        int[] cnt = new int[B];
        boolean[] dp = new boolean[m];

        for (int i = 0; i < n; i++) {
            for (int b = 0; b < B; b++) {
                if ((nums[i] & (1 << b)) != 0) {
                    cnt[b]++;
                }
            }

            if (i >= k) {
                for (int b = 0; b < B; b++) {
                    if ((nums[i - k] & (1 << b)) != 0) {
                        cnt[b]--;
                    }
                }
            }

            if (i >= k - 1) {
                int x = 0;

                for (int b = 0; b < B; b++) {
                    if (cnt[b] == k) {
                        x |= 1 << b;
                    }
                }

                if (x == 0) return -1;
                dp[x] = true;
            }
        }

        // SOS DP:
        // dp[mask] = whether any window AND
        // is a submask of mask.
        for (int b = 0; b < B; b++) {
            for (int mask = 0; mask < m; mask++) {
                if ((mask & (1 << b)) != 0) {
                    dp[mask] |= dp[mask ^ (1 << b)];
                }
            }
        }

        int ans = B + 1;

        for (int y = 1; y < m; y++) {
            int complement = full ^ y;

            if (!dp[complement]) {
                ans = Math.min(ans, Integer.bitCount(y));
            }
        }

        return ans == B + 1 ? -1 : ans;
    }
}

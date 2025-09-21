import base.ArrayUtils;

import java.util.Arrays;

public class SubseqSumAfterCapping {
    // The key is
    // 1. With knapsack we can know any prefix and the sum whether it is doable- dp[i][v]
    // 2. We can enumerate how many x we pick
    // 3. We can just sort the array
    public boolean[] subsequenceSumAfterCapping(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        boolean[][] dp = new boolean[n + 1][k + 1];
        dp[0][0] = true;
        // using numbers up to i-1, can we make it
        for (int i = 1; i <= n; ++i) {
            dp[i] = Arrays.copyOf(dp[i - 1], k + 1);
            int cur = a[i - 1];
            for (int v = cur; v <= k; ++v) {
                if (dp[i - 1][v - cur]) {
                    dp[i][v] = true;
                }
            }
        }
        int i = 0;
        boolean[] res = new boolean[n];
        for (int x = 1; x <= n; ++x) {
            while (i < n && a[i] < x) {
                ++i;
            }
            boolean[] cdp = dp[i];
            int xcnt = n - i;
            for (int j = 0; j <= xcnt && k - j * x >= 0; ++j) {
                if (cdp[k - j * x]) {
                    res[x - 1] = true;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SubseqSumAfterCapping().subsequenceSumAfterCapping(ArrayUtils.read1d("4,2,3,4"), 5)));

    }
}

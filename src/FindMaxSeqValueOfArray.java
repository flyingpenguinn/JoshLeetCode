public class FindMaxSeqValueOfArray {
    public int maxValue(int[] a, int k) {
        int n = a.length;
        int[][][] dp = new int[n + 1][k + 1][128];
        int[][][] dp2 = new int[n + 1][k + 1][128];
        dp[0][0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int l = 0; l <= k; l++) {
                for (int s = 0; s < 128; s++) {
                    if (dp[i][l][s] == 1) {
                        dp[i + 1][l][s] = 1;
                        if (l + 1 <= k) {
                            dp[i + 1][l + 1][s | a[i]] = 1;
                        }
                    }
                }
            }
        }
        dp2[n][0][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int l = 0; l <= k; l++) {
                for (int s = 0; s < 128; s++) {
                    if (dp2[i + 1][l][s] == 1) {
                        dp2[i][l][s] = 1;
                        if (l + 1 <= k) {
                            dp2[i][l + 1][s | a[i]] = 1;
                        }
                    }
                }
            }
        }
        int maxVal = 0;
        for (int mid = 0; mid <= n; mid++) {
            for (int s1 = 0; s1 < 128; s1++) {
                if (dp[mid][k][s1] == 1) {
                    for (int s2 = 0; s2 < 128; s2++) {
                        if (dp2[mid][k][s2] == 1) {
                            maxVal = Math.max(maxVal, s1 ^ s2);
                        }
                    }
                }
            }
        }
        return maxVal;
    }
}

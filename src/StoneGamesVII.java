public class StoneGamesVII {
    // note here score is from purely a's perspective
    public int stoneGameVII(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        int[][][] dp = new int[n + 1][n + 1][2];
        // from i..j, k's turn, their best score
        // score is always >=0
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        for (int len = 0; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (i > j) {
                    continue;
                }
                for (int k = 0; k <= 1; k++) {
                    int csum = sum[j] - (i == 0 ? 0 : sum[i - 1]);
                    int v1 = i + 1 > j ? 0 : dp[i + 1][j][1 - k];
                    int v2 = j - 1 < i ? 0 : dp[i][j - 1][1 - k];
                    if (k == 0) {
                        int way1 = csum - a[i] + v1;
                        int way2 = csum - a[j] + v2;
                        dp[i][j][k] = Math.max(way1, way2);
                    } else {
                        int way1 = v1 - (csum - a[i]);
                        int way2 = v2 - (csum - a[j]);
                        dp[i][j][k] = Math.min(way1, way2);
                    }
                }
            }
        }
        return dp[0][n - 1][0];
    }
}

class StoneGameViiSimpler {
    // at each round i...j we get max score. note for b, he wants max score too, just the negative value...
    // note here score meaning is from each mover's standing point, not from a or b
    public int stoneGameVII(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        int[][] dp = new int[n + 1][n + 1];
        // from i..j, k's turn, their best score
        // score is always >=0
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int csum = sum[j] - (i == 0 ? 0 : sum[i - 1]);
                int v1 = i + 1 > j ? 0 : dp[i + 1][j];
                int v2 = j - 1 < i ? 0 : dp[i][j - 1];
                int way1 = csum - a[i] - v1;
                int way2 = csum - a[j] - v2;
                dp[i][j] = Math.max(way1, way2);
            }
        }
        return dp[0][n - 1];
    }
}


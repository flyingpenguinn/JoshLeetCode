import java.util.Arrays;

// longest common substring problem
public class MaxLenofRepeatedSubarray {
    public int findLength(int[] a, int[] b) {
        int na = a.length;
        int nb = b.length;
        int[][] dp = new int[na][nb];
        int max = 0;
        for (int i = 0; i < na; ++i) {
            for (int j = 0; j < nb; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = a[i] == b[j] ? 1 : 0;
                } else if (a[i] == b[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }


        }
        return max;
    }
}

class MaxlenOfRepeatedSubarrayMemoization {
    int[][] dp;
    int max = 0;

    public int findLength(int[] a, int[] b) {
        dp = new int[a.length][b.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {


                max = Math.max(max, dolen(a, b, i, j));

            }
        }
        return max;
    }

    private int dolen(int[] a, int[] b, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int cur = a[i] == b[j] ? dolen(a, b, i - 1, j - 1) + 1 : 0;
        dp[i][j] = cur;
        return cur;

    }
}

import java.util.Arrays;

public class ConstructTwoIncreasingArrays {
    private int Max = (int) 1e9 + 7;

    public int minLargest(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int[][] dp = new int[an + 1][bn + 1];
        for (int i = 0; i < dp.length; ++i) {
            Arrays.fill(dp[i], Max);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= an; ++i) {
            for (int j = 0; j <= bn; ++j) {
                if (i > 0) {
                    boolean sameparity = a[i - 1] % 2 == dp[i - 1][j] % 2;
                    dp[i][j] = dp[i - 1][j] + (sameparity ? 2 : 1);
                }
                if (j > 0) {
                    boolean sameparity = b[j - 1] % 2 == dp[i][j - 1] % 2;
                    int way2 = dp[i][j - 1] + (sameparity ? 2 : 1);
                    dp[i][j] = Math.min(dp[i][j], way2);
                }
            }
        }
        return dp[an][bn];
    }
}

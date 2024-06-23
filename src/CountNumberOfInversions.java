import java.util.Arrays;

public class CountNumberOfInversions {

    private static final int MOD = 1000000007;

    public int numberOfPermutations(int n, int[][] requirements) {
        int[][] dp = new int[n + 1][400 + 1];

        dp[0][0] = 1;

        int[] req = new int[n];
        Arrays.fill(req, -1);
        for (int i = 0; i < requirements.length; ++i) {
            int index = requirements[i][0];
            int v = requirements[i][1];
            req[index] = v;
        }

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k <= 400; k++) {
                if (req[i - 1] != -1 && req[i - 1] != k) {
                    continue;
                }
                dp[i][k] = 0;

                for (int j = 0; j < i; j++) {
                    if (k - j >= 0) {
                        dp[i][k] = (dp[i][k] + dp[i - 1][k - j]);
                        dp[i][k] %= MOD;
                    }
                }

            }
        }
        return dp[n][req[n - 1]];

    }
}

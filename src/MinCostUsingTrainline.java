import java.util.Arrays;

public class MinCostUsingTrainline {

    private long MAX = (long) 1e16;
    public long[] minimumCosts(int[] rc, int[] ec, int sc) {
        int n = rc.length;
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = sc;
        long[] res = new long[n];
        Arrays.fill(res, MAX);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= 1; ++j) {
                if (j == 0) {
                    long way1 = dp[i - 1][0] + rc[i - 1];
                    long way2 = dp[i - 1][1] + rc[i - 1]; // back cost 0
                    dp[i][j] = Math.min(way1, way2);
                } else {
                    long way1 = dp[i - 1][0] + sc + ec[i - 1];
                    long way2 = dp[i - 1][1] + ec[i - 1];
                    dp[i][j] = Math.min(way1, way2);
                }
                res[i - 1] = Math.min(res[i - 1], dp[i][j]);
                //  System.out.println(i+","+j+" "+dp[i][j]);
            }
        }
        return res;
    }
}

import java.util.Arrays;

public class ElevatorRequestsII {
    // todo. interval dp
    private long Max = (long) 4e18;

    public long elevatorRequests(int en, int start, int[] requests) {
        Arrays.sort(requests);

        int n = requests.length;
        long[][][] dp = new long[n][n][2];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], Max);
            }
        }

        for (int i = 0; i < n; ++i) {
            long d = Math.abs((long) start - requests[i]);
            dp[i][i][0] = d * n;
            dp[i][i][1] = d * n;
        }

        for (int len = 1; len <= n; ++len) {
            int rem = n - len;

            for (int l = 0; l + len - 1 < n; ++l) {
                int r = l + len - 1;

                if (dp[l][r][0] < Max) {
                    if (l > 0) {
                        long d = requests[l] - requests[l - 1];
                        dp[l - 1][r][0] = Math.min(
                                dp[l - 1][r][0],
                                dp[l][r][0] + d * rem
                        );
                    }

                    if (r + 1 < n) {
                        long d = requests[r + 1] - requests[l];
                        dp[l][r + 1][1] = Math.min(
                                dp[l][r + 1][1],
                                dp[l][r][0] + d * rem
                        );
                    }
                }

                if (dp[l][r][1] < Max) {
                    if (l > 0) {
                        long d = requests[r] - requests[l - 1];
                        dp[l - 1][r][0] = Math.min(
                                dp[l - 1][r][0],
                                dp[l][r][1] + d * rem
                        );
                    }

                    if (r + 1 < n) {
                        long d = requests[r + 1] - requests[r];
                        dp[l][r + 1][1] = Math.min(
                                dp[l][r + 1][1],
                                dp[l][r][1] + d * rem
                        );
                    }
                }
            }
        }

        return Math.min(dp[0][n - 1][0], dp[0][n - 1][1]);
    }
}

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class SumOfSubarraysLengthAtleastM {
    private int Min = (int) -1e9;
    int[] psum;

    public int maxSum(int[] a, int k, int m) {
        int n = a.length;
        int[][] dp = new int[n + 1][k + 1];
        Arrays.fill(dp[n], Min);
        dp[n][0] = 0;
        psum = new int[n];
        psum[0] = a[0];
        for (int i = 1; i < n; ++i) {
            psum[i] = psum[i - 1] + a[i];
        }
        int res = Min;
        for (int p = 1; p <= k; ++p) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = n - 1; i >= 0; --i) {
                if (i + m <= n) {
                    pq.offer(psum[i + m - 1] + dp[i + m][p - 1]);
                }
                dp[i][p] = dp[i + 1][p];
                if (!pq.isEmpty()) {
                    int cmax = pq.peek() - (i - 1 < 0 ? 0 : psum[i - 1]);
                    dp[i][p] = Math.max(dp[i][p], cmax);


                }
                res = Math.max(res, dp[i][p]);
            }
        }
        return dp[0][k];
    }
}

import java.util.Arrays;

public class LargestSubmatrixWithRearrangements {
    // maximal rectangle is without rearrangements. if can rearrange, then max histo can be achieved via sorting!
    // can freely rearrange etc, either via some kind of projection, or sorting!
    public int largestSubmatrix(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] dp = new int[n];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    dp[j] = 0;
                } else {
                    dp[j]++;
                }
            }
            int[] sorted = Arrays.copyOf(dp, n);
            Arrays.sort(sorted);
            //   System.out.println(Arrays.toString(dp[i]));
            for (int j = n - 1; j >= 0; j--) {
                if (sorted[j] == 0) {
                    break;
                }
                int cur = sorted[j] * (n - j);
                res = Math.max(cur, res);
            }
        }
        return res;
    }
}

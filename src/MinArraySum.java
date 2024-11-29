import java.util.Arrays;

public class MinArraySum {
    private int Max = (int) (1e9);

    public int minArraySum(int[] a, int k, int op1, int op2) {
        int n = a.length;
        dp = new int[n][op1 + 1][op2 + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < dp[0].length; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(a, 0, op1, op2, k);
    }

    private int[][][] dp;

    private int solve(int[] a, int i, int op1, int op2, int k) {
        if (op1 < 0) {
            return Max;
        }
        if (op2 < 0) {
            return Max;
        }
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][op1][op2] != -1) {
            return dp[i][op1][op2];
        }
        int way1 = a[i] + solve(a, i + 1, op1, op2, k);
        int way2v = (int) Math.ceil(a[i] * 1.0 / 2);
        int way2 = Max;
        if (op1 > 0) {
            way2 = way2v + solve(a, i + 1, op1 - 1, op2, k);
        }
        int way3 = Max;
        if (a[i] >= k && op2 > 0) {
            way3 = a[i] - k + solve(a, i + 1, op1, op2 - 1, k);
        }
        int way4 = Max;
        if (way2v >= k && op1 > 0 && op2 > 0) {
            way4 = way2v - k + solve(a, i + 1, op1 - 1, op2 - 1, k);
        }
        int way5 = Max;
        int way5v = (int) Math.ceil(((a[i] - k) * 1.0) / 2);
        if (a[i] >= k && op1 > 0 && op2 > 0) {
            way5 = way5v + solve(a, i + 1, op1 - 1, op2 - 1, k);
        }
        int res = Math.min(way1, Math.min(way2, Math.min(way3, Math.min(way4, way5))));
        //  System.out.println(i+" "+op1+" "+op2+" "+res);
        dp[i][op1][op2] = res;
        return res;
    }
}

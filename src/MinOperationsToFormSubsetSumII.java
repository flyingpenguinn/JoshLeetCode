import java.util.Arrays;

public class MinOperationsToFormSubsetSumII {
    private int Max = (int) (1e9);
    private int[][] dp;

    public int minOperations(int[] a, int sum) {
        int n = a.length;
        dp = new int[n][sum + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);

        }

        int rt = solve(a, 0, 0,  sum);
        return rt >= Max ? -1 : rt;
    }

    private int solve(int[] a, int i, int csum, int sum) {
        int n = a.length;
        if (csum > sum) {
            return Max;
        }
        if (i == n) {
            return csum == sum ? 0 : Max;
        }
        if(dp[i][csum]!= -1){
            return dp[i][csum];
        }
        int res = solve(a, i + 1, csum, sum);
        int way0 = solve(a, i + 1, csum + a[i], sum);
        res = Math.min(res, way0);

        int raw = a[i];
        int div = 0;
        while (raw > 0) {
            int multi = 0;
            int addedv = raw;
            int cur = div + solve(a, i + 1, csum + raw, sum);
            res = Math.min(res, cur);
            while (addedv > 0 && csum + addedv * 2 <= sum) {
                ++multi;
                addedv *= 2;
                cur = div + multi + solve(a, i + 1, csum + addedv, sum);
                res = Math.min(res, cur);
            }
            ++div;
            raw /= 2;
        }
        dp[i][csum] = res;
        return res;
    }
}

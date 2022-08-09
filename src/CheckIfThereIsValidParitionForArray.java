import java.util.Arrays;

public class CheckIfThereIsValidParitionForArray {
    private int[] dp;

    public boolean validPartition(int[] a) {
        dp = new int[a.length];
        Arrays.fill(dp, -1);
        //Arrays.sort(a);
        return solve(a, 0) == 1;
    }

    private int solve(int[] a, int i) {
        int n = a.length;
        if (i == n) {
            return 1;
        }

        if (dp[i] != -1) {
            return dp[i];
        }
        int res = 0;
        if (i + 1 < n && a[i + 1] == a[i]) {
            res = solve(a, i + 2);
        }
        if (res == 1) {
           return dp[i] = res;
        }
        if (i + 1 < n && i + 2 < n && a[i] == a[i + 1] && a[i] == a[i + 2]) {
            res = solve(a, i + 3);
        }
        if (res == 1) {
            return dp[i] = res;
        }
        if (i + 1 < n && i + 2 < n && a[i] + 1 == a[i + 1] && a[i + 1] + 1 == a[i + 2]) {
            res = solve(a, i + 3);
        }
        if (res == 1) {
            return dp[i] = res;
        }
        dp[i] = res;
        return res;
    }
}

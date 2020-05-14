import java.util.Arrays;

public class PartitionEqualSubsetSum {
    int[][] dp;

    public boolean canPartition(int[] a) {
        Arrays.sort(a);
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        dp = new int[n][sum / 2 + 1];
        return doc(a, 0, sum / 2);
    }

    boolean doc(int[] a, int i, int t) {
        if (t < 0) {
            return false;
        }
        if (t == 0) {
            return true;
        }
        if (i == a.length) {
            return false;
        }
        if (a[i] > t) {
            return false;
        }
        if (dp[i][t] != 0) {
            return dp[i][t] == 1;
        }
        boolean rt = doc(a, i + 1, t) || doc(a, i + 1, t - a[i]);
        dp[i][t] = rt ? 1 : 2;
        return rt;
    }
}

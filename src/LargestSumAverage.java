public class LargestSumAverage {
    // similar to split array largets sum
    double[][] dp;
    int[] ri;

    public double largestSumOfAverages(int[] a, int k) {
        int n = a.length;
        dp = new double[n][k + 1];
        ri = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ri[i] = (i == n - 1 ? 0 : ri[i + 1]) + a[i];
        }
        return dodp(a, 0, k);
    }

    // start from i, k parts,retutn max avg
    double dodp(int[] a, int i, int k) {
        int n = a.length;
        if (k == 1) {
            return ri[i] * 1.0 / (n - i);
        }
        if (dp[i][k] != 0.0) {
            return dp[i][k];
        }
        int sum = 0;
        double max = 0.0;
        for (int j = i; j <= n - k; j++) {
            sum += a[j];
            double later = dodp(a, j + 1, k - 1);
            double cur = sum * 1.0 / (j - i + 1) + later;
            max = Math.max(cur, max);
        }
        dp[i][k] = max;
        return max;
    }
}

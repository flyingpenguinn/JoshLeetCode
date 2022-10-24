import java.util.Arrays;

public class MinCostToMakeArrayEqual {

    public long minCost(int[] a, int[] cost) {

        int n = a.length;
        int[][] input = new int[n][2];
        for (int i = 0; i < n; ++i) {
            input[i][0] = a[i];
            input[i][1] = cost[i];
        }
        Arrays.sort(input, (x, y) -> Integer.compare(x[0], y[0]));
        for (int i = 0; i < n; ++i) {
            a[i] = input[i][0];
            cost[i] = input[i][1];
        }
        long[] costrsum = new long[n + 1];
        costrsum[n - 1] = cost[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            costrsum[i] = 1L * costrsum[i + 1] + cost[i];
        }
        long[] rsum = new long[n + 1];
        rsum[n - 1] = 1L * a[n - 1] * cost[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rsum[i] = rsum[i + 1] + 1L * a[i] * cost[i];
        }
        long lsum = 0;
        long lcsum = 0;
        long res = (long) 1e18;
        for (int i = 0; i < n; ++i) {
            long p1 = a[i] * lcsum - lsum;
            long p2 = rsum[i + 1] - 1L * a[i] * costrsum[i + 1];
            long cur = p1 + p2;
            res = Math.min(res, cur);
            lsum += 1L * a[i] * cost[i];
            lcsum += cost[i];
        }
        return res;
    }
}

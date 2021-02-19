public class DeliverBoxesFromStorageToPorts {

    public int boxDelivering(int[][] a, int pn, int maxBoxes, int maxWeight) {
        int n = a.length;
        // dp[i] is the cost from 0...i
        int[] dp = new int[n];
        int j = 0;
        int w = 0;
        // extra ports to travel at each i. note for 0, no extra port to trave so =0
        int[] stops = new int[n];
        stops[0] = 0;
        for (int i = 1; i < n; i++) {
            if (a[i][0] != a[i - 1][0]) {
                stops[i] = stops[i - 1] + 1;
            } else {
                stops[i] = stops[i - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            // j...i, so i-j+1 is the box count. note we should add w here first
            w += a[i][1];
            int boxes = i - j + 1;
            if (boxes > maxBoxes) {
                w -= a[j++][1];
            }
            while (w > maxWeight) {
                w -= a[j++][1];
            }
            while (j < i && dp[j] == (j == 0 ? 0 : dp[j - 1])) {
                w -= a[j++][1];
            }
            // j... i so j<=i. we are guaranteed to start a new trip at j comparing to j-1
            int cstops = stops[i] - stops[j];
            dp[i] = (j == 0 ? 0 : dp[j - 1]) + cstops + 2;
        }
        return dp[n - 1];
    }
}

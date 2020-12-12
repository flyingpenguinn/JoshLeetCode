public class DeliverBoxesFromStorageToPorts {

    // dp i is the cost from 0 to i-1  dp i = dp start+ diffs between them +2 (come and go), dp[0]=0
    public int boxDelivering(int[][] a, int portsCount, int mb, int mw) {
        int n = a.length;
        int[] dp = new int[n + 1];
        int[] diff = new int[n];
        for (int i = 0; i + 1 < n; i++) {
            diff[i] = (a[i][0] != a[i + 1][0]) ? 1 : 0;
        }
        int start = 0;
        int cw = 0;
        int cdiff = 0;
        for (int i = 1; i <= n; i++) {
            // we are planning to do from start...i-1
            if (i - start > mb) {
                cw -= a[start][1];
                // if start... i-1 is too many boxes, drop start
                cdiff -= diff[start++];
            }
            cw += a[i - 1][1];
            if (i - 2 >= 0) {
                // i-2 is the box diff that we newly introduce: if we introduce box 1 we introduce diff[0] but i will be 2
                cdiff += diff[i - 2];
            }
            while (cw > mw) {
                cw -= a[start][1];
                cdiff -= diff[start++];
            }
            // we are calcing dp[i]
            while (start + 1 < i && dp[start] == dp[start + 1]) {
                cw -= a[start][1];
                cdiff -= diff[start++];
            }
            dp[i] = dp[start] + 2 + cdiff;
            //  System.out.println("i= "+i+" start= "+start+" cdiff= "+cdiff+" dp[i]= "+dp[i]);
        }
        return dp[n];
    }
}

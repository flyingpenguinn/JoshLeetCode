public class MinMoneyRequiredBeforeTransactions {
    // f*king greedy...
    public long minimumMoney(int[][] ts) {
        int n = ts.length;
        long maxcash = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            if (ts[i][0] > ts[i][1]) {
                res += ts[i][0] - ts[i][1];
                // we cannot really get thsi money, so add it to needed
                maxcash = Math.max(maxcash, ts[i][1]);
            } else {
                // only need to overcome this one for the earnings
                maxcash = Math.max(maxcash, ts[i][0]);
            }
        }
        return res + maxcash;
    }
}

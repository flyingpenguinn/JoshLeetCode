public class BestTimeBuySellStockStrategy {
    public long maxProfit(int[] a, int[] st, int k) {
        int n = a.length;
        long cres = 0;
        long[] pres = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            cres += st[i] * a[i];
            pres[i] = cres;
        }
        long[] psum = new long[n];
        for (int i = 0; i < n; ++i) {
            psum[i] = (i == 0 ? 0 : psum[i - 1]) + a[i];
        }
        long res = cres;
        long normal = 0;
        for (int i = 0; i + k - 1 < n; ++i) {
            int startsell = i + k / 2;
            int endsell = i + k - 1;
            long sellprofit = psum[endsell] - (startsell == 0 ? 0 : psum[startsell - 1]);
            long laterprofit = pres[endsell + 1];
            long cur = normal + sellprofit + laterprofit;
            normal += st[i] * a[i];
            res = Math.max(res, cur);
        }
        return res;
    }
}

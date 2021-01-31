public class CanYouEatFavCandyOnFavDay {
    public boolean[] canEat(int[] a, int[][] qs) {
        int n = a.length;
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        boolean[] res = new boolean[qs.length];
        for (int i = 0; i < qs.length; i++) {
            int[] q = qs[i];
            int type = q[0];
            int day = q[1];
            int cap = q[2];
            long pretype = (type == 0 ? 0 : sum[type - 1]);
            if (day == 0) {
                res[i] = cap >= pretype;
            } else {
                int predays = day;// note days start from 0!
                double mincap = (pretype + 1.0 - cap) / predays; // must eat at least one candy on day
                double maxcap = (sum[type] - 1.0) / predays;
                maxcap = Math.min(maxcap, cap);
                res[i] = maxcap >= 1.0 && maxcap >= mincap;
            }
        }
        return res;
    }
}

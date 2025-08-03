public class MaxBalancedShipments {
    // greedy
    public int maxBalancedShipments(int[] a) {
        int n = a.length;
        int cmax = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            cmax = Math.max(cmax, a[i]);
            if (a[i] < cmax) {
                ++res;
                cmax = 0;
            }
        }
        return res;
    }
}

public class MincostProcessAllElements {
    private long Mod = (long) (1e9+7);
    public int minimumCost(int[] a, int k) {
        int n = a.length;
        long res = 0;
        long cr = k;
        long costbase = 1;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            if (cr >= v) {
                cr -= v;
                continue;
            }
            long times = (long) Math.ceil(1.0 * (v - cr) / k);
            times %= Mod;
            long ccost = (costbase + (costbase + times - 1)) * times / 2;
            ccost %= Mod;
            cr += times * k;
            cr -= v;
            costbase += times;
            costbase %= Mod;
            res += ccost;
            res %= Mod;
        }
        return (int) res;
    }
}

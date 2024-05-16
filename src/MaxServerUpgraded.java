public class MaxServerUpgraded {
    public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
        int n = count.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int c = count[i];
            int s = sell[i];
            int m = money[i];
            int u = upgrade[i];
            long v1 = 1L * u * c - m;
            long v2 = s + u;
            //System.out.println(v1+" "+v2);
            int x = Math.max(0, (int) Math.ceil(v1 * 1.0 / v2));
            res[i] = c - x;
        }
        return res;
    }
}

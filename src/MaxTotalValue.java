public class MaxTotalValue {
    public int maxTotalValue(int[] value, int[] decay, int m) {
        int l = 0;
        int u = (int) 1e9;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (count(value, decay, mid) >= m) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        u = Math.max(u, 0);
        long res = 0;
        int n = value.length;
        long Mod = (long) (1e9 + 7);
        int remtimes = m;
        for (int i = 0; i < n && remtimes > 0; ++i) {
            if (value[i] < u) {
                continue;
            }
            int times = (value[i] - u) / decay[i] + 1;
            int last = value[i] - (times - 1) * decay[i];
            long csum = ((long) value[i] + last) * times / 2;
            if ((value[i] - u) % decay[i] == 0) {
                csum -= u;
                times -= 1;
            }
            res += csum;
            res %= Mod;
            remtimes -= times;
        }
        if (remtimes > 0) {
            res += (long) remtimes * u;
            res %= Mod;
        }
        return (int) res;
    }

    private long count(int[] value, int[] decay, int t) {
        long res = 0;
        int n = value.length;
        for (int i = 0; i < n; ++i) {
            if (value[i] >= t) {
                long times = (value[i] - t) / decay[i] + 1;
                res += times;
            }
        }
        return res;
    }
}

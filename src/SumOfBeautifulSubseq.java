import java.util.Arrays;

public class SumOfBeautifulSubseq {
    // TODO

    static final int MAXA = 70000;
    static final int MOD = 1000000007;
    static boolean inited = false;
    static int[][] fac;

    static void init() {
        if (inited) {
            return;
        }
        inited = true;
        fac = new int[MAXA + 1][];
        int[] cnt = new int[MAXA + 1];
        for (int i = 1; i <= MAXA; i++) {
            for (int j = i; j <= MAXA; j += i) {
                cnt[j]++;
            }
        }
        for (int i = 1; i <= MAXA; i++) {
            fac[i] = new int[cnt[i]];
        }
        Arrays.fill(cnt, 0);
        for (int i = 1; i <= MAXA; i++) {
            for (int j = i; j <= MAXA; j += i) {
                fac[j][cnt[j]++] = i;
            }
        }
    }

    public int totalBeauty(int[] a) {
        init();
        int n = a.length;
        int mx = 0;
        for (int x : a) {
            if (x > mx) {
                mx = x;
            }
        }

        int[] pc = new int[mx + 1];
        for (int i = 0; i < n; i++) {
            int[] ds = fac[a[i]];
            for (int k = 0; k < ds.length; k++) {
                int d = ds[k];
                if (d <= mx) {
                    pc[d]++;
                }
            }
        }

        int[][] pos = new int[mx + 1][];
        int[] pt = new int[mx + 1];
        for (int d = 1; d <= mx; d++) {
            if (pc[d] > 0) {
                pos[d] = new int[pc[d]];
            }
        }
        for (int i = 0; i < n; i++) {
            int[] ds = fac[a[i]];
            for (int k = 0; k < ds.length; k++) {
                int d = ds[k];
                if (d <= mx) {
                    pos[d][pt[d]++] = i;
                }
            }
        }

        long[] f = new long[mx + 1];
        for (int d = 1; d <= mx; d++) {
            f[d] = calc(d, a, pos[d]);
        }

        long ans = 0;
        for (int i = mx; i >= 1; i--) {
            for (int j = i + i; j <= mx; j += i) {
                f[i] -= f[j];
                if (f[i] < 0) {
                    f[i] += MOD;
                }
            }
            ans += (long) i * (f[i] % MOD);
            if (ans >= MOD) {
                ans %= MOD;
            }
        }
        return (int) (ans % MOD);
    }

    static long calc(int k, int[] a, int[] idxs) {
        if (idxs == null || idxs.length == 0) {
            return 0;
        }
        int len = idxs.length;
        int[] vals = new int[len];
        for (int i = 0; i < len; i++) {
            vals[i] = a[idxs[i]];
        }
        int[] tmp = vals.clone();
        Arrays.sort(tmp);
        int m = 0;
        int prev = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (i == 0 || tmp[i] != prev) {
                tmp[m++] = tmp[i];
                prev = tmp[i];
            }
        }
        long[] bit = new long[m + 1];
        long ret = 0;
        for (int i = 0; i < len; i++) {
            int v = vals[i];
            int id = Arrays.binarySearch(tmp, 0, m, v) + 1;
            long t = (query(bit, id - 1) + 1) % MOD;
            add(bit, id, t);
            ret += t;
            if (ret >= MOD) {
                ret -= MOD;
            }
        }
        return ret % MOD;
    }

    static void add(long[] bit, int i, long v) {
        int n = bit.length - 1;
        while (i <= n) {
            bit[i] += v;
            if (bit[i] >= MOD) {
                bit[i] -= MOD;
            }
            i += i & -i;
        }
    }

    static long query(long[] bit, int i) {
        long s = 0;
        while (i > 0) {
            s += bit[i];
            if (s >= MOD) {
                s -= MOD;
            }
            i -= i & -i;
        }
        return s % MOD;
    }


}

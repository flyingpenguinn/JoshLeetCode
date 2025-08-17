import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XorAfterRangeMultipleQueriesII {

    static final int MOD = 1000000007;

    static long mp(long a, long e) {
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = r * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return r;
    }

    static long inv(long a) {
        return mp(a, MOD - 2);
    }

    public int xorAfterQueries(int[] a, int[][] qs) {
        int n = a.length;
        long[] f = new long[n];
        Arrays.fill(f, 1L);
        int B = (int) Math.sqrt(n);
        ArrayList<int[]> big = new ArrayList<>();
        HashMap<Integer, ArrayList<int[]>> byk = new HashMap<>();
        for (int i = 0; i < qs.length; ++i) {
            int k = qs[i][2];
            if (k <= B) byk.computeIfAbsent(k, x -> new ArrayList<>()).add(qs[i]);
            else big.add(qs[i]);
        }
        HashMap<Integer, Long> invs = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<int[]>> e : byk.entrySet()) {
            int k = e.getKey();
            ArrayList<int[]> L = e.getValue();
            ArrayList<int[]>[] bys = new ArrayList[k];
            for (int s = 0; s < k; ++s) bys[s] = new ArrayList<>();
            for (int[] t : L) bys[t[0] % k].add(t);
            for (int s = 0; s < k; ++s) {
                int len = (n - 1 - s) / k + 1;
                long[] d = new long[len + 1];
                Arrays.fill(d, 1L);
                for (int[] t : bys[s]) {
                    int l = t[0], r = t[1], v = t[3];
                    int t0 = (l - s) / k, t1 = (r - s) / k;
                    d[t0] = d[t0] * v % MOD;
                    long iv = invs.getOrDefault(v, 0L);
                    if (iv == 0) {
                        iv = inv(v);
                        invs.put(v, iv);
                    }
                    d[t1 + 1] = d[t1 + 1] * iv % MOD;
                }
                long cur = 1L;
                for (int t = 0; t < len; ++t) {
                    cur = cur * d[t] % MOD;
                    int idx = s + t * k;
                    f[idx] = f[idx] * cur % MOD;
                }
            }
        }
        for (int[] t : big) {
            int l = t[0], r = t[1], k = t[2], v = t[3];
            for (int i = l; i <= r; i += k) f[i] = f[i] * v % MOD;
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            long v = ((a[i] % MOD) + MOD) % MOD;
            v = v * f[i] % MOD;
            res ^= (int) v;
        }
        return res;
    }
}

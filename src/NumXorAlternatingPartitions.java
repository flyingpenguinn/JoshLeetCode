public class NumXorAlternatingPartitions {
    public int alternatingXOR(int[] a, int t1, int t2) {
        int n = a.length;
        int m = 1 << 17;
        // ending in t1, prefix is the index
        long[] cnt1 = new long[m];
        long[] cnt2 = new long[m];
        cnt2[0] = 1;
        long mod = (long) (1e9 + 7);
        int psum = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            psum ^= v;
            int pt1 = psum ^ t1;
            int pt2 = psum ^ t2;
            // must save old values!
            long va = cnt2[pt1];
            long vb = cnt1[pt2];
            cnt1[psum] += va;
            cnt1[psum] %= mod;
            cnt2[psum] += vb;
            cnt2[psum] %= mod;
            if (i == n - 1) {
                res += va;
                res += vb;
                res %= mod;
            }
        }
        return (int) res;
    }
}

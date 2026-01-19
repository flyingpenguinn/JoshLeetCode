public class NumXorAlternatingPartitions {
    public int alternatingXOR(int[] a, int t1, int t2) {
        long mod = 1000000007L;

        int mx = t1;
        if (t2 > mx) {
            mx = t2;
        }
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > mx) {
                mx = a[i];
            }
        }
        int sz = 1;
        while (sz <= mx) {
            sz <<= 1;
        }

        long[] e = new long[sz];
        long[] o = new long[sz];

        e[0] = 1L;

        int px = 0;
        long addO = 0L;
        long addE = 0L;

        for (int i = 0; i < a.length; ++i) {
            px ^= a[i];

            addO = e[px ^ t1];
            addE = o[px ^ t2];

            o[px] += addO;
            o[px] %= mod;

            e[px] += addE;
            e[px] %= mod;
        }

        long ans = addO + addE;
        ans %= mod;
        return (int) ans;
    }
}

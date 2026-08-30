public class SumOfDecodedNumbers {
    private long mod = (long) 1e9 + 7;

    public int sumDecoded(long[] a) {
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            long wi = v % 10;
            long di = v / 10;
            String ds = String.valueOf(di);
            String xs = ds.substring(0, (int) wi);
            long xi = Long.valueOf(xs);
            String ys = ds.substring((int) wi);
            long yi = Long.valueOf(ys);
            long cur = pow(xi, yi);
            res += cur;
            res %= mod;
        }
        return (int) res;
    }

    private long pow(long x, long y) {
        if (y == 0) {
            return 1L;
        }
        long half = pow(x, y / 2);
        long hres = half * half;
        hres %= mod;
        if (y % 2 == 1) {
            hres *= x;
            hres %= mod;
        }
        return hres;
    }
}

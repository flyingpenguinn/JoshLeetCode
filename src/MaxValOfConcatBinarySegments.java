import java.util.Arrays;

public class MaxValOfConcatBinarySegments {
    private long Mod = (long) (1e9 + 7);

    public int maxValue(int[] a, int[] b) {
        int n = a.length;
        int[][] seg = new int[n][2];
        long total = 0;
        for (int i = 0; i < n; ++i) {
            seg[i][1] = a[i];
            seg[i][0] = b[i];
            total += seg[i][1];
            total += seg[i][0];
        }
        Arrays.sort(seg, (x, y) -> {
            if (x[0] == 0 || y[0] == 0) {
                if (x[0] == 0 && y[0] != 0) {
                    return -1;
                } else if (x[0] != 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (x[1] != y[1]) {
                return Integer.compare(y[1], x[1]);
            } else {
                return Integer.compare(x[0], y[0]);
            }
        });
        long remtotal = total;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long ones = seg[i][1];
            long zeros = seg[i][0];
            long rem = remtotal - ones;
            long p1 = pow2(ones) - 1;
            if (p1 < 0) {
                p1 += Mod;
            }
            long p2 = pow2(rem);
            long cres = p1 * p2;
            cres %= Mod;
            res += cres;
            res %= Mod;
            remtotal -= ones;
            remtotal -= zeros;
        }
        return (int) res;
    }

    private long pow2(long n) {
        if (n == 0) {
            return 1L;
        }
        long half = pow2(n / 2);
        long res = half * half;
        res %= Mod;
        if (n % 2 == 1L) {
            res *= 2;
        }
        res %= Mod;
        return res;
    }
}

import java.util.Arrays;

public class MinCostToEqualizeArray {
    // extension of question 1953
    // solve this one last min in contest !

    private long Mod = (long) (1e9 + 7);

    public int minCostToEqualizeArray(int[] a, int x, int y) {
        int n = a.length;
        Arrays.sort(a);
        int t = a[n - 1];
        if (n == 1) {
            return 0;
        }

        long res = 0;
        if (x <= y / 2) {
            for (int i = 0; i < n - 1; ++i) {
                res += t - a[i];
            }
            res *= x;

        } else {
            long diffs = 0;
            for (int i = 0; i < n - 1; ++i) {
                diffs += t - a[i];
            }
            long v0 = t - a[0];
            long other = diffs - (t - a[0]);
            if (n == 2) {
                res = v0 * x;
            } else {
                res = calc(x, y, diffs, v0, other);
                int tries = 2;
                while (tries > 0) {
                    other += n - 1;
                    ++v0;
                    diffs += n;
                    long nres = calc(x, y, diffs, v0, other);
                    res = Math.min(res, nres);
                    --tries;
                }
            }
        }
        res %= Mod;
        return (int) res;
    }

    protected long calc(int x, int y, long diffs, long v0, long other) {
        long res = 0;
        if (v0 > other) {
            // if v0 > other we can pair each v0 with an other and get some remnants
            res = (v0 - other) * x + (2 * other) / 2 * y;
        } else {
            // otherwise every number can find a pair until the last one if there are odd numbers
            res = (diffs / 2 * y + (diffs % 2) * x);
        }
        return res;
    }
}

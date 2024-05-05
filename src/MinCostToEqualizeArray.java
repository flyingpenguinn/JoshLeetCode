import java.util.Arrays;

public class MinCostToEqualizeArray {
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
                long v02 = Math.min(other, v0);
                long otherall = v02 + other;

                res = v0 >= other ? ((v0 - v02) * x + (otherall) / 2 * y) : (diffs / 2 * y + (diffs % 2) * x);
                while (true) {
                    other += n - 1;
                    ++v0;
                    diffs += n;
                    //   System.out.println(other+" "+v0);
                    v02 = Math.min(other, v0);
                    otherall = v02 + other;
                    long nres = v0 >= other ? ((v0 - v02) * x + (otherall) / 2 * y) : (diffs / 2 * y + (diffs % 2) * x);
                    //    System.out.println(nres);
                    res = Math.min(res, nres);
                    if (nres > res) {
                        break;
                    }
                }
            }
        }
        res %= Mod;
        return (int) res;
    }
}

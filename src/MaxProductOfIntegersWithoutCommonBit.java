import java.util.Arrays;

public class MaxProductOfIntegersWithoutCommonBit {
    // subset dp!
    public long maxProduct(int[] a) {
        int n = a.length;
        int maxbits = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 21; j > 0; --j) {
                if (((a[i] >> j) & 1) == 1) {
                    maxbits = Math.max(maxbits, j);
                }
            }
        }
        ++maxbits;
        int maxv = (1 << maxbits);
        int full = maxv - 1;
        int[] best = new int[maxv];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            best[v] = v;
        }
        int[] bestsub = Arrays.copyOf(best, best.length);
        for (int st = 0; st < maxv; ++st) {
            for (int i = maxbits; i >= 0; --i) {
                if (((st >> i) & 1) == 1) {
                    int unset = st ^ (1 << i);
                    bestsub[st] = Math.max(bestsub[st], bestsub[unset]);
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            long other = full ^ v;
            long u = bestsub[(int) other];
            long cur = v * u;
            res = Math.max(res, cur);
        }
        return res;
    }
}

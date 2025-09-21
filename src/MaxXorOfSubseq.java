import java.util.Arrays;

public class MaxXorOfSubseq {
    // gaussian elimination
    public int maxXorSubsequences(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int max = a[n - 1];
        int i = 0;
        for (int j = 31; j >= 0; --j) {
            int k = i;
            while (k < n && ((a[k] >> j) & 1) == 0) {
                ++k;
            }
            if (k == n) {
                continue;
            }
            arrayswap(a, k, i);
            for (int p = 0; p < n; ++p) {
                if (p == i) {
                    continue;
                }
                if (((a[p] >> j) & 1) == 1) {
                    a[p] ^= a[i];
                }
            }
            ++i;
        }
        int xor = 0;
        for (int ai : a) {
            xor ^= ai;
        }
        return xor;
    }

    private void arrayswap(int[] a, int k, int i) {
        int tmp = a[k];
        a[k] = a[i];
        a[i] = tmp;
    }
}

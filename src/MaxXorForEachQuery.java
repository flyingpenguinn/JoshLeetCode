public class MaxXorForEachQuery {
    public int[] getMaximumXor(int[] a, int maxbit) {
        int n = a.length;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= a[i];
        }
        int[] res = new int[n];
        int ri = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = find(xor, maxbit);
            xor ^= a[i];
            res[ri++] = cur;
        }
        return res;
    }

    private int find(int num, int maxbit) {
        int res = 0;
        int bits = maxbit - 1;
        while (bits >= 0) {
            if (((num >> bits) & 1) == 0) {
                res |= (1 << bits);
            }
            bits--;
        }
        return res;
    }
}

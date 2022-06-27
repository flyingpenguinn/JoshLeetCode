public class MaxXorAfterOperations {
    public int maximumXOR(int[] a) {
        int n = a.length;
        boolean[] bits = new boolean[32];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 32; ++j) {
                int bit = ((a[i] >> j) & 1);
                if (bit == 1) {
                    bits[j] = true;
                }
            }
        }
        int res = 0;
        for (int j = 0; j < 32; ++j) {
            if (bits[j]) {
                res |= (1 << j);
            }
        }
        return res;
    }
}

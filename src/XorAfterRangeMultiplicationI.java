public class XorAfterRangeMultiplicationI {
    // just f**king brute force
    public int xorAfterQueries(int[] a, int[][] queries) {
        int n = a.length;
        int qn = queries.length;
        long[] na = new long[n];
        for (int i = 0; i < n; ++i) {
            na[i] = a[i];
        }
        long mod = (long) 1e9 + 7;
        for (int[] q : queries) {
            int v1 = q[0];
            int v2 = q[1];
            int k = q[2];
            int mv = q[3];
            for (int i = v1; i <= v2; i += k) {
                na[i] *= mv;
                na[i] %= mod;
            }
        }
        long xor = 0;
        for (int i = 0; i < n; ++i) {
            xor ^= na[i];
        }
        return (int) xor;
    }
}

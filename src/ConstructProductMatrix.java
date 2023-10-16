public class ConstructProductMatrix {
    // same as product except self 1d solution
    // cant mod inverse as 12345 is not prime
    private int Mod = 12345;

    public int[][] constructProductMatrix(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long[] ia = new long[m * n];
        long all = 1;
        int pi = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ia[pi++] = a[i][j];
            }
        }
        long[] res = productExceptSelf(ia);
        int[][] rr = new int[m][n];
        int ri = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rr[i][j] = (int) res[ri++];
            }
        }
        return rr;
    }

    public long[] productExceptSelf(long[] a) {
        // check null etc
        int n = a.length;
        long[] right = new long[n];
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i];
            right[i] %= Mod;
        }

        long left = 1;
        for (int i = 0; i < n; i++) {
            right[i] = left * (i == n - 1 ? 1 : right[i + 1]);
            right[i] %= Mod;
            left *= a[i];
            left %= Mod;
        }
        return right;
    }
}

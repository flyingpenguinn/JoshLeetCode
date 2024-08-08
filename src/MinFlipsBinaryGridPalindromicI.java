public class MinFlipsBinaryGridPalindromicI {
    // row and col separate
    public int minFlips(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int rowflips = 0;
        int colflips = 0;
        for (int i = 0; i < m; ++i) {
            int[] r = a[i];
            rowflips += flips(r);
        }
        for (int j = 0; j < n; ++j) {
            int[] col = new int[m];
            int ci = 0;
            for (int i = 0; i < m; ++i) {
                col[ci++] = a[i][j];
            }
            colflips += flips(col);
        }
        return Math.min(rowflips, colflips);
    }

    private int flips(int[] a) {
        int i = 0;
        int j = a.length - 1;
        int res = 0;
        while (i < j) {
            if (a[i] != a[j]) {
                ++res;
            }
            ++i;
            --j;
        }
        return res;
    }
}

public class FindWidthofColumns {
    public int[] findColumnWidth(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] res = new int[n];
        for (int j = 0; j < n; ++j) {
            int cur = 0;
            for (int i = 0; i < m; ++i) {
                int clen = Integer.toString(a[i][j]).length();
                cur = Math.max(cur, clen);
                res[j] = cur;
            }
        }
        return res;
    }
}

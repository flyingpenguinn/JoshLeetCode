public class RowWithMinOnes {
    public int[] rowAndMaximumOnes(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = -1;
        int ri = -1;
        for (int i = 0; i < m; ++i) {
            int count = 0;
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    ++count;
                }
            }
            if (count > res) {
                res = count;
                ri = i;
            }
        }
        return new int[]{ri, res};
    }
}

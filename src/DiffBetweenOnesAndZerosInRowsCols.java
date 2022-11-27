public class DiffBetweenOnesAndZerosInRowsCols {
    public int[][] onesMinusZeros(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] r1 = new int[m];
        int[] r0 = new int[m];
        int[] c1 = new int[n];
        int[] c0 = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    ++r1[i];
                    ++c1[j];
                } else {
                    ++r0[i];
                    ++c0[j];
                }
            }
        }
        int[][] diff = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                //  System.out.println(r1[i]+" "+r1[j]+" "+r0[i]+" "+r0[j]);
                diff[i][j] = r1[i] + c1[j] - r0[i] - c0[j];
            }
        }
        return diff;
    }
}

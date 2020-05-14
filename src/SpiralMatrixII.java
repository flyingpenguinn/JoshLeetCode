public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] r = new int[n][n];
        int i = 0;
        int j = -1;
        int num = 1;
        while (num <= n * n) {
            while (j + 1 < n && r[i][j + 1] == 0) {
                r[i][++j] = num++;
            }
            while (i + 1 < n && r[i + 1][j] == 0) {
                r[++i][j] = num++;
            }
            while (j - 1 >= 0 && r[i][j - 1] == 0) {
                r[i][--j] = num++;
            }
            while (i - 1 >= 0 && r[i - 1][j] == 0) {
                r[--i][j] = num++;
            }
        }
        return r;
    }

}

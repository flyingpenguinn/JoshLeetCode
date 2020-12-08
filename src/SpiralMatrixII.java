public class SpiralMatrixII {
    // use ==0 or not as indicator
    // can simply the code even further if we code against i+1 and j+1
    public int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        int filled = 0;
        int i = 0;
        int j = 0;
        while (filled < n * n) {
            while (j < n && a[i][j] == 0) {
                a[i][j] = ++filled;
                j++;
            }
            j--;
            i++;
            while (i < n && a[i][j] == 0) {
                a[i][j] = ++filled;
                i++;
            }
            i--;
            j--;
            while (j >= 0 && a[i][j] == 0) {
                a[i][j] = ++filled;
                j--;
            }
            j++;
            i--;
            while (i >= 0 && a[i][j] == 0) {
                a[i][j] = ++filled;
                i--;
            }
            i++;
            j++;
        }
        return a;
    }

}

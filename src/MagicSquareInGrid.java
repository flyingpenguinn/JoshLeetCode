public class MagicSquareInGrid {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int c = 0;
        for (int i = 2; i < rows; i++) {
            for (int j = 2; j < cols; j++) {
                if (ism(grid, i, j)) {
                    c++;
                }
            }
        }
        return c;
    }


    boolean ism(int[][] g, int ei, int ej) {
        int si = ei - 2;
        int sj = ej - 2;

        boolean[] seen = new boolean[10];
        for (int i = si; i <= ei; i++) {
            for (int j = sj; j <= ej; j++) {
                if (g[i][j] > 9 || g[i][j] < 1) {
                    return false;
                }
                if (seen[g[i][j]]) {
                    return false;
                }
                seen[g[i][j]] = true;
            }
        }


        int esum = g[si][sj] + g[si][sj + 1] + g[si][ej];
        for (int i = si + 1; i <= ei; i++) {
            int sum = 0;
            for (int j = sj; j <= ej; j++) {
                sum += g[i][j];
            }
            if (sum != esum) {
                return false;
            }
        }
        for (int j = sj; j <= ej; j++) {
            int sum = 0;
            for (int i = si; i <= ei; i++) {
                sum += g[i][j];
            }
            if (sum != esum) {
                return false;
            }
        }
        int d1 = g[si][sj] + g[si + 1][sj + 1] + g[ei][ej];
        if (d1 != esum) {
            return false;
        }
        int d2 = g[si][ej] + g[si + 1][ej - 1] + g[ei][sj];
        if (d2 != esum) {
            return false;
        }
        return true;
    }
}

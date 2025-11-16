public class IncreaseSubmatriceByOne {
    // 2d diff array diff matrix solution
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n][n];
        for (int[] q : queries) {
            int tr = q[0];
            int tc = q[1];
            int br = q[2];
            int bc = q[3];
            diff[tr][tc] += 1;
            if (bc + 1 < n) {
                diff[tr][bc + 1] -= 1;
            }
            if (br + 1 < n) {
                diff[br + 1][tc] -= 1;
            }
            if (br + 1 < n && bc + 1 < n) {
                diff[br + 1][bc + 1] += 1;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                diff[i][j] += diff[i][j - 1];
            }
        }
        for (int j = 0; j < n; ++j) {
            for (int i = 1; i < n; ++i) {
                diff[i][j] += diff[i - 1][j];
            }
        }
        return diff;
    }
}

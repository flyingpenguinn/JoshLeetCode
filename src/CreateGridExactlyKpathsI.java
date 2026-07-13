import java.util.Arrays;

public class CreateGridExactlyKpathsI {
    public String[] createGrid(int m, int n, int k) {
        char[][] res = new char[m][n];

        for (int i = 0; i < m; ++i) {
            Arrays.fill(res[i], '#');
        }

        if (k == 1) {
            for (int j = 0; j < n; ++j) {
                res[0][j] = '.';
            }

            for (int i = 0; i < m; ++i) {
                res[i][n - 1] = '.';
            }

            return tores(res);
        }

        if (m >= 2 && n >= k) {
            for (int j = 0; j < k; ++j) {
                res[0][j] = '.';
                res[1][j] = '.';
            }

            for (int j = k - 1; j < n; ++j) {
                res[1][j] = '.';
            }

            for (int i = 1; i < m; ++i) {
                res[i][n - 1] = '.';
            }

            return tores(res);
        }

        if (n >= 2 && m >= k) {
            for (int i = 0; i < k; ++i) {
                res[i][0] = '.';
                res[i][1] = '.';
            }

            for (int i = k - 1; i < m; ++i) {
                res[i][1] = '.';
            }

            for (int j = 1; j < n; ++j) {
                res[m - 1][j] = '.';
            }

            return tores(res);
        }

        if (k == 4 && m >= 3 && n >= 3) {
            res[0][0] = '.';
            res[0][1] = '.';

            res[1][0] = '.';
            res[1][1] = '.';
            res[1][2] = '.';

            res[2][1] = '.';
            res[2][2] = '.';

            for (int j = 2; j < n; ++j) {
                res[2][j] = '.';
            }

            for (int i = 2; i < m; ++i) {
                res[i][n - 1] = '.';
            }

            return tores(res);
        }

        return new String[0];
    }

    private String[] tores(char[][] res) {
        int m = res.length;
        String[] rres = new String[m];

        for (int i = 0; i < m; ++i) {
            rres[i] = new String(res[i]);
        }

        return rres;
    }
}

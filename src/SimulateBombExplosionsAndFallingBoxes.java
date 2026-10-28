import java.util.Arrays;

public class SimulateBombExplosionsAndFallingBoxes {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] surr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public char[][] finalGrid(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        while (true) {
            char[][] na = new char[m][n];
            copymatrix(na, a);
            explode(na, a);
            fall(na);
            if (Arrays.deepEquals(na, a)) {
                return a;
            }
            a = na;
        }
    }

    private void fall(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int j = 0; j < n; ++j) {
            for (int i = m - 2; i >= 0; --i) {
                if(a[i][j]!= '#'){
                    continue;
                }
                if (a[i + 1][j] == '-') {
                    a[i][j] = '-';
                    a[i + 1][j] = '#';
                }
            }
        }
    }

    private void explode(char[][] na, char[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == '*') {
                    boolean exp = false;
                    for (int[] d : dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == '#') {
                            exp = true;
                            break;
                        }
                    }
                    if (exp) {
                        for (int[] s : surr) {
                            int ni = i + s[0];
                            int nj = j + s[1];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == '#') {
                                na[ni][nj] = '-';
                            }
                        }
                    }
                }

            }
        }
    }

    private void copymatrix(char[][] na, char[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; ++i) {
            na[i] = Arrays.copyOf(a[i], n);
        }
    }
}

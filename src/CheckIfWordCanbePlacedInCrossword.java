public class CheckIfWordCanbePlacedInCrossword {
    public boolean placeWordInCrossword(char[][] a, String word) {
        int m = a.length;
        int n = a[0].length;
        int wlen = word.length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((j == 0 || a[i][j - 1] == '#') && n - j >= wlen && (j + wlen == n || a[i][j + wlen] == '#') && canplace(a, i, j, 0, 1, word)) {
                    return true;
                }
                if ((j == n - 1 || a[i][j + 1] == '#') && j + 1 >= wlen && (j - wlen == -1 || a[i][j - wlen] == '#') && canplace(a, i, j, 0, -1, word)) {
                    return true;
                }
                if ((i == 0 || a[i - 1][j] == '#') && m - i >= wlen && (i + wlen == m || a[i + wlen][j] == '#') && canplace(a, i, j, 1, 0, word)) {
                    return true;
                }
                if ((i == m - 1 || a[i + 1][j] == '#') && i + 1 >= wlen && (i - wlen == -1 || a[i - wlen][j] == '#') && canplace(a, i, j, -1, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canplace(char[][] a, int i, int j, int di, int dj, String w) {
        int wlen = w.length();
        int wi = 0;
        while (wi < wlen) {
            if (a[i][j] == ' ' || a[i][j] == w.charAt(wi)) {
                i += di;
                j += dj;
            } else {
                return false;
            }
            ++wi;
        }
        return true;
    }
}

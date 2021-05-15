public class RotatingTheBox {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = box[m - 1 - j][i];
            }
        }
        for (int j = 0; j < m; j++) {
            int k = n - 1;
            int l = n - 1;
            while (l >= 0) {
                if (res[l][j] == '#') {
                    res[l][j] = '.';
                    res[k--][j] = '#';
                }
                else if (res[l][j] == '*') {
                    res[l][j] = '*';
                    k = l - 1;
                }
                l--;
            }
            while (k >= 0) {
                res[k--][j] = '.';
            }
        }
        return res;
    }
}

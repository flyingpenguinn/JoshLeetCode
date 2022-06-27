public class CheckXMatrix {
    public boolean checkXMatrix(int[][] a) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    if (a[i][j] == 0) {
                        return false;
                    }
                } else if (j + i == n - 1) {
                    if (a[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (a[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

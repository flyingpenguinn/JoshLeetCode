public class CheckIfEveryRowColContainsAllNumbers {
    public boolean checkValid(int[][] a) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int[] m = new int[n + 1];
            for (int j = 0; j < n; ++j) {
                int v = a[i][j];
                if (v < 1 || v > n) {
                    return false;
                }
                if (m[v] != 0) {
                    return false;
                }
                ++m[v];
            }
        }
        for (int j = 0; j < n; ++j) {
            int[] m = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                int v = a[i][j];
                if (v < 1 || v > n) {
                    return false;
                }
                if (m[v] != 0) {
                    return false;
                }
                ++m[v];
            }
        }
        return true;
    }
}

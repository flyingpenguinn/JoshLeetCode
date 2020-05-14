public class ImageSmoother {
    // basic idea is to reuse the original array and blend the sum with original values
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    boolean inRange(int r, int c, int[][] m) {
        return r >= 0 && r < m.length && c >= 0 && c < m[0].length;
    }

    public int[][] imageSmoother(int[][] m) {
        if (m.length == 0) {
            return new int[0][0];
        }
        int rows = m.length;
        int cols = m[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int c = 1;
                int num = m[i][j];
                for (int[] d : dirs) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (inRange(nr, nc, m)) {
                        c++;
                        num += m[nr][nc] % 256;
                    }
                }
                int t = num / c;
                m[i][j] = t * 256 + m[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = (m[i][j] - m[i][j] % 256) / 256;
            }
        }
        return m;

    }
}

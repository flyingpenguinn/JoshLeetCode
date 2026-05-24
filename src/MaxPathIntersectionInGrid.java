public class MaxPathIntersectionInGrid {
    public int maxScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = Integer.MIN_VALUE;

        // 1) Valid single-cell shared segment:
        // only strictly internal cells can be shared alone.
        for (int r = 1; r < m - 1; r++) {
            for (int c = 1; c < n - 1; c++) {
                ans = Math.max(ans, grid[r][c]);
            }
        }

        // 2) Horizontal segments with length >= 2
        for (int r = 0; r < m; r++) {
            int bestLenAtLeast2 = Integer.MIN_VALUE / 4;

            for (int c = 1; c < n; c++) {
                int x = grid[r][c];

                bestLenAtLeast2 = Math.max(
                        bestLenAtLeast2 + x,        // extend existing len >= 2 segment
                        grid[r][c - 1] + x          // start new len 2 segment
                );

                ans = Math.max(ans, bestLenAtLeast2);
            }
        }

        // 3) Vertical segments with length >= 2
        for (int c = 0; c < n; c++) {
            int bestLenAtLeast2 = Integer.MIN_VALUE / 4;

            for (int r = 1; r < m; r++) {
                int x = grid[r][c];

                bestLenAtLeast2 = Math.max(
                        bestLenAtLeast2 + x,        // extend existing len >= 2 segment
                        grid[r - 1][c] + x          // start new len 2 segment
                );

                ans = Math.max(ans, bestLenAtLeast2);
            }
        }

        return ans;
    }
}

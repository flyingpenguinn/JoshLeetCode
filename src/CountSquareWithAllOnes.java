public class CountSquareWithAllOnes {
    // almost identical to #221 maximal squares
    public int countSquares(int[][] a) {

        // dpij: edge length of max square using i,j as right bottom point
        // remember this: dpij = min(dp(i-1, j), dp(i, j-1), dp(i-1, j-1)+1
        // if max square is of len = n we have n squares ending at ij as right bottom
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int row = a.length;
        int col = a[0].length;
        int[][] dp = new int[row][col];
        int r = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (a[i][j] == 0) {
                    continue;
                }

                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                r += dp[i][j];
            }
        }
        return r;
    }
}

// O(n3) algo...
class CountSquareMatriceBinarySearch {
    int[][] sum;

    public int countSquares(int[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        sum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    sum[i][j] = a[i][j];
                } else if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + a[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + a[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + a[i][j];
                }
            }
        }
        int r = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int len = Math.min(i + 1, j + 1);
                for (int k = len; k >= 1; k--) {
                    int sr = i - k + 1;
                    int se = j - k + 1;
                    if (sum(sr, se, i, j) == k * k) {
                        r++;
                    }
                }
            }
        }
        return r;
    }

    int sum(int sr, int sc, int er, int ec) {
        if (sr == 0 && sc == 0) {
            return sum[er][ec];
        } else if (sr == 0) {
            return sum[er][ec] - sum[er][sc - 1];
        } else if (sc == 0) {
            return sum[er][ec] - sum[sr - 1][ec];
        } else {
            return sum[er][ec] - sum[sr - 1][ec] - sum[er][sc - 1] + sum[sr - 1][sc - 1];
        }
    }
}

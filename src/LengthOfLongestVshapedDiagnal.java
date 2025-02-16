import base.ArrayUtils;

import java.util.Arrays;

public class LengthOfLongestVshapedDiagnal {
    // though TLE the idea is the same
    private int[][] dirs = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    private int[][][][][] dp;
    private int Min = (int) -1e9;

    public int lenOfVDiagonal(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        dp = new int[m][n][4][2][3];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    for (int p = 0; p < 2; ++p) {
                        Arrays.fill(dp[i][j][k][p], -1);
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < dirs.length; ++k) {
                    int len = solve(a, i, j, k, 0, 1);
                    res = Math.max(res, len);
                }
            }
        }
        return res;
    }

    private int solve(int[][] a, int i, int j, int k, int turns, int wanted) {
        int m = a.length;
        int n = a[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        if (turns > 1) {
            return 0;
        }
        if (a[i][j] != wanted) {
            return 0;
        }
        if (dp[i][j][k][turns][wanted] != -1) {
            return dp[i][j][k][turns][wanted];
        }
        int res = 1;
        int ni = i + dirs[k][0];
        int nj = j + dirs[k][1];
        int nwanted = 2;
        if (wanted == 1) {
            nwanted = 2;
        } else if (wanted == 2) {
            nwanted = 0;
        } else if (wanted == 0) {
            nwanted = 2;
        }
        int way1 = solve(a, ni, nj, k, turns, nwanted);
        int nk = k;
        ++nk;
        nk %= 4;
        ni = i + dirs[nk][0];
        nj = j + dirs[nk][0];
        int way2 = solve(a, ni, nj, nk, turns + 1, nwanted);
        res += Math.max(way1, way2);
        dp[i][j][k][turns][wanted] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestVshapedDiagnal().lenOfVDiagonal(ArrayUtils.read("[[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]")));
    }
}

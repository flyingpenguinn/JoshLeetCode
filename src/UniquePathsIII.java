import base.ArrayUtils;

import java.util.*;

/*
LC#980
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation:
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.


Note:

1 <= grid.length * grid[0].length <= 20
 */
public class UniquePathsIII {

    // o(r*c*2^rc) number of hamilton cycles
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    Map<Long, Integer>[][] dp;

    public int uniquePathsIII(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new HashMap[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashMap<>();
            }
        }
        int[] start = {0, 0};
        long all = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                }
                if (a[i][j] != -1) {
                    int code = code(i, j, a);
                    all |= (1L << code);
                }
            }
        }
        int scode = code(start[0], start[1], a);
        return dop(start[0], start[1], (1L << scode), a, all);
    }

    private int dop(int i, int j, long st, int[][] a, long all) {
        int m = a.length;
        int n = a[0].length;

        if (a[i][j] == 2) {
            return st == all ? 1 : 0;
        }
        Map<Long, Integer> cm = dp[i][j];
        Integer ch = cm.get(st);
        if (ch != null) {
            return ch;
        }
        int r = 0;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] != -1) {
                int ncode = code(ni, nj, a);
                if (((st >> ncode) & 1) != 1) {
                    long nst = (st | (1L << ncode));
                    int cur = dop(ni, nj, nst, a, all);
                    r += cur;
                }
            }
        }
        dp[i][j].put(st, r);
        return r;
    }

    int code(int i, int j, int[][] a) {
        return i * a[0].length + j;
    }

    public static void main(String[] args) {
        //int[][] grid = ArrayUtils.read("[[1,0,0,0],[0,0,0,0],[0,0,0,2]]");
        int[][] grid = ArrayUtils.read("[[1,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,2]]");
        System.out.println(new UniquePathsIIIDfs().uniquePathsIII(grid));
    }
}

class UniquePathsIIIDfs {
    // no dp, just dfs, 4^r*c but surprisingly fast...
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int uniquePathsIII(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] start = {0, 0};
        int[] end = {0, 0};
        int all = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                }
                if (a[i][j] == 2) {
                    end[0] = i;
                    end[1] = j;
                }
                if (a[i][j] != -1) {
                    all++;
                }
            }
        }
        dop(start[0], start[1], end[0], end[1], all, a);
        return r;
    }

    int r = 0;

    private void dop(int i, int j, int er, int ec, int rem, int[][] a) {
        int m = a.length;
        int n = a[0].length;
        rem--;
        if (i == er && j == ec) {
            if (rem == 0) {
                r++;
            }
            return;
        }
        int old = a[i][j];
        a[i][j] = -2;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] >= 0) {
                dop(ni, nj, er, ec, rem, a);
            }
        }
        a[i][j] = old;
    }


}
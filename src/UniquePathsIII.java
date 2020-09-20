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

    // number of hamilton cycles... worst case O(3^n) n is the number of walkable cells. the bound of 20 is very misleading!
    private int res = 0;

    public int uniquePathsIII(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] start = null;
        int nonob = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    start = new int[]{i, j};
                }
                if (a[i][j] != -1) {
                    nonob++;
                }
            }
        }
        dfs(start[0], start[1], a, nonob);
        return res;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void dfs(int i, int j, int[][] a, int rem) {
        if (a[i][j] == 2) {
            if (rem == 1) { // ==1 because at this moment we haven't deducted it yet for the last position
                res++;
            }
            return;
        }
        int old = a[i][j];
        a[i][j] = -1;
        rem--;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length && a[ni][nj] != -1) {
                dfs(ni, nj, a, rem);
            }
        }
        a[i][j] = old;
    }

}
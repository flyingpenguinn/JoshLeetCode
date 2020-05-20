import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
LC#741
In a N x N grid representing a field of cherries, each cell is one of three possible integers.



0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.


Your task is to collect maximum number of cherries possible by following the rules below:



Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.




Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation:
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.


Note:

grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 */
public class CherryPickup {
    // 1. handle it as if it's two people from i, j and p,q separately walk to 0 left or upward
    // 2. below is plain dp but conveys the key idea: two persons at i,j and p,q intially must move at the same time, i.e. i+j == p+q is always held
    // we can make them arrive at the same point at the same time and then only one of them pick the cherry
    // because they start from the same point, they must spend same steps (distance) in reaching a point as they can only go up or left
    // as long as they both move in the same speed, they will reach at the same time because the distance is the same

    // purpose of them moving in the same speed is to detect duplicated pick early so that we dont need to note the picked flowers down
    int[][][] dp;

    public int cherryPickup(int[][] a) {
        int n = a.length;
        dp = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return doc(n - 1, n - 1, n - 1, a);
    }

    // one person at i,j, the other at p,q. note q is derived because i+j == p+q. they move at the same speed
    private int doc(int i, int j, int p, int[][] a) {
        int q = i + j - p;
        if (i < 0 || j < 0 || p < 0 || q < 0 || a[i][j] == -1 || a[p][q] == -1) {
            return Integer.MIN_VALUE;
        }
        if (i == 0 && j == 0 && p == 0 && q == 0) {
            return a[i][j];
        }
        if (dp[i][j][p] != -1) {
            return dp[i][j][p];
        }
        int cur = 0;
        if (i == p && j == q) {
            if (a[i][j] == 1) {
                cur++;
            }
        } else {
            if (a[i][j] == 1) {
                cur++;
            }
            if (a[p][q] == 1) {
                cur++;
            }
        }
        List<Integer> r = new ArrayList<>();
        // can only go up/left
        r.add(cur + doc(i - 1, j, p - 1, a));
        r.add(cur + doc(i - 1, j, p, a));
        r.add(cur + doc(i, j - 1, p - 1, a));
        r.add(cur + doc(i, j - 1, p, a));
        Collections.sort(r);
        int max = r.get(r.size() - 1);
        dp[i][j][p] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new CherryPickup().cherryPickup(ArrayUtils.read("[[0,1,-1],[1,0,-1],[1,1,1]]")));
        //  System.out.println(new CherryPickupBetterDp().cherryPickup(ArrayUtils.read("[[0,1,-1],[1,0,-1],[1,1,1]]")));
    }
}
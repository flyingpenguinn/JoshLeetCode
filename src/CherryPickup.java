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
    private Integer[][][] dp;
    public int cherryPickup(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new Integer[m][n][m];
        return Math.max(doc(a, 0, 0, 0),0);
    }

    private int doc(int[][] a, int i, int j, int s){
        int m = a.length;
        int n = a[0].length;
        int t = i+j-s;
        if(i==m-1 && j==n-1 && s==m-1){
            return a[i][j];
        }
        if(i<0 || i>=m || j<0 || j>=n || s<0 || s>=m || t<0 || t>=n){
            return Integer.MIN_VALUE;
        }
        if(a[i][j]==-1 || a[s][t]==-1){
            return Integer.MIN_VALUE;
        }
        if(dp[i][j][s] != null){
            return dp[i][j][s];
        }
        int cur = 0;
        if(a[i][j]==1){
            cur++;
        }
        if(a[s][t]==1 && (s!= i || t!=j)){
            cur++;
        }
        int way1 = doc(a, i+1, j, s);
        int way2 = doc(a, i, j+1, s);
        int way3 = doc(a, i+1, j, s+1);
        int way4 = doc(a, i, j+1, s+1);
        int later = Math.max(way1, Math.max(way2, Math.max(way3, way4)));
        dp[i][j][s] = later +cur;
        return dp[i][j][s];
    }

    public static void main(String[] args) {
        System.out.println(new CherryPickup().cherryPickup(ArrayUtils.read("[[0,1,-1],[1,0,-1],[1,1,1]]")));
        //  System.out.println(new CherryPickupBetterDp().cherryPickup(ArrayUtils.read("[[0,1,-1],[1,0,-1],[1,1,1]]")));
    }
}
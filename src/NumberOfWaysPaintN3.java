import java.util.Arrays;

/*
LC#1411
You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours: Red, Yellow or Green while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).

You are given n the number of rows of the grid.

Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown:
html
Example 2:

Input: n = 2
Output: 54
Example 3:

Input: n = 3
Output: 246
Example 4:

Input: n = 7
Output: 106494
Example 5:

Input: n = 5000
Output: 30228214


Constraints:

n == grid.length
grid[i].length == 3
1 <= n <= 5000
 */
public class NumberOfWaysPaintN3 {
    // O(12*12*3*5000)  last row has at most 12 possibilities
    // similar to max students take exams. we dp on each row and enumerate possibilities
    private int[] choices = {0, 121, 123, 131, 132, 212, 213, 232, 231, 313, 312, 323, 321};
    private int Mod = 1000000007;

    public int numOfWays(int n) {
        int[][] dp = new int[n][13];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, 0, n, dp);// 0 means the first row no last row
    }

    private int dfs(int i, int last, int n, int[][] dp) {
        if (i == n) {
            return 1;  // find solutions, return 1 not 0 otherwise there is nothing to return...
        }
        if (dp[i][last] != -1) {
            return dp[i][last];
        }
        long r = 0;
        for (int j = 1; j < choices.length; j++) {
            if (works(choices[j], choices[last])) {
                r += dfs(i + 1, j, n, dp);
                r %= Mod;
            }
        }
        dp[i][last] = (int) (r % Mod);
        return dp[i][last];
    }

    private boolean works(int s, int t) {
        while (s > 0 && t > 0) {
            if (s % 10 == t % 10) {
                return false;
            }
            s /= 10;
            t /= 10;
        }
        return true;
    }

}

class NumberOfWaysToPaintn3Simpler {
    // 121=> 232, 212,213,312,313, so 3* 121 shape, 2*123 shape
    // 123=> 212,231,232,312, so 2*121 shape, 2* 123 shape
    public int numOfWays(int n) {
        int n121 = 2 * 3;
        int n123 = 2 * 3;

        for (int i = 1; i <= n; i++) {
            int new121 = n121 * 3 + n123 * 2;
            int new123 = n121 * 2 + n123 * 2;
            n121 = new121;
            n123 = new123;
        }
        return n121 + n123;
    }
}

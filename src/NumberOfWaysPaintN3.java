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
    // O(12*12*5000)  last row has at most 12 possibilities
    // similar to max students take exams. we dp on each row and enumerate possibilities
    int Mod = 1000000007;

    int[][] dp;
    // all valid one row occurences to pick from
    int[] valids = {121, 123, 131, 132, 212, 213, 231, 232, 312, 313, 321, 323};

    public int numOfWays(int n) {
        dp = new int[n][334];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return (int) don(0, 0, n);
    }

    private long don(int i, int lastrow, int n) {
        if (i == n) {
            return 1L;
        }
        if (dp[i][lastrow] != -1) {
            return dp[i][lastrow];
        }
        long r = 0L;
        for (int cur : valids) {
            boolean bad = false;
            for (int j = 1; j <= 100; j *= 10) {
                if ((lastrow / j) % 10 == (cur / j) % 10) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                r += don(i + 1, cur, n);
                r %= Mod;
            }
        }
        dp[i][lastrow] = (int) (r % Mod);
        return dp[i][lastrow];
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

import java.util.Arrays;

/*
LC#1444
Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.

For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.



Example 1:



Input: pizza = ["A..","AAA","..."], k = 3
Output: 3
Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
Example 2:

Input: pizza = ["A..","AA.","..."], k = 3
Output: 1
Example 3:

Input: pizza = ["A..","A..","..."], k = 1
Output: 1


Constraints:

1 <= rows, cols <= 50
rows == pizza.length
cols == pizza[i].length
1 <= k <= 10
pizza consists of characters 'A' and '.' only.
 */
public class NumberOfWaysToCutPizza {
    long[][][] dp;

    public int ways(String[] a, int k) {
        int m = a.length;
        int n = a[0].length();

        dp = new long[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int[][] sum = new int[m][n];
        initsum(a, sum);
        return (int) doc(0, 0, k, a, sum);
    }

    protected void initsum(String[] a, int[][] sum) {
        int m = a.length;
        int n = a[0].length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = a[i].charAt(j) == 'A' ? 1 : 0;
                sum[i][j] = (i == 0 ? 0 : sum[i - 1][j]) + (j == 0 ? 0 : sum[i][j - 1]) - ((i == 0 || j == 0) ? 0 : sum[i - 1][j - 1]) + cur;
            }
        }
    }

    private int sum(int i, int j, int k, int l, int[][] sum) {
        return sum[k][l] - (i == 0 ? 0 : sum[i - 1][l]) - (j == 0 ? 0 : sum[k][j - 1]) + ((i == 0 || j == 0) ? 0 : sum[i - 1][j - 1]);
    }

    long Mod = 1000000007;

    // upper left at i, j, and k cuts left
    private long doc(int i, int j, int k, String[] a, int[][] am) {
        if (k == 1) {
            return 1L;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        int m = a.length;
        int n = a[0].length();

        int all = sum(i, j, m - 1, n - 1, am);
        long r = 0;
        for (int row = i; row < m - 1; row++) {
            int app = sum(i, j, row, n - 1, am);
            if (app > 0 && all - app > 0) {
                r += doc(row + 1, j, k - 1, a, am);
                r %= Mod;
            }
        }

        for (int col = j; col < n - 1; col++) {
            int app = sum(i, j, m - 1, col, am);
            if (app > 0 && all - app > 0) {
                r += doc(i, col + 1, k - 1, a, am);
                r %= Mod;
            }
        }
        dp[i][j][k] = r;
        return r;
    }


}

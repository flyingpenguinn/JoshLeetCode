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
    // from i,j as top left to m-1, n-1, how many ways to cut
    long Mod = 1000000007;

    public int ways(String[] a, int p) {
        int m = a.length;
        int n = a[0].length();
        int[][] sum = new int[m][n];
        initsum(a, sum);

        // from 0,0 to i,j, cut k pieces, how many ways
        long[][][] dp = new long[m][n][p + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int k = 1; k <= p; k++) {
                    if (k == 1) {
                        int apple = sum(i, j, m - 1, n - 1, sum);
                        // one piece, just one way if we have apple here
                        dp[i][j][k] = apple > 0 ? 1 : 0;
                    } else {
                        for (int s = i + 1; s < m; s++) {
                            int apple1 = sum(i, j, s - 1, n - 1, sum);
                            int apple2 = sum(s, j, m - 1, n - 1, sum);
                            if (apple1 > 0 && apple2 > 0) {
                                dp[i][j][k] += dp[s][j][k - 1];
                            }
                        }
                        for (int s = j + 1; s < n; s++) {
                            int apple1 = sum(i, j, m - 1, s - 1, sum);
                            int apple2 = sum(i, s, m - 1, n - 1, sum);
                            if (apple1 > 0 && apple2 > 0) {
                                dp[i][j][k] += dp[i][s][k - 1];
                            }
                        }
                        dp[i][j][k] %= Mod;
                    }
                }
            }
        }
        return (int) dp[0][0][p];
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
}

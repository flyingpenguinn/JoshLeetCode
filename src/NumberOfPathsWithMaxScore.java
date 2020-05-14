import java.util.List;

/*
LC#1301
You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.

You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.

In case there is no path, return [0, 0].



Example 1:

Input: board = ["E23","2X2","12S"]
Output: [7,1]
Example 2:

Input: board = ["E12","1X1","21S"]
Output: [4,2]
Example 3:

Input: board = ["E11","XXX","11S"]
Output: [0,0]


Constraints:

2 <= board.length == board[i].length <= 100
 */
public class NumberOfPathsWithMaxScore {

    int Mod = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size();
        int n = board.get(0).length();
        int[][][] dp = new int[m][n][2];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c = board.get(i).charAt(j);
                int ci = c == 'E' ? 0 : c - '0'; // deal with E
                if (c == 'X') {
                    dp[i][j][0] = Integer.MIN_VALUE;
                    dp[i][j][1] = 0;
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    // S
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 1;
                    continue;
                }
                // E or numbers
                if (i == m - 1) {
                    dp[i][j][0] = ci + dp[i][j + 1][0];
                    dp[i][j][1] = dp[i][j + 1][1];
                } else if (j == n - 1) {
                    dp[i][j][0] = ci + dp[i + 1][j][0];
                    dp[i][j][1] = dp[i + 1][j][1];
                } else {
                    int down = dp[i + 1][j][0];
                    int right = dp[i][j + 1][0];
                    int diag = dp[i + 1][j + 1][0];
                    int max = Math.max(down, Math.max(right, diag));
                    dp[i][j][0] = ci + max;
                    if (down == max) {
                        dp[i][j][1] += dp[i + 1][j][1];
                        dp[i][j][1] %= Mod;
                    }
                    if (right == max) {
                        dp[i][j][1] += dp[i][j + 1][1];
                        dp[i][j][1] %= Mod;
                    }
                    if (diag == max) {
                        dp[i][j][1] += dp[i + 1][j + 1][1];
                        dp[i][j][1] %= Mod;
                    }
                }
            }
        }
        // not == 0, <0 means no solution
        if (dp[0][0][0] < 0) {
            return new int[]{0, 0};
        } else {
            return dp[0][0];
        }
    }

}

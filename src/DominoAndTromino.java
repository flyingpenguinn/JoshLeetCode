/*
LC#790

We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.

(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)

Example:
Input: 3
Output: 5
Explanation:
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY
Note:

N  will be in range [1, 1000].
 */
public class DominoAndTromino {
    int Mod = 1000000007;

    // dp[i] = dp[i-1]+dp[i-2]+2* (dp[i-3] + dp(i-4)+...dp(1)
    /*example of 5:


    XXYYZ
    XKKZZ

    example of 6:

    XXYYZZ
    XKKJJZ

     */
    public int numTilings(int n) {
        // another way of thinking:
        // dpi - dpi-1 = dpi-1- dpi-3
        // => dpi = 2*dpi-1 - dpi-3

        long[] dp = new long[n + 1];
        dp[0] = 1;
        long sum = 2;
        for (int i = 1; i <= n; i++) {
            long cur = (sum - (i < 1 ? 0 : dp[i - 1]) - (i < 2 ? 0 : dp[i - 2])) % Mod;
            if (cur < 0) {
                cur += Mod;
            }
            dp[i] = cur;
            sum += 2 * dp[i];
            sum %= Mod;
        }
        return (int) dp[n];
    }
}

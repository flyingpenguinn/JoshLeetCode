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
    // so dp[i] = 2*dp[i-1]+dp[i-3]. we can get this via subtracting dp[i] and dp[i-1]

    public int numTilings(int n) {
        int nn = Math.max(4, n+1);
        long[] dp = new long[nn];
        long mod = (long) (1e9+7);
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for(int i=4; i<=n; ++i){
            dp[i] = 2*dp[i-1]+dp[i-3];
            dp[i] %= mod;
        }
        return (int) dp[n];
    }
}

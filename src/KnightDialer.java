/*
LC#935
A chess knight can move as indicated in the chess diagram below:

 .



This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.



Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46


Note:

1 <= N <= 5000
 */
public class KnightDialer {
    int[][] next = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};

    int Mod = 1000000007;

    public int knightDialer(int n) {
        long[][] dp = new long[10][2];
        for (int i = 0; i <= 9; i++) {
            dp[i][0] = 1L;
        }
        long r = 0;
        for (int t = 1; t <= n - 1; t++) {
            // must loop all i. ni can >i
            for (int i = 0; i <= 9; i++) {
                dp[i][t % 2] = 0;
                for (int ni : next[i]) {
                    dp[i][t % 2] += dp[ni][(t - 1) % 2];
                    dp[i][t % 2] %= Mod;
                }
            }
        }
        for (int i = 0; i <= 9; i++) {
            r += dp[i][(n - 1) % 2];
            r %= Mod;
        }
        return (int) r;
    }
}

class KnightDialerTopdown {
    int[][] next = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};

    int Mod = 1000000007;

    int[][] dp;

    public int knightDialer(int n) {
        long r = 0L;
        dp = new int[10][n];
        for (int i = 0; i <= 9; i++) {
            r += dok(i, n - 1);
            r %= Mod;
        }
        return (int) r;
    }

    int dok(int i, int t) {
        if (t == 0) {
            return 1;
        }
        if (dp[i][t] != 0) {
            return dp[i][t];
        }
        long r = 0L;
        for (int ni : next[i]) {
            r += dok(ni, t - 1);
        }
        r %= Mod;
        dp[i][t] = (int) r;
        return dp[i][t];
    }
}

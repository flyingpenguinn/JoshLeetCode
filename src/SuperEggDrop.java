/*
LC#887
You are given K eggs, and you have access to a building with N floors from 1 to N.

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?



Example 1:

Input: K = 1, N = 2
Output: 2
Explanation:
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4


Note:

1 <= K <= 100
1 <= N <= 10000
 */

public class SuperEggDrop {
    // break point for n,k won't deviate from n-1,k's point too much
    // note if it's 2 eggs, the first floor is x, then x+(x-1)+....1==100, we solve for x = 14
    int[][] dp;
    int[][] mini;
    int Max = 1000;

    public int superEggDrop(int k, int n) {

        dp = new int[n + 1][k + 1];
        mini = new int[n + 1][k + 1];
        return dos(n, k);
    }


    int dos(int n, int k) {
        // even one floor needs test. return 0 only when n is 0
        if (n <= 0) {
            return 0;
        }
        if (k <= 0) {
            return Max;
        }
        if (dp[n][k] != 0) {
            return dp[n][k];
        }
        int min = Max;
        int cmini = -1;
        dos(n - 1, k);
        // choosing between these two. note it won't be always +1 because the exact best point (intersection of the two lines) may not be an integer...
        int s = Math.max(1, mini[n - 1][k]);
        int e = Math.min(mini[n - 1][k] + 1, n);
        for (int i = s; i <= e; i++) {
            int low = dos(i - 1, k - 1) + 1;
            // break
            int high = dos(n - i, k) + 1;
            // not broken
            int cur = Math.max(low, high);
            if (cur < min) {
                min = cur;
                cmini = i;
            }
        }
        dp[n][k] = min;
        mini[n][k] = cmini;
        return min;
    }

}

class SuperEggDropAnotherDp {
    // dp(i,j) means i eggs, j drops, how many floors we can cover
    // it can cover dp(i-1, j-1) floors if egg is broken, and dp(i-1, j) floors more if the eggs is not broken
    // counting in the floor that we tested, then +1
    public int superEggDrop(int k, int n) {
        long[][] dp = new long[k + 1][n + 1];
        // dp[0][x] = 0
        // dp[x][0] = 0

        // i eggs, j drops
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + 1;
                if (i == k && dp[i][j] >= n) {
                    return j;
                }
            }
        }
        return -1;
    }
}


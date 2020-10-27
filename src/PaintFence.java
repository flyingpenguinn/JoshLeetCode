/*
LC#276
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3
 -----      -----  -----  -----
   1         c1     c1     c2
   2         c1     c2     c1
   3         c1     c2     c2
   4         c2     c1     c1
   5         c2     c1     c2
   6         c2     c2     c1
 */
public class PaintFence {
    // top down.
    // note we dont really need to remember what the previous paint was- we just know same or not same
    private int[][] dp;

    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }

        dp = new int[n][k + 1];
        return don(0, 0, n, k);
    }

    // we have consecutive colors at i-1 amount to eq. now at i. numbers of ways to paint from i to n-1
    int don(int i, int eq, int n, int k) {
        if (i == n) {
            return 1;
        }
        if (dp[i][eq] != 0) {
            return dp[i][eq];
        }
        int we = 0;
        int wne = 0;
        if (eq + 1 <= 2) {
            we = don(i + 1, eq + 1, n, k);
        }
        wne = (k - 1) * don(i + 1, 1, n, k);
        int rt = we + wne;
        dp[i][eq] = rt;
        return rt;
    }
}

class PaintFenceO1Space {
    // direct translation of above top down code. note we dont need special treatment of dp[0][0] it naturally fits into the recursion
    // but we do need to process n=0 or k=0 separately otherwise it becomes 1
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        int[][] dp = new int[2][3];
        for (int j = 0; j < 3; j++) {
            dp[n % 2][j] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 2; j++) {
                int same = 0;
                if (j + 1 <= 2) {
                    same = dp[(i + 1) % 2][j + 1];
                }
                int notsame = (k - 1) * dp[(i + 1) % 2][1];
                dp[i % 2][j] = same + notsame;
            }
        }
        return dp[0][0];
    }
}

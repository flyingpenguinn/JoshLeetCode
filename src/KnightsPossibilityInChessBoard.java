import java.util.Arrays;

/*
LC#688
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.







Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.



Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.


Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
 */
public class KnightsPossibilityInChessBoard {
    double[][][] dp;
    public double knightProbability(int n, int k, int r, int c) {
        dp = new double[n][n][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1.0);
            }
        }
        return dok(r, c, k, n);
    }

    int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};

    // it's ok if it goes back to an old point- k-1 so status is different
    double dok(int r, int c, int k, int n) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0.0;
        }
        // if ==0, anything after this becomes 0, so we dont need to calc it any more
        if (k == 0) {
            return 1.0;
        }
        if (dp[r][c][k] >= 0) {
            return dp[r][c][k];
        }
        double rt = 0.0;
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            double later = dok(nr, nc, k - 1, n);
            rt += later * 1.0 / 8.0;
        }
        dp[r][c][k] = rt;
        return rt;
    }


    public static void main(String[] args) {
        KnightsPossibilityInChessBoard kpi = new KnightsPossibilityInChessBoard();
        System.out.println(kpi.knightProbability(8, 12, 6, 4));

    }
}

import java.util.*;

/*
LC#51
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class Nqueens {
    List<List<String>> r = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // index is row, cols[i] = the col we put on row i
        int[] cols = new int[n];
        dos(0, cols, n);
        return r;
    }

    private void dos(int i, int[] cols, int n) {
        if (i == n) {
            r.add(sol(cols, n));
            return;
        }
        for (int j = 0; j < n; j++) {
            boolean good = isGood(i, j, cols);
            if (good) {
                cols[i] = j;
                dos(i + 1, cols, n);
                // we will override anyway so no need to backtrack
            }
        }
    }

    private boolean isGood(int i, int j, int[] cols) {
        // check with previous rows. k is a previous row
        for (int k = 0; k < i; k++) {
            if (canattack(i, j, k, cols[k])) {
                return false;
            }
        }
        return true;
    }

    private boolean canattack(int i, int j, int oi, int oj) {
        return i == oi || j == oj || (i - j == oi - oj) || (i + j == oi + oj);
    }

    private List<String> sol(int[] cols, int n) {
        List<String> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            int col = cols[i];
            sb.setCharAt(col, 'Q');
            r.add(sb.toString());
        }
        return r;
    }
}

import java.util.*;

/*
LC#52
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */
public class NQueensII {

    // O(n!) note because status is a list of int it's n! in nature
    Map<Integer, Map<List<Integer>, Integer>> dp = new HashMap<>();

    public int totalNQueens(int n) {
        return dot(0, new ArrayList<>(), n);
    }

    int dot(int i, List<Integer> cols, int n) {
        if (i == n) {
            return 1;
        }
        int ways = 0;
        for (int j = 0; j < n; j++) {
            if (isgood(i, j, cols)) {
                cols.add(j);
                ways += dot(i + 1, cols, n);
                cols.remove(cols.size() - 1);
            }
        }
        return ways;
    }

    private boolean isgood(int i, int j, List<Integer> cols) {
        for (int k = 0; k < i; k++) {
            if (canattack(i, j, k, cols.get(k))) {
                return false;
            }
        }
        return true;
    }

    private boolean canattack(int i, int j, int oi, Integer oj) {
        return i == oi || j == oj || (i - j == oi - oj) || (i + j == oi + oj);
    }

}

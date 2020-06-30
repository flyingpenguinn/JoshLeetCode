import base.ArrayUtils;

/*
LC#79
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.


Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */

public class WordSearch {
    // O(m*n*4^l) where<l is the length

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] a, String w) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == w.charAt(0) && isWord(a, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWord(char[][] a, int i, int j, String w, int k) {
        // aij == w[k] checked beforehand already
        if (k == w.length() - 1) {
            return true;
        }
        char original = a[i][j];
        a[i][j] = '-';
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length && a[ni][nj] == w.charAt(k + 1)) {
                if (isWord(a, ni, nj, w, k + 1)) {
                    return true;
                }
            }
        }
        a[i][j] = original;
        return false;
    }

}
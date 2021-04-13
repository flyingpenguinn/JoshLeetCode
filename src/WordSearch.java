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
    public boolean exist(char[][] a, String word) {
        if (a == null || a.length == 0 || a[0].length == 0 || word.isEmpty()) {
            return false;
        }
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean good = dfs(a, i, j, word, 0);
                if (good) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // checking i, j against k. k is valid value
    private boolean dfs(char[][] a, int i, int j, String word, int k) {
        int m = a.length;
        int n = a[0].length;
        if (a[i][j] != word.charAt(k)) {
            return false;
        }
        if (k == word.length() - 1) {
            return true;
        }
        char old = a[i][j];
        a[i][j] = '*';
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] != '*') {
                if (dfs(a, ni, nj, word, k + 1)) {
                    return true;
                }
            }
        }
        a[i][j] = old;
        return false;
    }
}
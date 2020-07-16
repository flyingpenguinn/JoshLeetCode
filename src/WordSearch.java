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
    private int[][] dirs = {{-1,0}, {1,0}, {0, -1}, {0,1}};
    public boolean exist(char[][] a, String word) {
        // check null or validate error out if needed. word non empty
        for(int i=0; i<a.length;i++){
            for(int j=0; j<a[0].length;j++){
                if(word.charAt(0) == a[i][j] && doExist(a, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    // a ij matching k already
    private boolean doExist(char[][] a, int i, int j, String word, int k){
        if(k==word.length()-1){
            return true;
        }
        char original = a[i][j];
        a[i][j]='-';
        boolean good = false;
        for(int[] d: dirs){
            int ni = i+d[0];
            int nj = j+d[1];
            if(ni>=0 && ni<a.length && nj>=0 && nj<a[0].length && a[ni][nj] == word.charAt(k+1)){
                if(doExist(a, ni, nj, word, k+1)){
                    good = true;
                    break;
                }
            }
        }
        a[i][j] = original;
        return good;
    }

}
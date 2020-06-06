/*
LC#723
This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.



Example:

Input:
board =
[[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]

Output:
[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]

Explanation:



Note:

The length of board will be in the range [3, 50].
The length of board[i] will be in the range [3, 50].
Each board[i][j] will initially start as an integer in the range [1, 2000].
 */
public class CandyCrush {
    // use a gone array to avoid a set of points..
    // use two pointers to handle drop down
    // use a gone array to avoid a set of points..
    // use two pointers to handle drop down
    int mergecount = 3;

    public int[][] candyCrush(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        while (true) {
            boolean[][] gone = new boolean[m][n];
            boolean hasgone = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == 0) {
                        continue;
                    }
                    // still check even if i, j is gone because their neighbor may not be gone yet
                    int nj = j;
                    while (nj < n && a[i][nj] == a[i][j] && nj-j<mergecount) {
                        nj++;
                    }
                    if (nj - j  == mergecount) {
                        hasgone = true;
                        nj = j;
                        while (nj-j<mergecount && a[i][nj] == a[i][j]) {
                            gone[i][nj] = true;
                            nj++;
                        }
                    }
                    int ni = i;
                    while (ni < m && a[ni][j] == a[i][j] && ni-i <mergecount) {
                        ni++;
                    }
                    if (ni - i  == mergecount) {
                        hasgone = true;
                        ni = i;
                        while (ni -i < mergecount  && a[ni][j] == a[i][j]) {
                            gone[ni][j] = true;
                            ni++;
                        }
                    }
                }
            }
            if (!hasgone) {
                break;
            }
            for (int j = 0; j < n; j++) {
                int ri = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    if (!gone[i][j]) {
                        a[ri--][j] = a[i][j];
                    }
                }
                while (ri >= 0) {
                    a[ri--][j] = 0;
                }
            }
        }
        return a;
    }

}

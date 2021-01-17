import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#782
An N x N board contains only 0s and 1s. In each move, you can swap any 2 rows with each other, or any 2 columns with each other.

What is the minimum number of moves to transform the board into a "chessboard" - a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.

Examples:
Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
Output: 2
Explanation:
One potential sequence of moves is shown below, from left to right:

0110     1010     1010
0110 --> 1010 --> 0101
1001     0101     1010
1001     0101     0101

The first move swaps the first and second column.
The second move swaps the second and third row.


Input: board = [[0, 1], [1, 0]]
Output: 0
Explanation:
Also note that the board with 0 in the top left corner,
01
10

is also a valid chessboard.

Input: board = [[1, 0], [1, 0]]
Output: -1
Explanation:
No matter what sequence of moves you make, you cannot end with a valid chessboard.
Note:

board will have the same number of rows and columns, a number in the range [2, 30].
board[i][j] will be only 0s or 1s.
 */
public class TransformToChessBoard {
    // for row/col, refer to 0 or 0's opposite
    private int Max = 10000000;

    public int movesToChessboard(int[][] a) {
        int n = a.length;
        int res = 0;
        for (int round = 0; round <= 1; round++) {
            // row and col, round0 and 1
            int rres = Max;
            for (int way = 0; way <= 1; way++) {
                // use row/col 0 or its opposite
                int cur = 0;
                int[] w = new int[2];
                for (int i = 0; i < n; i++) {
                    boolean cur1 = same(a, 0, i, round, way);
                    boolean cur2 = same(a, 0, i, round, way ^ 1);
                    int index = way;
                    if (i % 2 == 1) {
                        index = way ^ 1;
                        cur1 = same(a, 0, i, round, way ^ 1);
                        cur2 = same(a, 0, i, round, way);
                    }

                    if (cur1) {
                        continue;
                    } else if (cur2) {
                        if (w[index] != 0) {
                            w[index]--;
                            // dont really need to swap. swap will ruin 0 and make it incorrect
                            cur++;
                        } else {
                            w[index ^ 1]++;
                        }
                    } else {
                        cur = Max;
                        break;
                    }
                }
                if (w[0] > 0 || w[1] > 0) {
                    cur = Max;
                }
                rres = Math.min(rres, cur);
            }
            res += rres;
        }

        return res >= Max ? -1 : res;
    }

    private boolean same(int[][] a, int i, int j, int round, int flag) {
        int n = a.length;
        if (round == 0) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] != (a[j][k] ^ flag)) {
                    return false;
                }
            }
            return true;
        } else {
            for (int k = 0; k < n; k++) {
                if (a[k][i] != (a[k][j] ^ flag)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]")));
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[0,1,1],[1,0,0],[1,0,0]]")));
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[0,0,1,0,1,1],[1,1,0,1,0,0],[1,1,0,1,0,0],[0,0,1,0,1,1],[1,1,0,1,0,0],[0,0,1,0,1,1]]")));
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[1,0,0],[0,1,1],[1,0,0]]")));

        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[0,0,1,1],[1,1,0,0],[0,1,0,1],[1,0,1,0]]")));
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[1,1,0],[0,0,1],[0,0,1]]")));

        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[0, 1], [1, 0]]")));
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[1, 0], [1, 0]]")));
    }
}

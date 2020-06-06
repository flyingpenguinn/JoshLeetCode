import base.ArrayUtils;

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
    /*
    For any row/column, the number of 1 and 0 must differ by at most 1.
    For any two rows r1 and r2, either board[r1][c] == board[r2][c] for all c or board[r1][c] != board[r2][c] for all c. Same for columns.
     */
    public int movesToChessboard(int[][] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // rule 2: any two rows are either same or totally different
            // we only need to compare with row 0 in this case
            int rowflag = a[i][0] ^ a[0][0];// either 0 vs 0 or 1vs1
            for (int j = 1; j < n; j++) {
                int curflag = a[i][j] ^ a[0][j];
                if (curflag != rowflag) {
                    return -1;
                }
            }
        }

        int rowcount = 0;
        int colcount = 0;
        // because each row are either same or totally different, we only need to verify first col and row's 1 count
        // below is to verify rule 1
        for (int i = 0; i < n; i++) {
            if (a[i][0] == 1) {
                rowcount++;
            }
            if (a[0][i] == 1) {
                colcount++;
            }
        }
        int orowcount = n - rowcount;
        int ocolcount = n - colcount;
        if (Math.abs(orowcount - rowcount) > 1 || Math.abs(ocolcount - colcount) > 1) {
            return -1;
        }
        // we must have a solution. now suppose we want to make sure rows are 010101. so any 1 appearing in bad place will be counted
        // in the end the moves are either rmove, or n-rmove. in the opposite case we need to change n-rmove numbers
        // to make it swap based, /2 is the number of swaps, when the change count is even. otherwise we can't swap
        int rmove = 0;
        for (int i = 0; i < n; i++) {
            // assume it must be 010101
            if (a[i][0] != i % 2) {
                rmove++;
            }
        }
        if (n % 2 == 1) {
            // the moves must be even otherwise we can't /2 and get the swaps
            rmove = rmove % 2 == 0 ? rmove : n - rmove;
        } else {
            rmove = Math.min(rmove, n - rmove);
        }

        int cmove = 0;
        for (int i = 0; i < n; i++) {
            // assume it must be 010101
            if (a[0][i] != i % 2) {
                cmove++;
            }
        }
        if (n % 2 == 1) {
            // the moves must be even otherwise we can't /2 and get the swaps
            cmove = cmove % 2 == 0 ? cmove : n - cmove;
        } else {
            cmove = Math.min(cmove, n - cmove);
        }
        // we /2: if moves are even, then how to swap: swap any misplaced 0 or 1 that are counted as "misplaced".
        // because of rule 1, there must be same number of 0 and 1 to be swapped
        return (rmove + cmove) / 2;
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

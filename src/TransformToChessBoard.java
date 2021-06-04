import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
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
    public int movesToChessboard(int[][] a) {
        int n = a.length;
        // either the same as row 0 or its flip
        int[] rev = flip(a[0]);
        for(int i=1; i<n; i++){
            if(!Arrays.equals(a[i], a[0]) && !Arrays.equals(a[i], rev)){
                return -1;
            }
        }
        // 1 in row 0 and col 0 must be within range
        int r0 = countRow(a, 0);
        int c0 = countCol(a, 0);
        if(n%2==0 && (r0 != n/2 || c0 != n/2)){
            return -1;
        }else if(n%2==1 && (r0 >n/2+1 || r0<n/2)){
            return -1;
        }else if(n%2==1 && (c0 >n/2+1 || c0<n/2)){
            return -1;
        }
        if(n%2==0){
            // note the first col may not start with because it may not be the real first col at all. hence we try either way
            int minRow = Math.min(calcRow(a, 0), calcRow(a, 1));
            int minCol = Math.min(calcCol(a, 0), calcCol(a, 1));
            return minRow + minCol;
        }else{
            // here we know what the first row and col should be given there are more 1 than 0 or vice versa
            return calcRow(a, r0-n/2) + calcCol(a, c0-n/2);
        }
    }

    private int[] flip(int[] row){
        int n = row.length;
        int[] res = new int[n];
        for(int j=0; j<n; j++){
            res[j] = row[j]^1;
        }
        return res;
    }

    private int countRow(int[][] a, int i){
        int res = 0;
        for(int j=0; j<a[i].length; j++){
            res += a[i][j];
        }
        return res;
    }

    private int countCol(int[][] a, int j){
        int res = 0;
        for(int i=0; i<a.length; i++){
            res += a[i][j];
        }
        return res;
    }

    private int calcRow(int[][] a, int should){
        int n = a.length;
        int cres = 0;
        for(int j=0; j<n; j++){
            if(a[0][j] != should){
                cres++;
            }
            should ^= 1;
        }
        return cres/2;
    }

    private int calcCol(int[][] a, int should){
        int n = a.length;
        int rres = 0;
        for(int i=0; i<n; i++){
            if(a[i][0] != should){
                rres++;
            }
            should ^= 1;
        }
        return rres/2;
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

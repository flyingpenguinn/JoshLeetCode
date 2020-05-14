import java.util.*;

/*
LC#37
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
 */
public class SudokuSolver {
    int rows = 0;
    int cols = 0;
    int[][] rc;
    int[][] cc;
    int[][] sc;
    char[][] a;

    public void solveSudoku(char[][] a) {
        this.a = a;
        rows = a.length;
        cols = a[0].length;
        rc = new int[rows][10];
        cc = new int[cols][10];
        sc = new int[10][10];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (a[i][j] == '.') {
                    continue;
                }
                int v = a[i][j] - '0';
                if (rc[i][v]++ != 0) {
                    return;
                }
                if (cc[j][v]++ != 0) {
                    return;
                }
                cc[j][v]++;
                // row = i/3, col = i/3, overall index = 3*row + col
                int index = (i / 3) * 3 + (j / 3);
                if (sc[index][v]++ != 0) {
                    return;
                }
            }
        }
        solve(0);
    }

    // whether we found the solution. note we have to use this to avoid the correct result being stripped out by backtracking
    private boolean solve(int i) {
        if (i == rows * cols) {
            return true;
        }
        int ri = i / rows;
        int cj = i % cols;
        if (a[ri][cj] != '.') {
            return solve(i + 1);
        } else {
            for (int k = 1; k <= 9; k++) {
                if (rc[ri][k] != 0) {
                    continue;
                }
                if (cc[cj][k] != 0) {
                    continue;
                }
                int subindex = (ri / 3) * 3 + cj / 3;
                if (sc[subindex][k] != 0) {
                    continue;
                }
                rc[ri][k]++;
                cc[cj][k]++;
                sc[subindex][k]++;
                a[ri][cj] = (char) (k + '0');
                boolean found = solve(i + 1);
                if (found) {
                    return true;
                }
                rc[ri][k]--;
                cc[cj][k]--;
                sc[subindex][k]--;
                a[ri][cj] = '.';
            }
        }
        return false;
    }


    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuSolver().solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}

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
    // try out each cell with 1 to 9 . remember to set the . back!
    // row, col, sub cell all have maps
    // mind how we calc subindex
    public void solveSudoku(char[][] a) {
        // assuming non null otherwise error out
        int[][] rm = new int[10][10];
        int[][] cm = new int[10][10];
        int[][] subm = new int[10][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j] != '.') {
                    int subindex = subindex(i, j);
                    int v = a[i][j] - '0';
                    rm[i][v]++;
                    cm[j][v]++;
                    subm[subindex][v]++;
                }
            }
        }
        dfs(a, 0, 0, rm, cm, subm);  // can optimize
    }

    private int subindex(int i, int j) {
        int subrow = i / 3;
        int subcol = j / 3;
        return subrow * 3 + subcol;
    }

    private boolean dfs(char[][] a, int i, int j, int[][] rm, int[][] cm, int[][] subm) {
        if (i == 9) {
            return true;
        }
        if (j == 9) {
            return dfs(a, i + 1, 0, rm, cm, subm);
        }
        if (a[i][j] != '.') {
            return dfs(a, i, j + 1, rm, cm, subm);
        } else {
            int subindex = subindex(i, j);
            for (int v = 1; v <= 9; v++) {
                if (rm[i][v] == 0 && cm[j][v] == 0 && subm[subindex][v] == 0) {
                    rm[i][v]++;
                    cm[j][v]++;
                    subm[subindex][v]++;
                    a[i][j] = (char) (v + '0');
                    boolean solved = dfs(a, i, j + 1, rm, cm, subm);
                    if (!solved) {
                        rm[i][v]--;
                        cm[j][v]--;
                        subm[subindex][v]--;
                        a[i][j] = '.';
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuSolver().solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}

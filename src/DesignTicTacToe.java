import java.util.*;

/*
LC#348
Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
 */
public class DesignTicTacToe {

    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(3);
        System.out.println(ttt.move(0, 0, 1));
        System.out.println(ttt.move(0, 1, 1));
        System.out.println(ttt.move(1, 2, 1));
    }
}

class TicTacToe {
    int[][] rs;
    int[][] cs;
    // only need 2: only r+c==n-1 or r-c==0 are diagonal. throw away other values
    int[] ds = new int[2];
    int[] nds = new int[2];
    int n;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        this.n = n;
        for (int i = 0; i < 2; i++) {
            rs = new int[n][2];
            cs = new int[n][2];
            ds = new int[2];
            nds = new int[2];
        }
    }

    /**
     * Player {p} makes a move at ({r}, {col}).
     *
     * @param r The r of the board.
     * @param c The column of the board.
     * @param p The p, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int r, int c, int p) {
        int i = p - 1;
        rs[r][i]++;
        cs[c][i]++;
        if (rs[r][i] == n || cs[c][i] == n) {
            return p;
        }
        if (r == c) {
            ds[i]++;
        }
        if (ds[i] == n) {
            return p;
        }
        if (r + c == n - 1) {
            nds[i]++;
        }
        if (nds[i] == n) {
            return p;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
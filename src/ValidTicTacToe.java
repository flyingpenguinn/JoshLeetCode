import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidTicTacToe {


    public boolean validTicTacToe(String[] board) {
        int player1 = 0;
        int player2 = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == changeto[0]) {
                    player1++;
                } else if (board[i].charAt(j) == changeto[1]) {
                    player2++;
                }
            }
        }
        // play in turn
        if (!(player1 == player2 || player1 == player2 + 1)) {
            return false;
        }
        // if won, game ends now
        if (won(board, 1) && !(player2 == player1)) {
            return false;
        }
        if (won(board, 0) && !(player2 == player1 - 1)) {
            return false;
        }
        return true;
    }

    char[] changeto = {'X', 'O'};

    private boolean won(String[] board, int player) {
        return wonRow(board, player) || wonCol(board, player) || wonDiag(board, player);
    }

    private boolean wonDiag(String[] board, int player) {
        int n = board.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (board[i].charAt(i) == changeto[player]) {
                count++;
            }
        }
        if (count == n) {
            return true;
        }
        count = 0;
        for (int i = 0; i < n; i++) {
            if (board[i].charAt(n - 1 - i) == changeto[player]) {
                count++;
            }
        }
        return count == n;
    }

    private boolean wonCol(String[] board, int player) {
        int n = board.length;
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (board[i].charAt(j) == changeto[player]) {
                    count++;
                }
            }
            if (count == n) {
                return true;
            }
        }
        return false;
    }

    private boolean wonRow(String[] board, int player) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (board[i].charAt(j) == changeto[player]) {
                    count++;
                }
            }
            if (count == n) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String[] board = {"XO ", "XO ", "XO "};
        System.out.println(new ValidTicTacToe().validTicTacToe(board));
    }
}
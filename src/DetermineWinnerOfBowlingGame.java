public class DetermineWinnerOfBowlingGame {
    public int isWinner(int[] player1, int[] player2) {
        int sc1 = score(player1);
        int sc2 = score(player2);
        if (sc1 > sc2) {
            return 1;
        } else if (sc2 > sc1) {
            return 2;
        } else {
            return 0;
        }
    }

    private int score(int[] p) {
        int n = p.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {

            if ((i - 2 >= 0) && (p[i - 1] == 10 || p[i - 2] == 10)) {
                res += 2 * p[i];
            } else if (i - 1 >= 0 && p[i - 1] == 10) {
                // mind this!
                res += 2 * p[i];
            } else {
                res += p[i];
            }
        }
        return res;
    }
}

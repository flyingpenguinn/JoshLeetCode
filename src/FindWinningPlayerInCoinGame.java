public class FindWinningPlayerInCoinGame {
    public String losingPlayer(int x, int y) {
        int turn = 0;
        while (true) {
            if (x >= 1 && y >= 4) {
                x -= 1;
                y -= 4;
                ++turn;
            } else {
                break;
            }
        }
        return turn % 2 == 1 ? "Alice" : "Bob";
    }
}

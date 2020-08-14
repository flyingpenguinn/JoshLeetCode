public class FindTheWinnerOfArrayGame {
    public int getWinner(int[] a, int k) {
        // 1. assuming all distinct num
        // 2. always have one solution
        // 3. length>=2
        int winner = a[0];
        int won = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < winner) {
                won++;
            } else {
                winner = a[i];
                won = 1;
            }
            if (won == k) {
                break;
            }
        }
        // if we can't get to k times when we reach the max number then max number is the answer
        return winner;
    }
}

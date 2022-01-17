public class MinMovsToReachTarget {
    public int minMoves(int t, int md) {
        return solve(t, md);
    }

    private int solve(int t, int md) {
        if (t == 1) {
            return 0;
        }
        if (md == 0) {
            return t - 1;
        } else if (t % 2 == 1) {
            return 1 + solve(t - 1, md);
        } else {
            return 1 + solve(t / 2, md - 1);
        }
    }
}

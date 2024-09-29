public class FindKthCharInStringGameIandII {
    // working back
    public char kthCharacter(long k, int[] op) {
        long base = 1;
        long opi = 0;
        while (base < k) {
            base *= 2;
            ++opi;
        }
        int rt = solve(k, op, opi - 1);
        return (char) ('a' + rt);
    }

    private int solve(long k, int[] op, long index) {
        if (k == 1) {
            return 0;
        }
        long cur = index;
        if (k <= (1L << cur)) {
            return solve(k, op, index - 1);
        } else if (op[(int) cur] == 1) {
            return ((solve(k - (1L << cur), op, index - 1) + 1) % 26);
        } else {
            return solve(k - (1L << cur), op, index - 1);
        }

    }
}

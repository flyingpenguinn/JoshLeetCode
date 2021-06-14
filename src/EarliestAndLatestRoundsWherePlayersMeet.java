public class EarliestAndLatestRoundsWherePlayersMeet {
    // just a simple recursion will do...
    private int min = Integer.MAX_VALUE;
    private int max = -1;

    private void dfs(int st, int round, int i, int j, int a, int b, int n) {
        //  System.out.println(st + " " + round + " " + i + " " + j);
        if (i >= j) {
            dfs(st, round + 1, 0, n - 1, a, b, n);
        } else if (!contains(st, i)) {
            dfs(st, round, i + 1, j, a, b, n);
        } else if (!contains(st, j)) {
            dfs(st, round, i, j - 1, a, b, n);
            // below this line, it contains i and j, they are competing
        } else if (i == a && j == b) {
            // they meet
            min = Math.min(min, round);
            max = Math.max(max, round);

        } else if (i == a || i==b) {
            dfs((st ^ (1 << j)), round, i + 1, j - 1, a, b, n);
        } else if (j == b || j==a) {
            dfs((st ^ (1 << i)), round, i + 1, j - 1, a, b, n);
        } else {
            dfs((st ^ (1 << j)), round, i + 1, j - 1, a, b, n);
            dfs((st ^ (1 << i)), round, i + 1, j - 1, a, b, n);
        }
    }

    private boolean contains(int st, int i) {
        return ((st >> i) & 1) == 1;
    }

    public int[] earliestAndLatest(int n, int a, int b) {
        dfs((1 << n) - 1, 1, 0, n - 1, a - 1, b - 1, n);
        return new int[]{min, max};
    }
}

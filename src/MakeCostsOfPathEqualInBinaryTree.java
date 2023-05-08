public class MakeCostsOfPathEqualInBinaryTree {
    // compare with " Lowest Common Ancestor of Deepest Leaves"
    // note we can also do top down - enlarge each node so that the max path sum it affects = the max path sum itself. then go on to enlarge later nodes
    private int res = 0;

    public int minIncrements(int n, int[] cost) {
        dfs(0, n, cost);
        return res;
    }

    private int dfs(int i, int n, int[] cost) {
        if (i >= n) {
            return 0;
        }
        int left = dfs(2 * i + 1, n, cost);
        int right = dfs(2 * i + 2, n, cost);
        int delta = Math.abs(left - right);
        res += delta;
        return cost[i] + Math.max(left, right);
    }
}

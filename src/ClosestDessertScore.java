public class ClosestDessertScore {
    private int res = -1;
    private int resdiff = Integer.MAX_VALUE;

    public int closestCost(int[] bc, int[] tc, int t) {

        for (int i = 0; i < bc.length; i++) {
            dfs(tc, 0, bc[i], t);
        }
        return res;
    }

    private void dfs(int[] tc, int i, int cur, int t) {
        if (i == tc.length) {
            int curdiff = Math.abs(cur - t);
            if (curdiff < resdiff) {
                resdiff = curdiff;
                res = cur;
            } else if (curdiff == resdiff) {
                if (cur < res) {
                    res = cur;
                }
            }
            return;
        }
        dfs(tc, i + 1, cur + tc[i], t);
        dfs(tc, i + 1, cur + 2 * tc[i], t);
        dfs(tc, i + 1, cur, t);
    }
}

public class NumberOfNodesWithValueOne {
    public int numberOfNodes(int n, int[] queries) {
        int[] v = new int[n + 1];

        for (int q : queries) {
            v[q] += 1;
        }
        return dfs(1, v, 0);
    }

    private int dfs(int i, int[] v, int cur) {
        int n = v.length;
        if (i >= n) {
            return 0;
        }
        cur += v[i];
        int res = 0;
        if (cur % 2 == 1) {
            res += 1;
        }
        res += dfs(i * 2, v, cur);
        res += dfs(i * 2 + 1, v, cur);
        return res;
    }
}

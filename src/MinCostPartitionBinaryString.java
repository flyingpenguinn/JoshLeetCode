public class MinCostPartitionBinaryString {
    private int[] ones;

    public long minCost(String s, int v1, int v2) {
        char[] c = s.toCharArray();
        int n = s.length();
        ones = new int[n];
        for (int i = 0; i < n; ++i) {
            ones[i] = (i == 0 ? 0 : ones[i - 1]) + (c[i] - '0');


        }
        return solve(c, 0, n - 1, v1, v2);
    }

    private long solve(char[] c, int l, int u, long v1, long v2) {
        if (l > u) {
            return 0;
        }
        if (l == u) {
            return c[l] == '1' ? v1 : v2;
        }
        int n = c.length;
        int overall1 = ones[u] - (l == 0 ? 0 : ones[l - 1]);
        long way1 = 0;
        int len = u - l + 1;
        if (overall1 > 0) {
            way1 = overall1 * v1 * len;
        } else {
            way1 = v2;
        }
        long way2 = Long.MAX_VALUE;
        if (len % 2 == 0) {
            int mid = l + (u - l) / 2;
            long h1 = solve(c, l, mid, v1, v2);
            long h2 = solve(c, mid + 1, u, v1, v2);
            way2 = h1 + h2;
        }
        long res = Math.min(way1, way2);
        return res;
    }
}

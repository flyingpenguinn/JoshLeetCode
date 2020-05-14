public class MinScoreTriangulation {
    // l....u =numerate k in the middle to get lku and divide to l..k and k..,u
    int[][] dp;

    public int minScoreTriangulation(int[] a) {
        dp = new int[a.length][a.length];
        return dom(a, 0, a.length - 1);
    }

    int dom(int[] a, int l, int u) {
        if (u - l + 1 < 3) {
            return 0;
        }
        if (u - l + 1 == 3) {
            return a[l] * a[l + 1] * a[u];
        }
        if (dp[l][u] != 0) {
            return dp[l][u];
        }
        int min = Integer.MAX_VALUE;
        for (int k = l + 1; k <= u - 1; k++) {
            int left = dom(a, l, k);
            int right = dom(a, k, u);
            int p = a[l] * a[k] * a[u];
            int cur = left + right + p;
            min = Math.min(min, cur);
        }
        dp[l][u] = min;
        return min;
    }
}

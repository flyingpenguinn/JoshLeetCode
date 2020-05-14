public class StoneGameII {
    // m useless, directly use x
    int[][] dp;

    public int stoneGameII(int[] a) {
        int n = a.length;
        dp = new int[n][n];
        int[] right = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            right[i] = (i == n - 1 ? 0 : right[i + 1]) + a[i];
        }
        return dom(a, 0, 2, right);
    }

    int dom(int[] a, int s, int x, int[] right) {
        int n = a.length;
        if (s == n) {
            return 0;
        }
        if (s + x >= n) {
            x = n - s;
        }
        if (dp[s][x] != 0) {
            return dp[s][x];
        }
        int max = 0;
        int ss = 0;
        for (int i = 0; i < x && i + s < n; i++) {
            ss += a[i + s];
            int nx = Math.max(x, 2 * (i + 1));
            int later = dom(a, i + s + 1, nx, right);
            int rem = right[i + s + 1];
            int cap = rem - later + ss;
            //  System.out.println("s="+s+" i="+i+" "+" rem="+rem+" cap="+cap);
            max = Math.max(max, cap);
        }
        dp[s][x] = max;
        return max;
    }
}

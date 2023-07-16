public class MaxJumpsToReachLastIndex {
    // private int MAX = (int)(1e9);
    private Integer[] dp;

    public int maximumJumps(int[] a, int t) {
        dp = new Integer[a.length];
        int rt = solve(a, 0, t);
        return rt;
    }

    private int solve(int[] a, int i, int t) {
        int n = a.length;
        //    System.out.println("doing "+i);
        if (i == n - 1) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int res = -1;
        for (int j = i + 1; j < n; ++j) {
            if (Math.abs(a[j] - a[i]) <= t) {
                //     System.out.println(i+" going to "+j);
                int later = solve(a, j, t);
                if (later != -1) {
                    res = Math.max(res, 1 + later);
                }
            }
        }
        //  System.out.println(i+" "+res);
        dp[i] = res;
        return res;
    }
}

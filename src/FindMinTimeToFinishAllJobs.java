public class FindMinTimeToFinishAllJobs {
    // assign a subset to everyone, so complexity is O(n*3^n) if we use the lookup table to accelerate
    private int[] lookup;
    private Integer[][] dp;

    public int minimumTimeRequired(int[] a, int k) {
        int n = a.length;
        lookup = new int[1 << n];
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    lookup[i] += a[j];
                }
            }
        }
        dp = new Integer[k][1 << n];
        return domin(a, k, 0, (1 << n) - 1);
    }

    private int Max = 1000000000;

    private int domin(int[] a, int k, int i, int st) {
        int n = a.length;
        if (i == k) {
            return (st == 0) ? 0 : Max;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        int res = Max;
        for (int sub = st; sub > 0; sub = ((sub - 1) & st)) {
            int later = st - sub;
            int laterres = domin(a, k, i + 1, later);
            //    System.out.println(st+" "+sub+" "+later);
            int cur = lookup[sub];
            int curres = Math.max(cur, laterres);
            res = Math.min(res, curres);
        }
        dp[i][st] = res;
        return res;
    }
}

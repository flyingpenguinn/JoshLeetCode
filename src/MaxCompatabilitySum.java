public class MaxCompatabilitySum {
    private Integer[][] dp;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int n = students.length;
        dp = new Integer[n][1 << n];
        return dfs(0, 0, students, mentors);
    }

    private int dfs(int i, int st, int[][] stu, int[][] men) {
        int n = stu.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (((st >> j) & 1) != 1) {
                int cur = score(stu[i], men[j]);
                int nst = st | (1 << j);
                cur += dfs(i + 1, nst, stu, men);
                res = Math.max(res, cur);
            }
        }
        dp[i][st] = res;
        return res;
    }

    private int score(int[] stu, int[] men) {
        int res = 0;
        int m = stu.length;
        for (int i = 0; i < m; i++) {
            if (stu[i] == men[i]) {
                res++;
            }
        }
        return res;
    }
}

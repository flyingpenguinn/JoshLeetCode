import java.util.Arrays;

public class MaxNumberOfNonoverlappingPalindromeStrings {
    private int[] dp;

    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[][] palin = new int[n][n];
        dp = new int[n];
        Arrays.fill(dp, -1);
        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;
                if (len == 1) {
                    palin[i][j] = 1;
                } else if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        palin[i][j] = 1;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        palin[i][j] = palin[i + 1][j - 1];
                    }
                }
            }
        }
        //   System.out.println(Arrays.deepToString(palin));
        return solve(s, 0, k, palin);
    }

    private int solve(String s, int i, int k, int[][] palin) {
        int n = s.length();

        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int res = solve(s, i + 1, k, palin);
        for (int j = i + k - 1; j < n; ++j) {
            if (palin[i][j] == 1) {
                //    System.out.println(i+".."+j);
                int cur = 1 + solve(s, j + 1, k, palin);
                res = Math.max(res, cur);
            }
        }
        dp[i] = res;
        return res;
    }
}

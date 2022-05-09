public class CountNumberOfTexts {
    private Long[] dp;
    private long mod = (long) (1e9 + 7);

    public int countTexts(String s) {
        StringBuilder sb = new StringBuilder();

        dp = new Long[s.length()];
        long rt = solve(s.toCharArray(), 0);
        return (int) rt;
    }

    private long solve(char[] s, int i) {
        int n = s.length;
        if (i == n) {
            return 1L;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        long way1 = solve(s, i + 1);
        long way2 = 0;
        long way3 = 0;
        long way4 = 0;
        if (i + 1 < n && s[i + 1] == s[i]) {
            way2 = solve(s, i + 2);
        }
        if (i + 2 < n && s[i] == s[i + 1] && s[i + 1] == s[i + 2]) {
            way3 = solve(s, i + 3);
        }
        if (i + 3 < n && ((s[i] == '7') || (s[i] == '9')) && s[i] == s[i + 1] && s[i + 1] == s[i + 2] && s[i + 2] == s[i + 3]) {
            way4 = solve(s, i + 4);
        }
        long rt = way1 + way2 + way3 + way4;
        rt %= mod;
        dp[i] = rt;
        return rt;
    }
}

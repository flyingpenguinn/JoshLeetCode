public class EqualizeStringsByAddingOrRemovingAtBothEnds {
    public int minOperations(String is, String it) {
        char[] s = is.toCharArray();
        char[] t = it.toCharArray();
        int sn = s.length;
        int tn = t.length;
        int[][] dp = new int[sn][tn];
        int res = 0;
        for (int i = 0; i < sn; ++i) {
            for (int j = 0; j < tn; ++j) {
                if (s[i] == t[j]) {
                    dp[i][j] = ((i == 0 || j == 0) ? 0 : (dp[i - 1][j - 1])) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return sn - res + tn - res;
    }
}

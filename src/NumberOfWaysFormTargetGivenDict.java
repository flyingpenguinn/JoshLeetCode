public class NumberOfWaysFormTargetGivenDict {
    private Long[][] dp;

    public int numWays(String[] ws, String t) {

        int maxw = 0;
        for (String w : ws) {
            maxw = Math.max(maxw, w.length());
        }
        int[][] m = new int[26][maxw];
        for (String w : ws) {
            for (int i = 0; i < w.length(); i++) {
                char wc = w.charAt(i);
                m[wc - 'a'][i]++;

            }
        }
        //   System.out.println(Arrays.deepToString(m));
        dp = new Long[t.length()][maxw + 1];
        long rt = doways(m, t, 0, 0, maxw);
        return (int) rt;
    }

    private long doways(int[][] m, String t, int i, int k, int maxw) {
        if (i == t.length()) {
            return 1;
        }
        if (k == maxw) {
            return 0;
        }
        if (dp[i][k] != null) {
            return dp[i][k];
        }
        long nopick = doways(m, t, i, k + 1, maxw);
        long pick = 0;
        char tc = t.charAt(i);
        int curpick = m[tc - 'a'][k];
        if (curpick > 0) {
            pick = curpick * doways(m, t, i + 1, k + 1, maxw);
            pick %= mod;
        }
        long rt = pick + nopick;
        rt %= mod;
        dp[i][k] = rt;
        return rt;

    }

    private long mod = 1000000007;
}

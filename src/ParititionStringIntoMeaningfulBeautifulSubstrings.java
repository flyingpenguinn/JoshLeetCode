public class ParititionStringIntoMeaningfulBeautifulSubstrings {

    private int MAX = (int) (1e9);
    private Integer[] dp;

    public int minimumBeautifulSubstrings(String s) {

        dp = new Integer[s.length()];
        int rt = solve(s.toCharArray(), 0);
        return rt >= MAX ? -1 : rt;
    }

    private int solve(char[] s, int i) {
        int n = s.length;
        if (i == n) {
            return 0;
        }
        // System.out.println("doing "+i);
        if (s[i] == '0') {
            return MAX;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int cur = 0;
        int res = MAX;
        for (int j = i; j < n; ++j) {
            cur = cur * 2 + (s[j] - '0');
            //   System.out.println("cur = "+cur);
            if (pow5(cur)) {
                //    System.out.println("redo");
                int later = 1 + solve(s, j + 1);
                res = Math.min(res, later);
            }
        }
        dp[i] = res;
        return res;
    }

    private boolean pow5(int n) {
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}

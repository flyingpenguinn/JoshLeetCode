import java.util.Arrays;

public class MinAsciiDeleteSum {
    // just like edit distance
    int[][] dp;

    public int minimumDeleteSum(String s1, String s2) {

        dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(s1, s2, 0, 0);
    }


    int dom(String s1, String s2, int i, int j) {
        if (i == s1.length()) {
            int sum = 0;
            while (j < s2.length()) {
                sum += (int) s2.charAt(j++);
            }
            return sum;
        }
        if (j == s2.length()) {
            int sum = 0;
            while (i < s1.length()) {
                sum += (int) s1.charAt(i++);
            }
            return sum;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return dom(s1, s2, i + 1, j + 1);
        } else {
            int c1 = (int) s1.charAt(i);
            int d1 = c1 + dom(s1, s2, i + 1, j);
            int c2 = (int) s2.charAt(j);
            int d2 = c2 + dom(s1, s2, i, j + 1);
            int rt = Math.min(d1, d2);
            dp[i][j] = rt;
            return rt;
        }
    }
}

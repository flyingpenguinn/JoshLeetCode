import java.util.Arrays;

public class StringCompressionII {

    /*
    We define the state dp[i][k]: the minimum length for s.substring(0, i+1) with at most k deletion.
For each char s[i], we can either keep it or delete it.
If delete, dp[i][j]=dp[i-1][j-1].
If keep, we delete at most j chars in s.substring(0, i+1) that are different from s[i].
     */
    private int Max = 99999;

    public int getLengthOfOptimalCompression(String s, int m) {
        int n = s.length();
        int[][] dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Max);
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j == 0 ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                int count = 0;
                int curdelete = 0;
                for (int k = i; k >= 0; k--) {
                    if (s.charAt(i) == s.charAt(k)) {
                        count++;
                    } else {
                        curdelete++;
                    }
                    if (j >= curdelete) {
                        dp[i][j] = Math.min(dp[i][j], (k == 0 ? 0 : (dp[k - 1][j - curdelete])) + 1 + digits(count));
                    }
                }
                if (j >= 1) {
                    dp[i][j] = Math.min(dp[i][j], i == 0 ? 0 : dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n - 1][m];
    }

    private int digits(int count) {
        if (count >= 100) {
            return 3;
        } else if (count >= 10) {
            return 2;
        } else if (count >= 2) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new StringCompressionII().getLengthOfOptimalCompression("ab", 1));
    }

}

class StringCompressionIIMine {

    private int[][][][] dp;

    public int getLengthOfOptimalCompression(String s, int ks) {
        int n = s.length();
        dp = new int[n][27][n][ks + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        return domin(0, 26, 0, ks, s);
    }


    // min length if we start from i, last char is last and
    // we have count pending chars in it, with k chances to delete remaining
    private int domin(int i, int last, int count, int k, String s) {
        if (i == s.length()) {
            return countLen(count);
        }
        if (dp[i][last][count][k] != -1) {
            return dp[i][last][count][k];
        }
        char c = s.charAt(i);
        int cind = c - 'a';
        int keep = 0;
        if (cind == last) {
            keep = domin(i + 1, last, count + 1, k, s);
        } else {
            keep = countLen(count) + domin(i + 1, cind, 1, k, s);
        }
        int remove = Integer.MAX_VALUE;
        if (k > 0) {
            remove = domin(i + 1, last, count, k - 1, s);
        }
        dp[i][last][count][k] = Math.min(keep, remove);
        return dp[i][last][count][k];
    }

    private int countLen(int count) {
        if (count == 0) {
            return 0;
        }
        if (count == 1) {
            return 1;
        }
        return (1 + String.valueOf(count).length());
    }
}
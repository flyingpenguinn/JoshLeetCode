import java.util.Arrays;

public class StringCompressionII {

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
        return (1 + digits(count));
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
}
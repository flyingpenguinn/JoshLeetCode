import java.util.Arrays;

public class FlipStringToMonoIncrease {
    public int minFlipsMonoIncr(String s) {
        // can be improved to O1 space as we only depend on i+1
        dp = new int[s.length()][2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return domin(s, 0, 0);
    }

    int Max = 1000000;
    int[][] dp;

    int domin(String s, int i, int pre) {
        int n = s.length();
        if (i == n) {
            return 0;
        }
        if (dp[i][pre] != -1) {
            return dp[i][pre];
        }
        int cur = s.charAt(i) - '0';
        int flip = Max;
        int nof = Max;
        if (pre == 0) {
            if (cur == 0) {
                flip = domin(s, i + 1, 1) + 1;
                nof = domin(s, i + 1, 0);
            } else {
                flip = domin(s, i + 1, 0) + 1;
                nof = domin(s, i + 1, 1);
            }
        } else {
            if (cur == 0) {
                flip = domin(s, i + 1, 1) + 1;
            } else {
                nof = domin(s, i + 1, 1);
            }
        }
        int rt = Math.min(flip, nof);
        dp[i][pre] = rt;
        return rt;
    }
}

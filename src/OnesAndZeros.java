import java.util.Arrays;

/*
LC#474
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:

The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.


Example 1:

Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”


Example 2:

Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".l
 */
public class OnesAndZeros {
    // classical knapsack
    int[][][] dp;
    int[][] map;

    public int findMaxForm(String[] ss, int m, int n) {
        int sn = ss.length;
        dp = new int[sn + 1][m + 1][n + 1];
        map = new int[sn + 1][2];
        for (int i = 0; i < sn; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
            int zc = 0;
            int oc = 0;
            for (int j = 0; j < ss[i].length(); j++) {
                if (ss[i].charAt(j) == '0') {
                    zc++;
                } else {
                    oc++;
                }
            }
            map[i][0] = zc;
            map[i][1] = oc;
        }
        return dof(ss, 0, m, n);
    }

    int dof(String[] ss, int i, int m, int n) {
        if (i == ss.length) {
            return 0;
        }
        if (dp[i][m][n] != -1) {
            return dp[i][m][n];
        }
        int pick = -1;
        if (m - map[i][0] >= 0 && n - map[i][1] >= 0) {
            pick = 1 + dof(ss, i + 1, m - map[i][0], n - map[i][1]);
        }
        int nopick = dof(ss, i + 1, m, n);
        int max = Math.max(pick, nopick);
        dp[i][m][n] = max;
        return max;
    }
}

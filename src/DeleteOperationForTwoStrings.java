import java.util.Arrays;

/*
LC#583
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
 */
public class DeleteOperationForTwoStrings {
    // basically edit distance...
    int[][] dp;

    public int minDistance(String w1, String w2) {
        char[] s = w1.toCharArray();
        char[] t = w2.toCharArray();
        dp = new int[s.length][t.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(s, t, 0, 0);
    }


    int dom(char[] s, char[] t, int i, int j) {
        if (i == s.length) {
            return t.length - j;
        }
        if (j == t.length) {
            return s.length - i;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (s[i] == t[j]) {
            dp[i][j] = dom(s, t, i + 1, j + 1);
        } else {
            int d1 = 1 + dom(s, t, i + 1, j);
            int d2 = 1 + dom(s, t, i, j + 1);
            dp[i][j] = Math.min(d1, d2);
        }
        return dp[i][j];
    }
}

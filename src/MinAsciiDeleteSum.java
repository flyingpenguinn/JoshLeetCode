import java.util.Arrays;

/*
LC#712
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].
 */
public class MinAsciiDeleteSum {
    // just like edit distance
    int[][] dp;

    public int minimumDeleteSum(String s1, String s2) {
        dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(s1, s2, 0, 0);
    }

    int dom(String s, String t, int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int rt = 0;
        if (i == s.length() && j == t.length()) {
            rt = 0;
        } else if (i == s.length()) {
            rt = (int) (t.charAt(j)) + dom(s, t, i, j + 1);
        } else if (j == t.length()) {
            rt = (int) (s.charAt(i)) + dom(s, t, i + 1, j);
        } else {
            if (s.charAt(i) == t.charAt(j)) {
                rt = dom(s, t, i + 1, j + 1);
            } else {
                int way1 = (int) (s.charAt(i)) + dom(s, t, i + 1, j);
                int way2 = (int) (t.charAt(j)) + dom(s, t, i, j + 1);
                rt = Math.min(way1, way2);
            }
        }
        dp[i][j] = rt;
        return rt;
    }
}

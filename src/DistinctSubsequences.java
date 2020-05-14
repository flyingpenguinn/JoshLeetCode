import java.util.Arrays;

/*
LC#115
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
 */
public class DistinctSubsequences {
    int[][] dp;

    public int numDistinct(String s, String t) {
        dp = new int[s.length()][t.length()];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return don(s, 0, t, 0);
    }

    // pick or not pick si
    int don(String s, int si, String t, int ti) {
        if (ti == t.length()) {
            return 1;
        }
        if (si == s.length()) {
            return 0;
        }
        if (dp[si][ti] != -1) {
            return dp[si][ti];
        }
        int wo = don(s, si + 1, t, ti);
        int with = 0;
        if (s.charAt(si) == t.charAt(ti)) {
            with = don(s, si + 1, t, ti + 1);
        }
        dp[si][ti] = wo + with;
        return dp[si][ti];
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinct("aaa", "aa"));
    }
}

class DistinctSubsequencesBottomUp {
    // if si == tj, then i has contribution, +1 to the old match number
    // otherwise no contribution just keep the previous match numbers on
    public int numDistinct(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int[][] dp = new int[sn][tn];
        for (int i = 0; i < sn; i++) {
            dp[i][0] = (i > 0 ? dp[i - 1][0] : 0);
            if (s.charAt(i) == t.charAt(0)) {
                dp[i][0]++;
            }
        }
        // dp[0][j]==0 for any j>0
        for (int i = 1; i < sn; i++) {
            for (int j = 1; j < tn; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[sn - 1][tn - 1];
    }

}

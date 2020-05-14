/*
LC#44
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */

public class WildcardMatching {
    int[][] dp;

    public boolean isMatch(String s, String p) {
        dp = new int[s.length() + 2][p.length() + 1];
        return dom(s, p, 0, 0);
    }

    boolean same(String s, String p, int i, int j) {
        return i < s.length() && j < p.length() && (s.charAt(i) == '?' || p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
    }

    boolean hitAndRun(int[][] dp, int i, int j, boolean r) {
        dp[i][j] = r ? 1 : 2;
        return r;
    }

    boolean dom(String s, String p, int i, int j) {
        int sn = s.length();
        int pn = p.length();
        if (i == sn && j == pn) {
            return true;
        }
        // handle i==sn below with * i may >sn
        if (i > sn || j == pn) {
            return false;
        }
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        char pc = p.charAt(j);
        if (pc == '*') {
            // match 0
            // or match one. note not moving j to allow matching multiple in s,no need to loop on i. @SILU this is to allow zero or more
            boolean rt = dom(s, p, i, j + 1) || dom(s, p, i + 1, j);
            return hitAndRun(dp, i, j, rt);
        } else {
            if (same(s, p, i, j)) {
                boolean rt = dom(s, p, i + 1, j + 1);
                return hitAndRun(dp, i, j, rt);
            }
        }
        return hitAndRun(dp, i, j, false);
    }
}

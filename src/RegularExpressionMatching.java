/*
LC#10
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

public class RegularExpressionMatching {
    // note s has no pattern! only need to deal with * in p
    private static int[][] dp;

    public boolean isMatch(String s, String t) {
        dp = new int[s.length() + 1][t.length() + 1];
        return domatch(s, t, 0, 0);
    }

    private boolean domatch(String s, String t, int i, int j) {
        int sn = s.length();
        int tn = t.length();
        if (i == sn && j == tn) {
            return true;
        }
        // allow s to be empty to match empty with b*
        if (i > sn || j == tn) {
            return false;
        }
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        char nt = j + 1 < tn ? t.charAt(j + 1) : 0;
        if (nt == '*') {
            // match zero, or one. similar to WildcardMatching dont need a loop here
            boolean rt = domatch(s, t, i, j + 2) || (same(s, t, i, j) && domatch(s, t, i + 1, j));
            return cache(i, j, rt);
        } else {
            boolean rt = (same(s, t, i, j) && domatch(s, t, i + 1, j + 1));
            return cache(i, j, rt);
        }
    }

    private boolean same(String s, String t, int i, int j) {
        return i < s.length() && j < t.length() && (s.charAt(i) == '.' || t.charAt(j) == '.' || s.charAt(i) == t.charAt(j));
    }

    private boolean cache(int i, int j, boolean r) {
        dp[i][j] = r == true ? 1 : 2;
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching().isMatch("aa", "a*"));
        System.out.println(new RegularExpressionMatching().isMatch("a", "ab*"));
    }
}

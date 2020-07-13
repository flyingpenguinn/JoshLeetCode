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
    // same as regular exp: * can match zero first. then if allows, match one.
    // in regular exp, it would have to match the preceding char, but here it can match anything
    public boolean isMatch(String s, String p) {
        // check null
        Boolean[][] dp = new Boolean[s.length() + 1][p.length()];
        return match(s, 0, p, 0, dp);
    }

    private Boolean match(String s, int i, String p, int j, Boolean[][] dp) {
        if (i == s.length() && j == p.length()) {
            return true;
        } else if (j == p.length()) {
            return false;
        } else if (i > s.length()) {
            return false;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (p.charAt(j) == '*') {
            boolean zero = match(s, i, p, j + 1, dp); // * can allow us to match zero chars: a vs *, we move *
            if (zero) {
                dp[i][j] = true;
            } else {
                dp[i][j] = match(s, i + 1, p, j, dp); // match one: a vs * note we can match more in i+1
            }
        } else {
            if (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                dp[i][j] = match(s, i + 1, p, j + 1, dp);
            } else {
                dp[i][j] = false;
            }
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        //    System.out.println(new WildcardMatching().isMatch("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));
        System.out.println(new WildcardMatching().isMatch("ab", "?*"));
    }
}

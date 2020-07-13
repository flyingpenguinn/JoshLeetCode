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
    public boolean isMatch(String s, String p) {
        // check null etc
        Boolean[][] dp = new Boolean[s.length()+1][p.length()];
        return match(s, 0, p, 0, dp);
    }

    private Boolean match(String s, int i, String p, int j, Boolean[][] dp){
        if(i==s.length() && j==p.length()){
            return true;
        }else if (j==p.length()){
            return false;
        }else if (i>s.length()){
            return false;
        }
        if(dp[i][j]!= null){
            return dp[i][j];
        }
        if(j+1<p.length() && p.charAt(j+1)=='*'){
            // j always points to a non * char, here we get a* in p
            boolean zero = match(s, i, p, j+2, dp); // b in s. zero match
            if(zero){
                dp[i][j] = true;
            }
            else if(same(s, i, p, j)){   // a* vs a. we match one here but later can match more in i+1
                dp[i][j] = match(s, i+1, p, j, dp);
            }else{
                dp[i][j] = false;  // a* vs b but that can't match so return false
            }
        }else{
            if(same(s, i, p, j)){   // .or a vs a without *, can only move one step
                dp[i][j] = match(s, i+1, p, j+1, dp);
            }else{
                dp[i][j] = false;
            }
        }
        return dp[i][j];
    }

    private boolean same(String s, int i, String p, int j){
        return i<s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j)=='.');
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching().isMatch("aa", "a*"));
        System.out.println(new RegularExpressionMatching().isMatch("a", "ab*"));
    }
}

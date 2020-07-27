import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#87
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s1.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int n = s1.length();
        Boolean[][][] dp = new Boolean[n][n][n + 1];
        return doScramble(s1.toCharArray(), 0, s2.toCharArray(), 0, n, dp);
    }

    private boolean doScramble(char[] s1, int l1, char[] s2, int l2, int len, Boolean[][][] dp) {
        if (len == 0) {
            return true;
        }
        if (len == 1) {
            return s1[l1] == s2[l2];
        }
        if (dp[l1][l2][len] != null) {
            return dp[l1][l2][len];
        }

        int[] cm1 = new int[26];
        int[] cm2 = new int[26];
        for (int i = 1; i <= len; i++) {
            cm1[s1[l1 + i - 1] - 'a']++;
            cm2[s2[l2 + i - 1] - 'a']++;
        }
        // not having same charset, return false
        if (!Arrays.equals(cm1, cm2)) {
            dp[l1][l2][len] = false;
            return false;
        }
        for (int left = 1; left < len; left++) {
            if (doScramble(s1, l1, s2, l2, left, dp) && doScramble(s1, l1 + left, s2, l2 + left, len - left, dp)) {
                dp[l1][l2][len] = true;
                return true;
            }
        }
        int u2 = l2 + len - 1;
        for (int leftlen = 1; leftlen < len; leftlen++) {
            if (doScramble(s1, l1, s2, u2 - leftlen + 1, leftlen, dp) && doScramble(s1, l1 + leftlen, s2, l2, len - leftlen, dp)) {
                dp[l1][l2][len] = true;
                return true;
            }
        }
        dp[l1][l2][len] = false;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ScrambleString().isScramble("great", "rgeat"));
        System.out.println(new ScrambleString().isScramble("great", "rgtae"));
        System.out.println(new ScrambleString().isScramble("abcdefghijklmnopq", "efghijklmnopqcadb"));
    }
}

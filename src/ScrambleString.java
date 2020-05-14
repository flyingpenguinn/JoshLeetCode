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
    int[][][] dp;

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        dp = new int[n][n][n + 1];
        return dos(s1, s2, 0, 0, s1.length());
    }

    boolean dos(String s1, String s2, int p1, int p2, int len) {
        if (len == 1) {
            return s1.charAt(p1) == s2.charAt(p2);
        }
        if (dp[p1][p2][len] != 0) {
            return dp[p1][p2][len] == 1;
        }

        for (int left = 1; left <= len - 1; left++) {
            int right = len - left;
            // xxyyy vs aabbb
            if (dos(s1, s2, p1, p2, left) && dos(s1, s2, p1 + left, p2 + left, right)) {
                dp[p1][p2][len] = 1;
                return true;
            }
            // xxyyy vs bbbaa
            if (dos(s1, s2, p1, p2 + right, left) && dos(s1, s2, p1 + left, p2, right)) {
                dp[p1][p2][len] = 1;

                return true;
            }
        }
        dp[p1][p2][len] = 2;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ScrambleString().isScramble("great", "rgeat"));
        System.out.println(new ScrambleString().isScramble("great", "rgtae"));
        System.out.println(new ScrambleString().isScramble("abcdefghijklmnopq", "efghijklmnopqcadb"));
    }
}

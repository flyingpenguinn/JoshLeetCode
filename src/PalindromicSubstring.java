/*
LC#647
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".


Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Note:

The input string length won't exceed 1000.
 */
public class PalindromicSubstring {
    // extend from center
    int r = 0;

    public int countSubstrings(String s) {
        int r = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            calc(s, n, i, i);
            calc(s, n, i, i + 1);
        }
        return r;
    }

    private void calc(String s, int n, int j, int k) {
        while (j >= 0 && k < n) {
            if (s.charAt(j) == s.charAt(k)) {
                r++;
                j--;
                k++;
            } else {
                break;
            }
        }
    }
}

class PalindromicSubstringDp {
    // compare with subsequence...
    public int countSubstrings(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int r = 0;
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 0;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }
                r += dp[i][j];
            }
        }
        return r;
    }
}

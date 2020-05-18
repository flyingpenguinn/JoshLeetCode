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
        int n = s.length();
        for (int i = 0; i < n; i++) {
            calc(s, n, i, i);
            if (i + 1 < n) {
                calc(s, n, i, i + 1);
            }
        }
        return r;
    }

    private void calc(String s, int n, int j, int k) {
        while (j >= 0 && k < n) {
            if (s.charAt(j--) == s.charAt(k++)) {
                r++;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new PalindromicSubstring().countSubstrings("aaa"));
    }
}

class PalindromicSubstringDp {
    // compare with subsequence...
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int r = 0;
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = true;
                    r++;
                } else if (len == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    r++;
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        r++;
                    }
                }
            }
        }
        return r;
    }
}

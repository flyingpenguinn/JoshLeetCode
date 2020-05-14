import java.util.Arrays;

/*
LC#1278
You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.



Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
Example 2:

Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
Example 3:

Input: s = "leetcode", k = 8
Output: 0


Constraints:

1 <= k <= s.length <= 100.
s only contains lowercase English letters.
 */
public class PalindromePartitionIII {
    // just like #132 calc p outside of the dp as it's sth static and unchanged
    int[][] dp;
    int[][] p;

    public int palindromePartition(String str, int k) {
        int n = str.length();
        dp = new int[n][k + 1];
        p = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (i == j) {
                    p[i][j] = 0;
                } else if (j == i + 1) {
                    p[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
                } else {
                    p[i][j] = p[i + 1][j - 1] + (str.charAt(i) == str.charAt(j) ? 0 : 1);
                }
            }
        }
        char[] s = str.toCharArray();
        return dom(s, 0, k);
    }

    int Max = 1000000;

    private int dom(char[] s, int i, int k) {
        if (i == s.length) {
            if (k != 0) {
                return Max;
            } else {
                return 0;
            }
        }
        if (k <= 0) {
            return Max;
        }
        if (dp[i][k] != -1) {
            return dp[i][k];
        }
        int min = Max;
        for (int j = i; j < s.length; j++) {
            int changes = p[i][j];
            int later = dom(s, j + 1, k - 1);
            min = Math.min(min, changes + later);
        }
        dp[i][k] = min;
        return min;
    }


    public static void main(String[] args) {
        System.out.println(new PalindromePartitionIII().palindromePartition("abc", 2));
    }
}

import java.util.*;

/*
LC#730
Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

A subsequence of a string S is obtained by deleting 0 or more characters from S.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:
Input:
S = 'bccb'
Output: 6
Explanation:
The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.
Example 2:
Input:
S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
Output: 104860361
Explanation:
There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
Note:

The length of S will be in the range [1, 1000].
Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 */
public class CountDistinctPalindromeSubsequence {
    // Let dp(i, j) be the answer for the string T = S[i:j+1] including the empty sequence. The answer is the number of unique characters in T,
    // plus palindromes of the form "a_a", "b_b", "c_c", and "d_d", where "_" represents zero or more characters.
    // note we count empty str so -1 in the end
    // O(4*n^2)
    int Mod = 1000000007;
    int[][] dp;
    int[][] left; // nearest char j on the left of i
    int[][] right;// on the right of i

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        left = new int[n][4];
        right = new int[n][4];
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            for (int j = 0; j < 4; j++) {
                int lbf = i == 0 ? -1 : left[i - 1][j];
                if (j == cind) {
                    left[i][j] = i;
                } else {
                    left[i][j] = lbf;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int cind = s.charAt(i) - 'a';
            for (int j = 0; j < 4; j++) {
                int rbf = i == n - 1 ? n : right[i + 1][j];
                if (j == cind) {
                    right[i][j] = i;
                } else {
                    right[i][j] = rbf;
                }
            }
        }
        dp = new int[n][n];
        return doc(s.toCharArray(), 0, n - 1) - 1;
    }

    int doc(char[] s, int l, int u) {
        if (l > u) {
            return 1;//counting empty
        }
        if (l == u) {
            return 2;
        }
        if (dp[l][u] > 0) {
            return dp[l][u];
        }

        long r = 1L;//counting empty
        for (int i = 0; i < 4; i++) {
            if (right[l][i] <= u) {
                r++;
            }
            int j = right[l][i];
            int k = left[u][i];
            int add = 0;
            if (j < k) {
                add = doc(s, j + 1, k - 1);
                r += add;
                r %= Mod;
            }
        }
        dp[l][u] = (int) r;
        return dp[l][u];
    }

    public static void main(String[] args) {
        System.out.println(new CountDistinctPalindromeSubsequence().countPalindromicSubsequences("bddaabdbbccdcdcbbdbddccbaaccabbcacbadbdadbccddccdbdbdbdabdbddcccadddaaddbcbcbabdcaccaacabdbdaccbaacc"));
    }
}

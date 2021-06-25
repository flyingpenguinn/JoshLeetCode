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
public class CountDifferentPalindromeSubsequence {
    // the palins between i and j is the sum of those end in a and b and c and d, and use the outmost a, b,c,d...
    // axxxxxa the number of UNIQUE palin here is the number of palin in xxxx plus a and aa. if there is an aaa inside, then we can form aaa outside too
    // despite the inner whiles, still o(n^2) because we dont process those ni, nj
    private long[][] dp;
    private long mod = 1000000007;

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return (int) solve(0, n - 1, s);
    }


    private long solve(int i, int j, String s) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int res = 0;
        for (char c = 'a'; c <= 'd'; c++) {
            int ni = i;
            int nj = j;
            while (ni <= nj && s.charAt(ni) != c) {
                ni++;
            }
            while (ni <= nj && s.charAt(nj) != c) {
                nj--;
            }
            if (ni > nj) {
                continue;
            } else if (ni == nj) {
                // just single ones contribute 1 point
                res += 1;
            } else {
                long later = solve(ni + 1, nj - 1, s);
                res += later + 2; // count in a and aa. we are "elevating" internal ones (if any) with one more a and aa so we need to compensate
            }
            res %= mod;
        }
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountDifferentPalindromeSubsequence().countPalindromicSubsequences("bccb"));
    }
}

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
    // Let dp(i, j) be the answer for the string T = S[i][j] including the empty sequence. The answer is the number of unique characters in T,
    // plus palindromes of the form "a_a", "b_b", "c_c", and "d_d", where "_" represents zero or more characters.
    // note the gists are
    // 1. separate single digits and a_a
    // 2. always count in empty string until we - it in the end
    // note we count empty str so -1 in the end
    // can use left/right array to accelerate the j/k look up
    // O(4*n^2)

    long[][] dp;

    public int countPalindromicSubsequences(String s) {
        int n = s.length();

        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        long rt = doc(0, n - 1, s);
        rt = (rt - 1) % Mod; // - empty string in the end
        return (int) rt;
    }


    private long doc(int l, int u, String s) {
        if (l > u) {
            return 1;
        }
        if (dp[l][u] != -1) {
            return dp[l][u];
        }
        Set<Character> set = new HashSet<>();
        for (int i = l; i <= u; i++) {
            set.add(s.charAt(i));
            if (set.size() == 4) {
                break;
            }
        }
        long rt = 1 + set.size();
        for (int i = 0; i < 4; i++) {
            int j = l;
            while (j <= u && s.charAt(j) - 'a' != i) {
                j++;
            }
            int k = u;
            while (k >= j && s.charAt(k) - 'a' != i) {
                k--;
            }
            if (j < k) {
                // <, we dont process single digits
                long inner = doc(j + 1, k - 1, s);
                rt += inner;
                // inner bccb, bbb, b b + the outside single b
            }
        }
        dp[l][u] = rt % Mod;
        return rt;
    }

    long Mod = 1000000007;

    public static void main(String[] args) {
        System.out.println(new CountDifferentPalindromeSubsequence().countPalindromicSubsequences("bccb"));
    }
}

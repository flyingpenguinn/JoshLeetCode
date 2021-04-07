import java.util.Arrays;

/*
LC#1397
Given the strings s1 and s2 of size n, and the string evil. Return the number of good strings.

A good string has size n, it is alphabetically greater than or equal to s1, it is alphabetically smaller than or equal to s2, and it does not contain the string evil as a substring. Since the answer can be a huge number, return this modulo 10^9 + 7.



Example 1:

Input: n = 2, s1 = "aa", s2 = "da", evil = "b"
Output: 51
Explanation: There are 25 good strings starting with 'a': "aa","ac","ad",...,"az". Then there are 25 good strings starting with 'c': "ca","cc","cd",...,"cz" and finally there is one good string starting with 'd': "da".
Example 2:

Input: n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
Output: 0
Explanation: All strings greater than or equal to s1 and smaller than or equal to s2 start with the prefix "leet", therefore, there is not any good string.
Example 3:

Input: n = 2, s1 = "gx", s2 = "gz", evil = "x"
Output: 2


Constraints:

s1.length == n
s2.length == n
1 <= n <= 500
1 <= evil.length <= 50
All strings consist of lowercase English letters.
 */
public class FindAllGoodStrings {
    // dp + kmp
    // have to use kmp because if we get a mismatch with evil we need kmp to "slide" to the next evil position to match with
    private long mod = 1000000007;
    private Long[][][][] dp;

    public int findGoodStrings(int n, String s1, String s2, String e) {
        dp = new Long[n][2][2][e.length()];
        int[] nexts = nexts(e);
        int rt = (int) solve(n, s1, s2, e, 0, 0, 0, 0, nexts, "");
        return rt;
    }

    // i: cur index of the string we are creating
    // j: 0 or 1, already good for s1 or not
    // k: same for s2
    // t: index to matchin evil
    private long solve(int n, String s1, String s2, String e, int i, int j, int k, int t, int[] nexts, String cur) {

        if (t == e.length()) {
            return 0;
        }
        if (i == n) {
            return 1L;
        }
        if (dp[i][j][k][t] != null) {
            return dp[i][j][k][t];
        }
        char c1 = s1.charAt(i);
        char c2 = s2.charAt(i);
        long res = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int nj = j;
            int nk = k;
            int nt = t;
            // this part of sliding is almost the same as kmp
            while (nt > 0 && e.charAt(nt) != c) {
                nt = nexts[nt - 1];
            }
            if (e.charAt(nt) == c) {
                nt++;
            }
            if (j == 1 && k == 1) {
                // we can freely choose s1 and s2, no change
            } else if (j == 1) {
                // can freely choose s1, but not s2
                if (c < c2) {
                    nk = 1;
                } else if (c > c2) {
                    continue;
                }
            } else if (k == 1) {
                if (c > c1) {
                    nj = 1;
                } else if (c < c1) {
                    continue;
                }
            } else {
                if (c < c1 || c > c2) {
                    continue;
                }
                if (c < c2) {
                    nk = 1;
                }
                if (c > c1) {
                    nj = 1;
                }
            }
            res += solve(n, s1, s2, e, i + 1, nj, nk, nt, nexts, cur + c);
            res %= mod;
        }

        dp[i][j][k][t] = res;
        return res;
    }

    private int[] nexts(String s) {
        int n = s.length();
        int[] nexts = new int[n];
        nexts[0] = 0;// length of the longest prefix: 0...next[i] -1 == the prefix. the suffix ends at i
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            // abaaabab, macthing at the last b
            // when we mismatch with pos j, we try out next[j-1] it's a shorter prefix but may give new hope
            int j = nexts[i - 1];
            while (j > 0 && c != s.charAt(j)) {
                j = nexts[j - 1];
            }
            // if j==0 give a chance to match s[0]
            if (c == s.charAt(j)) {
                // if j==0 and equal, j would be 1
                nexts[i] = j + 1;
            }
        }
        return nexts;
    }
}

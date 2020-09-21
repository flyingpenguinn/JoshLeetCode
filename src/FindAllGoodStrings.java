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
    private Integer[][][][] dp;
    private int mod = 1000000007;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        dp = new Integer[n][2][2][evil.length()];
        int[] nexts = nexts(evil);

        return dof(n, s1, s2, 0, false, false, evil, 0, nexts);
    }

    private int dof(int n, String s1, String s2, int i, boolean fs1, boolean fs2, String evil, int j, int[] nexts) {
        if (j == evil.length()) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        int f1 = fs1 ? 1 : 0;
        int f2 = fs2 ? 1 : 0;
        if (dp[i][f1][f2][j] != null) {
            return dp[i][f1][f2][j];
        }
        long res = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            boolean nfs1 = fs1;
            boolean nfs2 = fs2;
            int pj = j;
            int nj = 0;
            // for example sdka vs ss. the 2nd s can match to 0, so next time we start from 1. so nj will =1
            // if it's ssd vs sss, then we should start from ssd vs ss[x], next to match is this x against d, so nj would be =2
            while (pj > 0 && c != evil.charAt(pj)) {
                pj = nexts[pj - 1];
            }
            if (c == evil.charAt(pj)) {
                nj = pj + 1;
            }
            boolean bad = false;
            if (fs1 && fs2) {
                // do nothing
            } else if (fs1 && c <= s2.charAt(i)) {
                nfs2 = c < s2.charAt(i);
            } else if (fs2 && c >= s1.charAt(i)) {
                nfs1 = c > s1.charAt(i);
            } else if (c >= s1.charAt(i) && c <= s2.charAt(i)) {
                nfs2 = c < s2.charAt(i);
                nfs1 = c > s1.charAt(i);
            } else {
                bad = true;
            }
            if (!bad) {
                res += dof(n, s1, s2, i + 1, nfs1, nfs2, evil, nj, nexts);
                res %= mod;
            }
        }
        dp[i][f1][f2][j] = (int) res;
        return dp[i][f1][f2][j];
    }

    // kmp next array
    private int[] nexts(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = 0;// length of the longest prefix: 0...next[i] -1 == the prefix. the suffix ends at i
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            // abaaabab, macthing at the last b
            // when we mismatch with pos j, we try out next[j-1] it's a shorter prefix but may give new hope
            int j = next[i - 1];
            while (j > 0 && c != s.charAt(j)) {
                j = next[j - 1];
            }
            // if j==0 give a chance to match s[0]
            if (c == s.charAt(j)) {
                next[i] = j + 1;
            }
        }
        return next;
    }
}

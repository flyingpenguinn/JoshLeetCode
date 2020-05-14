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
    private int Mod = 1000000007;
    int[][][][] dp;
    int[] nexts;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        dp = new int[s1.length() + 1][evil.length() + 1][2][2];
        nexts = nexts(evil);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        return dof(0, 0, 1, 1, n, s1, s2, evil);
    }

    private int dof(int i, int j, int eq1, int eq2, int n, String s1, String s2, String evil) {
        if (j == evil.length()) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (dp[i][j][eq1][eq2] != -1L) {
            return dp[i][j][eq1][eq2];
        }
        long r = 0L;
        for (int k = 0; k < 26; k++) {
            char c = (char) (k + 'a');
            int nj = j;
            // if 0..j in evial doesnt match what we have, there could be 0...p where p<j and match with current string
            // we use kmp to "slide" into that p position
            while (nj > 0 && evil.charAt(nj) != c) {
                nj = nexts[nj - 1];
            }
            nj = evil.charAt(nj) == c ? nj + 1 : nj;
            if (eq1 == 0 && eq2 == 0) {
                // >1 but <2, any can go
                r += dof(i + 1, nj, 0, 0, n, s1, s2, evil);
                r %= Mod;
            } else if (eq1 == 1 && eq2 == 1) {
                // ==1 and ==2. we may be able to change eq1 and eq2 but must folow the rule for 1 and 2
                if (c >= s1.charAt(i) && c <= s2.charAt(i)) {
                    int neq1 = c > s1.charAt(i) ? 0 : 1;
                    int neq2 = c < s2.charAt(i) ? 0 : 1;
                    r += dof(i + 1, nj, neq1, neq2, n, s1, s2, evil);
                    r %= Mod;
                }
            } else if (eq1 == 1 && eq2 == 0) {
                // ==1, but <2
                if (c >= s1.charAt(i)) {
                    int neq1 = c > s1.charAt(i) ? 0 : 1;
                    r += dof(i + 1, nj, neq1, 0, n, s1, s2, evil);
                    r %= Mod;
                }
            } else {
                // >1 but ==2
                if (c <= s2.charAt(i)) {
                    int neq2 = c < s2.charAt(i) ? 0 : 1;
                    r += dof(i + 1, nj, 0, neq2, n, s1, s2, evil);
                    r %= Mod;
                }
            }
        }
        dp[i][j][eq1][eq2] = (int) (r % Mod);
        return dp[i][j][eq1][eq2];
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

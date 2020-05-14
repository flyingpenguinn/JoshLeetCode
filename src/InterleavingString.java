/*
LC#97
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */
public class InterleavingString {
    // whether s3 of length i+j can be formed by interleaving i chars from s1 and j chars from s2
    // note we dont need another k for s3 since s3 index = s1 index + s2 index
    public boolean isInterleave(String s1, String s2, String s3) {

        int m = s1.length();
        int n = s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        // dpij: i...m-1 and j..n-1 can interleave
        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m && j == n) {
                    dp[i][j] = true;
                    continue;
                }
                boolean withi = i < m && s3.charAt(i + j) == s1.charAt(i) && dp[i + 1][j];
                boolean withj = j < n && s3.charAt(i + j) == s2.charAt(j) && dp[i][j + 1];
                dp[i][j] = withi || withj;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new InterleavingString().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }

}

class InterleavingStringMemoization {
    // no need k for pos in s3. k=i+j all the time
    int[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        dp = new int[s1.length() + 1][s2.length() + 1];
        return doi(s1, s2, s3, 0, 0);
    }

    boolean doi(String s1, String s2, String s3, int i, int j) {
        if (i == s1.length() && j == s2.length()) {
            return true;
        }
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        char c1 = i == s1.length() ? '*' : s1.charAt(i);
        char c2 = j == s2.length() ? '*' : s2.charAt(j);
        char c3 = s3.charAt(i + j);
        if (c3 == c1) {
            boolean rt = doi(s1, s2, s3, i + 1, j);
            if (rt) {
                dp[i][j] = 1;
                return true;
            }
        }
        // no else. could match both
        if (c3 == c2) {
            boolean rt = doi(s1, s2, s3, i, j + 1);
            if (rt) {
                dp[i][j] = 1;
                return true;
            }
        }
        dp[i][j] = 2;
        return false;
    }
}
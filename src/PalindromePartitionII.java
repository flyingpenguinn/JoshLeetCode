import java.util.Arrays;

/*
LC#132
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitionII {
    // pre calculate is palin!
    int[] dp = null;
    boolean[][] p = null;

    public int minCut(String s) {
        int n = s.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        p = new boolean[n][n];
        // in this way we get everything for n2 so that we dont need to call is palin in a n2 loop. note the n^# loop won't work
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (i == j) {
                    p[i][j] = true;
                } else if (j == i + 1) {
                    p[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    p[i][j] = s.charAt(i) == s.charAt(j) ? p[i + 1][j - 1] : false;
                }
            }
        }
        // cut = segments -1. what we returned is segments
        return doPartition(s, 0) - 1;
    }


    private int doPartition(String s, int i) {
        if (i == s.length()) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int min = Integer.MAX_VALUE;
        for (int j = i; j < s.length(); j++) {
            if (p[i][j]) {
                int cur = doPartition(s, j + 1);
                min = Math.min(min, 1 + cur);
            }
        }
        dp[i] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitionII().minCut("aabbc"));
    }
}

import java.util.Arrays;

/*
LC#1458
Given two arrays nums1 and nums2.

Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.

A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).



Example 1:

Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
Output: 18
Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
Their dot product is (2*3 + (-2)*(-6)) = 18.
Example 2:

Input: nums1 = [3,-2], nums2 = [2,-6,7]
Output: 21
Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
Their dot product is (3*7) = 21.
Example 3:

Input: nums1 = [-1,-1], nums2 = [1,1]
Output: -1
Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
Their dot product is -1.


Constraints:

1 <= nums1.length, nums2.length <= 500
-1000 <= nums1[i], nums2[i] <= 1000
 */
public class MaxDotProductBetweenSubsequences {
    // dpij means when we MUST pick one, the dot product. using 1...i and 1...j
    // we can opt not to pick anything before ij
    int Min = -10000000;

    public int maxDotProduct(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int[][] dp = new int[an + 1][bn + 1];
        // given we muts pick, 0 yields Min because there is nothing to pair with
        for (int i = 0; i <= an; i++) {
            dp[i][0] = Min;
        }
        for (int j = 0; j <= bn; j++) {
            dp[0][j] = Min;
        }
        for (int i = 1; i <= an; i++) {
            for (int j = 1; j <= bn; j++) {
                int nopick1 = dp[i - 1][j]; // we need i-1 or j-1 here because w are not picking so rely on them to pick
                int nopick2 = dp[i][j - 1];
                int cur = a[i - 1] * b[j - 1];// we can choose not to pick anything later because we picked here so no longer rely on them
                int pick = Math.max(dp[i - 1][j - 1] + cur, cur);
                dp[i][j] = Math.max(nopick1, Math.max(nopick2, pick));
            }
        }
        return dp[an][bn];
    }
}

class MaxDotProductExtension {
    // what if it wants to pick "at least k pairs"?
    // in the original problem when k == 0 it's 0 so an easy one.
    int Min = -10000000;

    public int maxDotProduct(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int picks = 1; // must do picks times
        int[][][] dp = new int[an + 1][bn + 1][picks + 1];

        // given we must pick >=picks items
        for (int i = 0; i <= an; i++) {
            for (int k = 1; k <= picks; k++) {
                dp[i][0][k] = Min;
            }
        }
        for (int j = 0; j <= bn; j++) {
            for (int k = 1; k <= picks; k++) {
                dp[0][j][k] = Min;
            }
        }
        for (int i = 1; i <= an; i++) {
            for (int j = 1; j <= bn; j++) {
                for (int k = 1; k <= picks; k++) {
                    int nopick1 = dp[i - 1][j][k];
                    int nopick2 = dp[i][j - 1][k];
                    // at least k times, so we have 2 choices: still pick k in i-1,j-1, or pick k-1 times there because we picked here
                    int pick = Math.max(dp[i - 1][j - 1][k - 1], dp[i - 1][j - 1][k]) + a[i - 1] * b[j - 1];
                    dp[i][j][k] = Math.max(nopick1, Math.max(nopick2, pick));
                }
            }
        }
        return dp[an][bn][1];
    }
}


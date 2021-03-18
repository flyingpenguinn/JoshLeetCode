import base.ArrayUtils;

import java.util.Arrays;

/*
LC#376
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Example 1:

Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.
Example 2:

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
Example 3:

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?
 */
public class WiggleSubsequence {
    // similar to "longest turbulent subarray"
    public int wiggleMaxLength(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][2];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for (int j = i + 1; j < n; j++) {
                if (a[i] < a[j]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                } else if (a[i] > a[j]) {
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
            }
            max = Math.max(max, dp[i][0]);
            max = Math.max(max, dp[i][1]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new WiggleSubsequence().wiggleMaxLength(ArrayUtils.read1d("[1,7,4,9,2,5]")));
    }
}

class WiggleSubsequenceOn {
    //  1,2,4-> use 4 to supercede 2
    // 2,1,0-> use 0 to supercede 1
    // 1,2,1-> good, keep the 1 look for > we can't do better than this
    // 2,1,2-> good, keep the 2 look for < we can't do better than this
// so we must select 0. then we diverge by +1 or -1, but we must pick the best +1 (the max) or best -1 (the min) and use this number as the next
    public int wiggleMaxLength(int[] a) {
        return Math.max(solve(a, 1), solve(a, -1));
    }

    private int solve(int[] a, int sign) {
        int n = a.length;
        int i = 0;
        int res = 1;
        while (i < n - 1) {
            int j = i + 1;
            if (sign == 1) {
                while (j < n && a[j] <= a[i]) {
                    j++;
                }
                while (j + 1 < n && a[j] <= a[j + 1]) {
                    j++;
                }
            } else {
                while (j < n && a[j] >= a[i]) {
                    j++;
                }
                while (j + 1 < n && a[j] >= a[j + 1]) {
                    j++;
                }
            }
            if (j < n) {
                res++;
            }
            i = j;
            sign = -sign;
        }
        return res;
    }
}

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
    //  count peaks and valleys. note if there is a consecutive peak or valley we are only counting that once
    public int wiggleMaxLength(int[] a){
        int n = a.length;
        int peaks = 1;
        int valleys = 1;

        for(int i=1; i<n; ++i){
            if(a[i]>a[i-1]){
                peaks = valleys + 1;
            }else if(a[i]<a[i-1]){
                valleys = peaks +1;
            }
        }
        int res = Math.max(peaks, valleys);
        return res;
    }
}

import base.ArrayUtils;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

/*
LC#1330
You are given an integer array nums. The value of this array is defined as the sum of |nums[i]-nums[i+1]| for all 0 <= i < nums.length-1.

You are allowed to select any subarray of the given array and reverse it. You can perform this operation only once.

Find maximum possible value of the final array.



Example 1:

Input: nums = [2,3,1,5,4]
Output: 10
Explanation: By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
Example 2:

Input: nums = [2,4,9,24,2,1,10]
Output: 68


Constraints:

1 <= nums.length <= 3*10^4
-10^5 <= nums[i] <= 10^5
 */
public class ReverseSubarrayToMaximize {
    /*

https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/discuss/489968/O(n)-Java-with-Mathematical-Proof

 */
    int Min = -1000000000;

    public int maxValueAfterReverse(int[] a) {
        int base = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            base += abs(a[i] - a[i + 1]);
        }
        int best = 0;
        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Min);
        }
        for (int i = 0; i + 1 < n; i++) {

            int aad = abs(a[i] - a[i + 1]);
            // |a-d |
            if (i - 2 >= 0) {
                // c<a, b<=a-2
                int v1 = a[i] - a[i + 1] - aad;
                // (a-d)- |a-d|
                int c1 = dp[i - 2][0];
                int v2 = -(a[i] + a[i + 1]) - aad;
                // -(a+d)-|a-d|
                int c2 = dp[i - 2][1];
                int v3 = a[i] + a[i + 1] - aad;
                // (a+d)- |a-d |
                int c3 = dp[i - 2][2];
                int v4 = -(a[i] - a[i + 1]) - aad;
                // -(a-d) - |a-d
                int c4 = dp[i - 2][3];
                int m1 = v1 + c1;
                int m2 = v2 + c2;
                int m3 = v3 + c3;
                int m4 = v4 + c4;
                int cmax = max(m1, max(m2, max(m3, m4)));
                best = max(best, cmax);
            }
            dp[i][0] = max((i == 0 ? Min : dp[i - 1][0]), -(a[i] - a[i + 1]) - aad);
            // -(b-c)- |b-c|
            dp[i][1] = max((i == 0 ? Min : dp[i - 1][1]), (a[i] + a[i + 1]) - aad);
            // (b+c)- |b-c|
            dp[i][2] = max((i == 0 ? Min : dp[i - 1][2]), -(a[i] + a[i + 1]) - aad);
            // (-(b+c) - |b-c|
            dp[i][3] = max((i == 0 ? Min : dp[i - 1][3]), (a[i] - a[i + 1] - aad));
            // (b-c)- |b-c|

        }
        // to cover reversing on the two ends
        for (int i = 1; i + 1 < n; i++) {
            int m1 = abs(a[0] - a[i + 1]) - abs(a[i] - a[i + 1]);
            int m2 = abs(a[n - 1] - a[i - 1]) - abs(a[i] - a[i - 1]);
            int cmax = max(m1, m2);
            best = max(best, cmax);
        }
        return base + best;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseSubarrayToMaximize().maxValueAfterReverse(ArrayUtils.read1d("2,4,9,24,2,1,10")));
    }
}

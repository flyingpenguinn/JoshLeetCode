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

sum = |a[i-1]-a[j]| -|a[i-1]-a[i]| +|a[i]-a[j+1]| -|a[j]-a[j+1]|

given i, 2nd is decided

we then take out the 4 possibilities out of
|a[i-1]-a[j]|+|a[i]-a[j+1]|
these are v1 to v4, we need max or min of them, + the 4th item above which is also decided by j

 */
    public int maxValueAfterReverse(int[] a) {

        int n = a.length;
        long sum = 0;
        for (int i = 0; i + 1 < n; i++) {
            sum += abs(a[i] - a[i + 1]);
        }
        // all -abs(a[j]-a[j+1])
        long v1 = Integer.MAX_VALUE;
        // min a[j]+a[j+1]
        long v2 = Integer.MAX_VALUE;
        // min a[j]-a[j+1]
        long v3 = Integer.MIN_VALUE;
        // max a[j]-a[j+1]
        long v4 = Integer.MIN_VALUE;
        // max a[j]+a[j+1]

        // calc special case i=0. note we dont need to switch from 0...n-1 that's the same...so from n-2
        long max = 0;
        for (int i = n - 2; i >= 1; i--) {
            long cur = abs(a[0] - a[i + 1]) - abs(a[i] - a[i + 1]);
            max = max(cur, max);
        }
        for (int i = n - 1; i >= 1; i--) {
            long cur = abs(a[i - 1] - a[n - 1]) - abs(a[i - 1] - a[i]); // j=n-1 has no a[j+1], it is special process it first
            long minus = -abs(a[i - 1] - a[i]);
            long c1 = a[i - 1] + a[i] - v1 + minus;
            long c2 = a[i - 1] - a[i] - v2 + minus;
            long c3 = a[i] - a[i - 1] + v3 + minus;
            long c4 = -a[i] - a[i - 1] + v4 + minus;
            cur = max(cur, max(c1, max(c2, max(c3, c4))));
            max = max(max, cur);
            if (i + 1 < n) {
                v1 = min(v1, a[i] + a[i + 1] + abs(a[i] - a[i + 1]));
                v2 = min(v2, a[i] - a[i + 1] + abs(a[i] - a[i + 1]));
                v3 = max(v3, a[i] - a[i + 1] - abs(a[i] - a[i + 1]));
                v4 = max(v4, a[i] + a[i + 1] - abs(a[i] - a[i + 1]));
            }
        }
        long rt = sum + max;
        return (int) rt;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseSubarrayToMaximize().maxValueAfterReverse(ArrayUtils.read1d("2,4,9,24,2,1,10")));
    }
}

/*
LC#209
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

public class MinimumSizeSubarraySum {

    // all positive hence we can two pointer
    // this is the "all positive" version of "shortest subarray with sum at least k"
    public int minSubArrayLen(int t, int[] a) {
        int n = a.length;
        int start = 0;
        int sum = 0;
        int res = n + 1;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            while (start <= i && sum >= t) {
                // start...i good
                res = Math.min(res, i - start + 1);
                sum -= a[start];
                start++;
            }
        }
        return res >= n + 1 ? 0 : res;
    }
}

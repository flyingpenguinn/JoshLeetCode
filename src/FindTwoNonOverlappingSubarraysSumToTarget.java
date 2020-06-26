import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#1477
Given an array of integers arr and an integer target.

You have to find two non-overlapping sub-arrays of arr each with sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.

Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.



Example 1:

Input: arr = [3,2,2,4,3], target = 3
Output: 2
Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.
Example 2:

Input: arr = [7,3,4,7], target = 7
Output: 2
Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.
Example 3:

Input: arr = [4,3,2,6,2,3,4], target = 6
Output: -1
Explanation: We have only one sub-array of sum = 6.
Example 4:

Input: arr = [5,5,4,4,5], target = 3
Output: -1
Explanation: We cannot find a sub-array of sum = 3.
Example 5:

Input: arr = [3,1,1,1,5,1,2,1], target = 3
Output: 3
Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because they overlap.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 1000
1 <= target <= 10^8
 */
public class FindTwoNonOverlappingSubarraysSumToTarget {
    // similar to three non overlapping subarray...
    private int Max = 10000000;

    public int minSumOfLengths(int[] a, int t) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int sum = 0;
        int[] start = new int[n];
        int[] end = new int[n];
        Arrays.fill(start, Max);
        Arrays.fill(end, Max);
        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (m.containsKey(sum - t)) {
                int prev = m.get(sum - t);
                start[prev + 1] = Math.min(start[prev + 1], i - prev);
                end[i] = i - prev;
            }
            m.put(sum, i);
        }
        int[] right = new int[n];
        right[n - 1] = start[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], start[i]);
        }
        int leftmin = Max;
        int min = Max;
        for (int i = 0; i < n - 1; i++) {
            leftmin = Math.min(leftmin, end[i]);
            int nextlen = right[i + 1];
            int cursum = leftmin + nextlen;
            min = Math.min(min, cursum);
        }
        return min >= Max ? -1 : min;
    }


    public static void main(String[] args) {
        System.out.println(new FindTwoNonOverlappingSubarraysSumToTarget().minSumOfLengths(ArrayUtils.read1d("[64,5,20,9,1,39]"), 69));
        System.out.println(new FindTwoNonOverlappingSubarraysSumToTarget().minSumOfLengths(ArrayUtils.read1d("[3,2,2,4,3]"), 3));
    }
}

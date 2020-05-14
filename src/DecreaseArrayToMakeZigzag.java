/*
LC#1144
Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.

An array A is a zigzag array if either:

Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
Return the minimum number of moves to transform the given array nums into a zigzag array.



Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation: We can decrease 2 to 0 or 3 to 1.
Example 2:

Input: nums = [9,6,1,6,2]
Output: 4


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
 */

public class DecreaseArrayToMakeZigzag {
    // no need to dp, always moving to one direction so no overlapping subproblem
    // only - even positions or only - odd position. we have no reason to - the position we want to keep big
    int Max = 1000000;

    public int movesToMakeZigzag(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return 0;
        }
        int even = 0;
        for (int i = 0; i < n; i += 2) {
            int pre = i == 0 ? Max : a[i - 1];
            int after = i == n - 1 ? Max : a[i + 1];
            int min = Math.min(pre, after);
            int diff = a[i] - (min - 1);
            if (diff > 0) {
                even += diff;
            }
        }
        int odd = 0;
        for (int i = 1; i < n; i += 2) {
            int pre = a[i - 1];
            int after = i == n - 1 ? Max : a[i + 1];
            int min = Math.min(pre, after);
            int diff = a[i] - (min - 1);
            if (diff > 0) {
                odd += diff;
            }
        }
        return Math.min(even, odd);
    }


}

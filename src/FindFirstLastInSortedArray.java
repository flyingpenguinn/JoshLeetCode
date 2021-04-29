/*
LC#34
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]


Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non decreasing array.
-10^9 <= target <= 10^9
 */
public class FindFirstLastInSortedArray {
    public int[] searchRange(int[] a, int t) {
        int start = binary(a, t, true);
        int end = binary(a, t, false);
        return new int[]{start, end};
    }

    private int binary(int[] a, int t, boolean start) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] > t) {
                u = mid - 1;
            } else if (a[mid] < t) {
                l = mid + 1;
            } else {
                if (start) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        // if no == check then first >=
        if (start && l >= 0 && l <= n - 1 && a[l] == t) {
            return l;
            // if no == check then first <=
        } else if (!start && u >= 0 && u <= n - 1 && a[u] == t) {
            return u;
        } else {
            return -1;
        }
    }

    private boolean inRange(int[] a, int l) {
        return l < a.length && l >= 0;
    }
}

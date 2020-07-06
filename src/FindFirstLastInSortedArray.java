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
        if (a == null) {
            return new int[0];
        }
        int p1 = binarySearch(a, t, 0);
        int p2 = binarySearch(a, t, 1);
        return new int[]{p1, p2};
    }

    private int binarySearch(int[] a, int t, int mode) {
        // 0=> first, we need l if that equals
        // 1=> last, we need u if that equals
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] < t) {
                l = mid + 1;
            } else if (a[mid] > t) {
                u = mid - 1;
            } else {
                if (mode == 0) {
                    u = mid - 1;
                } else if (mode == 1) {
                    l = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        if (mode == 0 && l < a.length && a[l] == t) {
            return l;
        } else if (mode == 1 && u >= 0 && a[u] == t) {
            return u;
        }
        return -1;
    }

    private boolean inRange(int[] a, int l) {
        return l < a.length && l >= 0;
    }
}

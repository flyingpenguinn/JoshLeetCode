/*
LC#154
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
 */
public class FindMinimumInRoatedArrayII {
    public int findMin(int[] a) {
        // check null etc
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (l == u) {
                return a[mid];
            } else if (a[l] == a[mid] && a[mid] == a[u]) {
                // linear scan
                return min(a, l, u);
            } else if (a[l] > a[mid]) {
                // mid in 2nd half
                if (a[mid] < a[mid - 1]) {
                    return a[mid];
                } else {
                    u = mid - 1;
                }
            } else {
                // mid in first half
                if (a[mid] <= a[u]) {
                    return a[l];
                    // no 2nd half
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    private int min(int[] a, int start, int end) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }
}

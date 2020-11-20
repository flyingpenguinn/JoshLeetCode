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
    // note the structure is similar: check which part mid is in first then decide the two directions. note the < not <=
    public int findMin(int[] a) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l < u) {
            int mid = l + (u - l) / 2;
            if (a[l] == a[mid] && a[mid] == a[u]) {
                // when all equal like
                // 3,3,2,[3],3,3,3 or 3,3,[3],2,3,3
                // hard to tell which part mid is in
                int min = a[l];
                for (int i = l + 1; i < n; i++) {
                    min = Math.min(min, a[i]);
                }
                return min;
            }
            if (a[mid] >= a[l]) {
                // bigger part
                if (a[l] < a[u]) {
                    // can't be <=: 1,2,0,1. gist is even if a[l] <=a[mid] and a[mid] <=a[u] it may not be a sorted one
                    return a[l];
                } else {
                    l = mid + 1;
                }
            } else {
                if ((mid == l || a[mid] < a[mid - 1]) && (mid == u || a[mid] <= a[mid + 1])) {
                    // can't be a[mid] <= a[mid-1] otherwise we will return in an equal stream. because there are unequal numbers the min must be either a[l] or < its predecessor
                    return a[mid];
                } else {
                    u = mid - 1;
                }
            }
        }
        return a[l];
    }
}

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
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            if (l == u || a[l] < a[u]) {
                // == for single element
                return a[l];
            }
            int mid = l + (u - l) / 2;
            if (a[mid] == a[l]) {
                // extra part from #1
                if (a[mid] < a[u]) {
                    //[3,3,4]
                    return a[l];
                } else if (a[mid] > a[u]) {
                    //[3,3,1]
                    l = mid + 1;
                } else {
                    //[3,1,3,3,3] or [3,3,1,3],cant tell which part
                    return fm(a);
                }
            } else if (a[mid] > a[l]) {
                l = mid + 1;
            } else {
                if (a[mid] < a[mid - 1]) {
                    return a[mid];
                } else {
                    u = mid - 1;
                }
            }
        }
        return -1;
    }

    int fm(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }
}

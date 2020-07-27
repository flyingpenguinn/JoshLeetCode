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
    // al>amid to know which part we are in, then drill down on 2 cases each
    public int findMin(int[] a) {
        // check null etc
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == a[l] && a[mid] == a[u]) {
                // can't tell in this case
                return min(a, l, u);
            }
            if (a[l] > a[mid]) {
                // 2nd half
                if (a[mid] < a[mid - 1]) {
                    return a[mid];
                } else {
                    u = mid - 1;
                }
            } else {
                // first half
                if (a[mid] <= a[u]) {
                    // al <=amid <=au and one of the equals not holding so al<au for sure
                    return a[l];
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    private int min(int[] a, int l, int u) {
        int res = a[l];
        for (int i = l + 1; i <= u; i++) {
            res = Math.min(res, a[i]);
        }
        return res;
    }
}

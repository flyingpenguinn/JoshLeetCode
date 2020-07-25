/*
LC#153
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 */
public class FindMinimumInRoatedArray {
    public int findMin(int[] a) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (l == u) {
                return a[mid];
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
                    // and there is no 2nd halfmid
                    return a[l];
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}

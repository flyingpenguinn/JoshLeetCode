/*
LC#1064
Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.  Return -1 if no such i exists.



Example 1:

Input: [-10,-5,0,3,7]
Output: 3
Explanation:
For the given array, A[0] = -10, A[1] = -5, A[2] = 0, A[3] = 3, thus the output is 3.
Example 2:

Input: [0,2,5,8,17]
Output: 0
Explanation:
A[0] = 0, thus the output is 0.
Example 3:

Input: [-10,-5,3,4,7,9]
Output: -1
Explanation:
There is no such i that A[i] = i, thus the output is -1.


Note:

1 <= A.length < 10^4
-10^9 <= A[i] <= 10^9
 */
public class FixedPoint {
    // distinct, ascending is why we can use binary search
    public int fixedPoint(int[] a) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] >= mid) {
                // if a[mid] >= then all i after mid >=i, because number increase at least 1. need to look left
                u = mid - 1;
            } else {
                // if a[mid]<mid then all i before <i because a[i] decrease by at leat 1 while i decrease only one
                l = mid + 1;
            }
        }
        return (l < a.length && a[l] == l) ? l : -1;
    }
}

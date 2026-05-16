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
    // note the structure is similar: compare mid with r. 3 cases, <. > , =
    public int findMin(int[] a) {
        int n = a.length;
        int l = 0;
        int u = n-1;
        int res = Integer.MAX_VALUE;
        while(l<=u){
            int mid = l+(u-l)/2;
            res = Math.min(res, a[mid]);
            if(a[mid]>a[u]){
                l = mid+1;
            }else if(a[mid]<a[u]){
                u = mid-1;
            }else{
                u -= 1;
            }
        }
        return res;
    }
}

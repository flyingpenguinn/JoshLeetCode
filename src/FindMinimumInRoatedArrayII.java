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
        int l=0;
        int u=n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[l] == a[mid] && a[mid]==a[u]){
                int min = a[0];
                for(int i=1; i<n; ++i){
                    min = Math.min(min, a[i]);
                }
                return min;
            }
            if(a[l]<a[u]){
                return a[l];
            }
            else if((mid==0 || a[mid]<a[mid-1])
                    && (mid==n-1 || a[mid]<=a[mid+1])){
                // key: min must < on the left it could <= on the right
                return a[mid];
            }
            else if(a[l]>a[mid]){
                // a[l]>a[mid] means mid is on the smaller side on the right
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return -1;
    }
}

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
        int n = a.length;
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if(a[l]<=a[u]){
                return a[l];
            }
            else if((mid==0 || a[mid]<a[mid-1])
                    && (mid==n-1 || a[mid]<=a[mid+1])){
                // key: min must < on the left it could <= on the right
                return a[mid];
            }
            else if(a[l]>a[mid]){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return -1;
    }
}

/*
LC#81
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == t) {
                return true;
            } else if (a[l] == a[mid] && a[mid] == a[u]) {
                // can't tell mid is in which part if all 3 are equal
                // for example if it's
                // 5,5,5,5,2,3,4,[5],5,5,5,5
                // we would go l= mid+1 when we should go u = mid-1 if mid is the bracketed 5
                for (int i = l; i <= u; i++) {
                    if (a[i] == t) {
                        return true;
                    }
                }
                return false;
            } else {
                // rest is the same as search iin rotated sorted array I
                if (a[mid] >= a[l]) {
                    // mid in bigger part
                    if (t >= a[l] && t <= a[mid]) {
                        // t is on the left between a[l] and a[mid]
                        u = mid - 1;
                    } else {
                        // t<a[l] or t>a[mid]. we go to the right part. t could be in the bigger part but > a[mid], or in the smaller part
                        l = mid + 1;
                    }
                } else {
                    // mid in smaller part
                    if (t >= a[mid] && t <= a[u]) {
                        l = mid + 1;
                    } else {
                        u = mid - 1;
                    }
                }
            }
        }
        return false;
    }
}

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
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (t == a[mid] || t == a[u]) {
                return true;
            }
            if (a[mid] > a[u]) {
                if (t < a[u] || t > a[mid]) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            } else if (a[mid] < a[u]) {
                if (t > a[mid] && t < a[u]) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            } else {
                --u;
            }
        }
        return false;
    }
}

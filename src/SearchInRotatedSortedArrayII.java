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
        // has dupe in array. apart from the l, u, mid check, all rest are the same as problem 1
        if (a == null) {
            return false;
        }
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == t) {
                return true;
            } else {
                // if all 3 the same, hard to tell where i am...
                // could be 13[1]11 (2nd half here) or
                //  33[3]123 (first half here)
                if (a[l] == a[u] && a[l] == a[mid]) {
                    for (int i = l; i <= u; i++) {
                        if (a[i] == t) {
                            return true;
                        }
                    }
                    return false;
                } else if (a[l] > a[mid]) {   // can still use this same check to see which section it is in: 2nd half
                    if (t > a[mid] && t <= a[u]) {
                        l = mid + 1;
                    } else {
                        u = mid - 1;
                    }
                } else { // or first half
                    if (t < a[mid] && t >= a[l]) {
                        u = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
        }
        return false;
    }
}

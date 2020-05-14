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
    public boolean search(int[] a, int target) {
        int l = 0;
        int u = a.length - 1;

        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == target) {
                return true;
            }
            // hard to tell...have to revert to normal search
            // for example we can't tell between
            // 13xxxx mid =11111
            // 1xxxxxxmid=1xxx31111
            // 3 could be on both sides...
            if (a[mid] == a[l] && a[mid] == a[u]) {
                for (int i = l; i <= u; i++) {
                    if (a[i] == target) {
                        return true;
                    }
                }
                return false;
            }
            // otherwise if >=a[l] then on the same side.
            // note amid!= atarget so if they are both >=a[l] they must be on the same side even when al == au
            // (i.e. they could well be on right side but that doesn't hurt)

            // below is almost the same as search I

            // mid and target are in well sorted segments. note using ==
            boolean sameside = (a[mid] >= a[l] && target >= a[l]) || (a[mid] < a[l] && target < a[l]);
            if (sameside) {
                // use normal binary search for same side
                if (a[mid] > target) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // use opposite binary search for different sides
                if (a[mid] < target) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

        }
        return false;
    }
}

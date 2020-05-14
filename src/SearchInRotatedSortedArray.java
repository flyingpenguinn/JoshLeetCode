/*
LC#33
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

import static base.ArrayUtils.read1d;

public class SearchInRotatedSortedArray {
    public int search(int[] a, int target) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == target) {
                return mid;
            }
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
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArray().search(read1d("3,1"), 1));

    }
}

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
    // depending on relationship of a[l], mid, know where mid is. then depending on t vs a[l] and a[u], know where to go
    public int search(int[] a, int t) {
        // no dupe in array
        if (a == null) {
            return -1;
        }
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == t) {
                return mid;
            } else {
                if (a[l] > a[mid]) { // 2nd half the smaller one
                    if (t > a[mid] && t <= a[u]) {
                        l = mid + 1;
                    } else {
                        u = mid - 1;
                    }
                } else { // first half the bigger one
                    if (t < a[mid] && t >= a[l]) {
                        u = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArray().search(read1d("3,1"), 1));

    }
}

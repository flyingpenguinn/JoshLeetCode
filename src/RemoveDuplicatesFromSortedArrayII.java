/*
LC#80
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
 */

import base.ArrayUtils;

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] a) {
        if (a.length < 2) {
            return a.length;
        }
        int rp = 2;
        // note old vals are overwritten!
        int p0 = a[0];
        int p1 = a[1];
        for (int i = 2; i < a.length; i++) {
            if (a[i] == p0 && a[i] == p1) {
                continue;
            }
            p0 = p1;
            p1 = a[i];
            a[rp++] = a[i];
        }
        return rp;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateElegant().removeDuplicates(ArrayUtils.read1d("1,1,1,2,2,2,3")));
    }
}

// can be extended to k easily...
class RemoveDuplicateElegant {
    public int removeDuplicates(int[] a) {
        int k = 2;
        if (a.length < k) {
            return a.length;
        }
        int ri = k;
        for (int i = k; i < a.length; i++) {
            if (a[i] > a[ri - k]) {
                // ri, not i!
                a[ri++] = a[i];
            }
        }
        return ri;
    }
}

/*
LC#169
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */
public class MajorityElement {
    public int majorityElement(int[] a) {
        // always exist! otherwise need to double check ma
        // delete two different elements each time. in the end, majority element must be the one left
        // note we are NOT putting v to anywhere when we count to 0 for n1. v is the number we take away
        int n1 = 0;
        int c1 = 0;
        for (int v : a) {
            if (v == n1) {
                c1++;
            } else if (c1 == 0) {
                n1 = v;
                c1 = 1;
            } else {
                c1--;
            }
        }
        return n1;
    }
}

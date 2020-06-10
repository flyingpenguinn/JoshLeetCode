/*
LC#88
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {
    // go from the back because it has enough space to avoid being overridden
    public void merge(int[] a, int m, int[] b, int n) {
        int i = m - 1;
        int j = n - 1;
        int ri = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (a[i] >= b[j]) {
                a[ri--] = a[i--];
            } else {
                a[ri--] = b[j--];
            }
        }
        while (i >= 0) {
            a[ri--] = a[i--];
        }
        while (j >= 0) {
            a[ri--] = b[j--];
        }
    }
}


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
    // go from the back
    int Min = Integer.MIN_VALUE;

    public void merge(int[] a, int na, int[] b, int nb) {
        int i = na - 1;
        int j = nb - 1;
        int k = na + nb - 1;
        while (i >= 0 || j >= 0) {
            int va = i < 0 ? Min : a[i];
            int vb = j < 0 ? Min : b[j];
            if (va > vb) {
                a[k--] = va;
                i--;
            } else {
                a[k--] = vb;
                j--;
            }
        }
    }
}


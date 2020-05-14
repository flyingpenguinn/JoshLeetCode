import base.ArrayUtils;

/*
LC#4
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

https://leetcode.com/problems/median-of-two-sorted-arrays/

 */
public class MedianOfTwoSortedArrays {
    // Lg a+b  make sure we throw away a fixed percentage of numbers each time. here we at least throw away either na or nb numbers each time
    public double findMedianSortedArrays(int[] a, int[] b) {
        int na = a.length;
        int nb = b.length;
        int n = na + nb;
        if (n == 0) {
            return 0.0;
        }
        if (n % 2 == 1) {
            return find(a, 0, na - 1, b, 0, nb - 1, (n + 1) / 2);
        } else {
            // if there are two we take the avg of them
            int n1 = find(a, 0, na - 1, b, 0, nb - 1, n / 2);
            int n2 = find(a, 0, na - 1, b, 0, nb - 1, n / 2 + 1);
            return (n1 + n2) / 2.0;

        }

    }

    // find the kth number among sorted a and b.   k from 1
    private int find(int[] a, int al, int au, int[] b, int bl, int bu, int k) {
        // b could become shorter than a...
        int lena = au - al + 1;
        int lenb = bu - bl + 1;
        if (lena > lenb) {
            return find(b, bl, bu, a, al, au, k);
        }
        if (al > au) {
            // exhausted a. note a is shorter
            return b[bl + k - 1];
        }
        if (k == 1) {
            return Math.min(a[al], b[bl]);
        }

        int cutlena = Math.min(lena, k / 2);
        int cutlenb = k - cutlena;
        int cuta = al + cutlena - 1;
        int cutb = bl + cutlenb - 1;
        if (a[cuta] == b[cutb]) {
            // including itself this number >= cutlena numbers in a, cutlenb numbers in b. so k numbers in all. it's the kth number
            return a[cuta];
        } else if (a[cuta] < b[cutb]) {
            // a[cuta] is guaranteed <= na-cutlena numbers in a, and <= nb-cutlenb+1 numbers in b.
            // in all excluding itself, it's <= na+nb-k+1 +1 (+self) numbers, hence >= k-2 other numbers at most.
            // so it won't be the kth number as that number >=k-1 others. note even if it equals the real kth number, throwing it away won't hurt
            // similarly, b[cutb+1] is guaranteed >= cutlenb numbers in b, cutlena numbers in a. it's >= k numbers, excluding itself can't be the kth number
            // so cutb is the last possible one
            return find(a, cuta + 1, au, b, bl, cutb, k - cutlena);
        } else {
            // if reverse, we reverse the above verdict
            return find(a, al, cuta, b, cutb + 1, bu, k - cutlenb);
        }
    }
}

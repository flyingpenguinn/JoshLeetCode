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
    // Lg a+b  make sure we throw away a fixed percentage of numbers each time.
    // here we at least throw away either na or nb numbers each time
    public double findMedianSortedArrays(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int n = an + bn;
        if (n % 2 == 0) {
            int v1 = find(a, 0, an - 1, b, 0, bn - 1, n / 2);
            int v2 = find(a, 0, an - 1, b, 0, bn - 1, n / 2 + 1);
            return (0.0 + v1 + v2) / 2.0;
        } else {
            return find(a, 0, an - 1, b, 0, bn - 1, n / 2 + 1);
        }
    }

    int find(int[] a, int la, int ua, int[] b, int lb, int ub, int k) {
        if (ua - la > ub - lb) {
            return find(b, lb, ub, a, la, ua, k);
        }
        if (la > ua) {
            // need to do this first otherwise error will be thrown from below line when we get the "first"
            return b[k - 1];
        }
        if (k == 1) {
            return Math.min(a[la], b[lb]);
        }

        int mida = la+(ua-la)/2;
        int cuta = Math.min(mida-la+1, k);
        int cutb = k - cuta;
        int ma = la + cuta - 1;
        int mb = lb + cutb - 1;
        if (a[ma] == b[mb]) {
            return a[ma];
        } else if (a[ma] < b[mb]) {
            // the number at mb+1 has at least k numbers < it, won't be the kth number
            // the number at ma has at least lena-k/2+lenb-k/2+1 == lena+lenb-k+1 numbers > it, won't be the kth number either
            // so we throw numbers that are too big, or too small
            return find(a, ma + 1, ua, b, lb, mb, k - cuta);
        } else {
            return find(a, la, ma, b, mb + 1, ub, k - cutb);
        }
    }
}

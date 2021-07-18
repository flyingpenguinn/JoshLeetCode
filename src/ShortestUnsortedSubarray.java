import base.ArrayUtils;

import java.util.Arrays;

/*
LC#581
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortestUnsortedSubarray {
    // there is a smaller one on the right. the last of such is the start, right to left
    // there is a bigger  one on the left. the  last of such is the end, left to right
    public int findUnsortedSubarray(int[] a) {
        int n = a.length;
        int start = -1;
        int end = -1;
        int right = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right = Math.min(right, a[i]);
            if (right < a[i]) {
                start = i;
            }
        }
        int left = a[0];
        for (int i = 1; i < n; i++) {
            left = Math.max(left, a[i]);
            if (left > a[i]) {
                end = i;
            }
        }
        return start == -1 ? 0 : end - start + 1;
    }
}

class ShortedUnsotedSubarraySorting {
    // any part that is unequal to sorted array is the boundary
    public int findUnsortedSubarray(int[] a) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        int first = -1;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (ca[i] != a[i]) {
                last = i;
                first = first == -1 ? i : first;
            }
        }
        return first == -1 ? 0 : last - first + 1;
    }
}

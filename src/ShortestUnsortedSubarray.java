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
    // 1. find the suspicous area first
    // 2. get its min and max
    // 3. scan from front or back to get the range. i.e. we extand the suspicious area wider to include boundaries
    public int findUnsortedSubarray(int[] a) {
        int n = a.length;
        int i = 1;
        for (; i < n; i++) {
            if (a[i] < a[i - 1]) {
                i--;
                break;
            }
        }
        if (i == n) {
            return 0;
        }

        int j = n - 2;
        for (; j >= 0; j--) {
            if (a[j] > a[j + 1]) {
                j++;
                break;
            }
        }
        int min = a[i];
        int max = a[i];

        for (int k = i; k <= j; k++) {
            min = Math.min(min, a[k]);
            max = Math.max(max, a[k]);
        }
        int rs = -1;
        for (i = 0; i < n; i++) {
            if (a[i] > min) {
                rs = i;
                break;
            }
        }
        int re = -1;
        for (i = n - 1; i >= 0; i--) {
            if (a[i] < max) {
                re = i;
                break;
            }
        }
        return re - rs + 1;
    }
}

class ShortedUnsotedSubarraySorting {
    // "flood" from both sides.
    public int findUnsortedSubarray(int[] a) {
        int[] clone = a.clone();
        Arrays.sort(a);
        int start = 0;
        while (start < a.length && a[start] == clone[start]) {
            start++;
        }
        int end = a.length - 1;
        while (end >= start && a[end] == clone[end]) {
            end--;
        }
        return end - start + 1;
    }
}

import base.ArrayUtils;

/*
LC#949
Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.



Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""


Note:

A.length == 4
0 <= A[i] <= 9
 */
public class LargestTimeForGivenDigits {
    // no need to loop on l, 6-i-j-k is l
    public String largestTimeFromDigits(int[] a) {
        int max = -1;
        String maxs = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) {
                    continue;
                }
                int hour = 10 * a[i] + a[j];
                if (hour >= 24) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    if (i == k || j == k) {
                        continue;
                    }
                    int l = 6 - i - j - k;
                    int min = a[k] * 10 + a[l];
                    if (min >= 60) {
                        continue;
                    }
                    int cur = hour * 60 + min;
                    if (cur > max) {
                        max = cur;
                        maxs = "" + a[i] + a[j] + ":" + a[k] + a[l];
                    }
                }
            }
        }
        return maxs == null ? "" : maxs;
    }
}
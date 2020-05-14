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
    // turn time to minutes, chck all permutations
    // can't just check and get best hour that may yield no result even if there is one
    public String largestTimeFromDigits(int[] a) {
        int n = a.length;
        int max = -1;
        String r = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                int hour = 10 * a[i] + a[j];
                if (hour >= 24) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        int min = 10 * a[k] + a[l];
                        if (min >= 60) {
                            continue;
                        }
                        int cur = hour * 60 + min;
                        if (cur > max) {
                            max = cur;
                            r = "" + a[i] + a[j] + ":" + a[k] + a[l];
                        }
                    }
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("5,5,5,5")));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("0,0,0,3")));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("1,2,3,4")));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("2,0,6,6")));

    }
}
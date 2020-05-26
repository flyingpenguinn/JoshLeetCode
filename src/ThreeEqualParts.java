import base.ArrayUtils;

import java.util.Arrays;

/*
LC#927
Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i+1 < j, such that:

A[0], A[1], ..., A[i] is the first part;
A[i+1], A[i+2], ..., A[j-1] is the second part, and
A[j], A[j+1], ..., A[A.length - 1] is the third part.
All three parts have equal binary value.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.



Example 1:

Input: [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: [1,1,0,1,1]
Output: [-1,-1]


Note:

3 <= A.length <= 30000
A[i] == 0 or A[i] == 1
 */
public class ThreeEqualParts {
    // rely on the fact that for the 3 parts to be equal, their 1s must equal. we can use count of one to know the starting points of 3 parts
    // once we figure out the positions we can compare them in On time
    public int[] threeEqualParts(int[] a) {
        int n = a.length;
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                ones++;
            }
        }
        if (ones % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (ones == 0) {
            return new int[]{0, 2};
        }
        int count = 0;
        int start2 = -1;
        int start3 = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                count++;
            }
            if (count == ones / 3) {
                start2 = i + 1;
            }
            if (count == 2 * ones / 3) {
                start3 = i + 1;
                break;
            }
        }
        return allsame(a, start2, start3);
    }

    private int[] allsame(int[] a, int j, int k) {
        int n = a.length;
        int i = 0;
        int p2start = j;
        int p3start = k;
        while (i < p2start && a[i] == 0) {
            i++;
        }
        while (j < p3start && a[j] == 0) {
            j++;
        }
        while (k < n && a[k] == 0) {
            k++;
        }
        p2start = j;
        p3start = k;
        int len = n - p3start; // we know the needed len from p3
        int curlen = 0;
        while (i < p2start && j < p3start && k < n) {
            if (a[i] != a[j] || a[j] != a[k] || a[i] != a[k]) {
                return new int[]{-1, -1};
            }
            i++;
            j++;
            k++;
            curlen++;
        }
        if (curlen == len) {
            return new int[]{i - 1, j};
        } else {
            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,0,1"))));
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,1,0"))));

        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,0,0,1,0,1,0,0,1,0,0"))));
    }
}

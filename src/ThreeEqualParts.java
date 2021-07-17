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
    // rely on the fact that for the 3 parts to be equal, their 1s must equal.
    // then 0s at the end of the 3rd number decides the number itself, if they can be equal
    // note after counting 0s and 1s, we need to verify they are really equal
    public int[] threeEqualParts(int[] a) {
        int n = a.length;
        int ones = 0;
        int[] bad = new int[]{-1, -1};
        for (int i = 0; i < n; i++) {
            ones += a[i];
        }
        if (ones % 3 != 0) {
            return bad;
        }
        if (ones == 0) {
            return new int[]{0, 2};
        }
        int t1 = ones / 3;
        int i = n - 1;
        while (i >= 0 && a[i] == 0) {
            i--;
        }
        int t0 = n - 1 - i;
        int s2 = find(a, 0, t1, t0);
        if (s2 == -1) {
            return bad;
        }
        int s3 = find(a, s2, t1, t0);
        if (s3 == -1) {
            return bad;
        }
        if (eq(a, 0, s2, s3)) {
            return new int[]{s2 - 1, s3};
        }
        return bad;
    }

    int find(int[] a, int st, int t1, int t0) {
        int i = st;
        int n = a.length;
        while (i < n && a[i] == 0) {
            i++;
        }
        while (i < n && t1 > 0) {
            t1 -= a[i];
            i++;
        }
        while (i < n && t0 > 0 && a[i] == 0) {
            t0--;
            i++;
        }
        // note there could be no enough 0 at the end for one segment
        if (t0 > 0) {
            return -1;
        }
        return i;
    }

    boolean eq(int[] a, int i1, int i2, int i3) {
        int n = a.length;
        while (i1 < i2 && a[i1] == 0) {
            i1++;
        }
        while (i2 < i3 && a[i2] == 0) {
            i2++;
        }
        while (i3 < n && a[i3] == 0) {
            i3++;
        }
        while (i1 < i2 && i2 < i3 && i3 < n) {
            if (a[i1] != a[i2] || a[i2] != a[i3] || a[i1] != a[i3]) {
                return false;
            }
            i1++;
            i2++;
            i3++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,0,1"))));
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,1,0"))));

        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,0,0,1,0,1,0,0,1,0,0"))));
    }
}

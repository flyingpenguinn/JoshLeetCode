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
        int allones = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            allones += a[i];
        }
        if (allones % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (allones == 0) {
            return new int[]{0, n - 1};
        }
        int t = allones / 3;
        int rzeros = 0;
        int j = n - 1;
        int[] res = new int[2];
        for (int times = 2; times >= 0; times--) {
            int i = j;
            int zeros = 0;
            while (i >= 0 && a[i] == 0) {
                i--;
                zeros++;
            }
            if (times == 2) {
                rzeros = zeros;
            } else {
                if (zeros < rzeros) {
                    return new int[]{-1, -1};
                }
                int diff = zeros - rzeros;
                res[times] = j - diff + times;
                if (times == 0) {
                    break;
                }
                // if times==1 we need to +1
            }
            int ones = 0;
            j = i;
            while (j >= 0 && ones < t) {
                ones += a[j--];
            }
        }

        if (match(a, 0, res[0], res[0] + 1, res[1] - 1, res[1], n - 1)) {
            return res;
        } else {
            return new int[]{-1, -1};
        }
    }

    private boolean match(int[] a, int i1, int i2, int j1, int j2, int k1, int k2) {
        while (i1 <= i2 && a[i1] == 0) {
            i1++;
        }
        while (j1 <= j2 && a[j1] == 0) {
            j1++;
        }
        while (k1 <= k2 && a[k1] == 0) {
            k1++;
        }
        int len1 = i2 - i1;
        int len2 = j2 - j1;
        int len3 = k2 - k1;
        if (len1 != len2 || len2 != len3 || len1 != len3) {
            return false;
        }
        while (i1 <= i2 && j1 <= j2 && k1 <= k2) {
            if (a[i1] != a[j1] || a[i1] != a[k1] || a[j1] != a[k1]) {
                return false;
            }
            i1++;
            j1++;
            k1++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,0,1"))));
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,1,0"))));

        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,0,0,1,0,1,0,0,1,0,0"))));
    }
}

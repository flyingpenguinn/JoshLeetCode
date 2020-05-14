import base.ArrayUtils;

import java.util.Arrays;

/*
LC#360
Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
 */
public class SortTransformedArray {
    // parabola nature, start from two ends
    public int[] sortTransformedArray(int[] x, int a, int b, int c) {
        if (a < 0) {
            int[] r = sortTransformedArray(x, -a, -b, -c);
            reverse(r);
            return r;
        }
        // a must be >0
        int i = 0;
        int n = x.length;
        int j = n - 1;
        int ri = n - 1;
        int[] r = new int[n];
        while (i <= j) {
            int vi = a * x[i] * x[i] + b * x[i] + c;
            int vj = a * x[j] * x[j] + b * x[j] + c;
            if (vj >= vi) {
                r[ri--] = vj;
                j--;
            } else {
                r[ri--] = vi;
                i++;
            }
        }
        return r;
    }

    // reverse and change sign
    private void reverse(int[] a) {
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int tmp = -a[i];
            a[i] = -a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SortTransformedArray().sortTransformedArray(ArrayUtils.read1d("2,4,12,14"), 0, -3, 5)));
    }
}

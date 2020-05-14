import base.ArrayUtils;

import java.util.Arrays;

/*
LC#1089
Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place, do not return anything from your function.



Example 1:

Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]


Note:

1 <= arr.length <= 10000
0 <= arr[i] <= 9
 */
public class DuplicateZeros {
    // we can also use a[i] to indicate delta of i then reverse traverse
    // gist is we know where to put non zero a[i] if we know how many zeros are there when we get to i.
    // if zero>0 we set sth after i so reverse traverse
    public void duplicateZeros(int[] a) {
        int n = a.length;
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                zeros++;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == 0) {
                zeros--;
            } else {
                if (i + zeros < n) {
                    a[i + zeros] = a[i];
                }
                if (zeros > 0) {
                    a[i] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,2,3,0,4,5,0]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,2,3]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,0,2,0,4]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,0,2,3,4,5]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,0,0,2,3,4,5]"));

    }
}

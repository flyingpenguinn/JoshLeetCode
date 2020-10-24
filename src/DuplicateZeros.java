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

    // start from the back once we know what the last element is- could be a single zero
    public void duplicateZeros(int[] a) {
        int n = a.length;
        int len = 0;
        int i = 0;
        for (; i < n; i++) {
            if (a[i] != 0) {
                len++;
            } else {
                len += 2;
            }
            if (len >= n) {
                break;
            }
        }
        int j = n - 1;
        for (; i >= 0; i--) {
            if (a[i] == 0) {
                a[j] = 0;
                j--;
                if (len <= n) {
                    // in case only one 0 fits at the end
                    a[j] = 0;
                    j--;
                }
                len -= 2;
            } else {
                a[j] = a[i];
                j--;
            }
        }
    }

    public static void main(String[] args) {
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,2,3,0,4,5,0]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,2,3]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,0,2,0,4]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,0,2,3,4,5]"));
        new DuplicateZeros().duplicateZeros(ArrayUtils.read1d("[1,0,0,0,2,3,4,5]"));

    }
}

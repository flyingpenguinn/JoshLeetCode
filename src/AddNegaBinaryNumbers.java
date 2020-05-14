import java.util.ArrayList;
import java.util.List;

/*
LC#1073
Given two numbers arr1 and arr2 in base -2, return the result of adding them together.

Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.

Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.



Example 1:

Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
Output: [1,0,0,0,0]
Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.


Note:

1 <= arr1.length <= 1000
1 <= arr2.length <= 1000
arr1 and arr2 have no leading zeros
arr1[i] is 0 or 1
arr2[i] is 0 or 1
 */
public class AddNegaBinaryNumbers {
    public int[] addNegabinary(int[] a, int[] b) {
        int i = a.length - 1;
        int j = b.length - 1;
        int carry = 0;
        List<Integer> rlist = new ArrayList<>();
        while (i >= 0 || j >= 0 || carry != 0) {
            int va = i == -1 ? 0 : a[i--];
            int vb = j == -1 ? 0 : b[j--];
            int rv = va + vb + carry;
            int bit = Math.abs(rv % 2);
            rlist.add(bit);
            if (rv > 1) {
                carry = -1;
            } else if (rv <= -1) {
                carry = 1;
            } else {
                carry = 0;
            }
        }

        i = rlist.size() - 1;
        while (i >= 1 && rlist.get(i) == 0) {
            i--;
        }

        int[] ar = new int[i + 1];
        for (j = 0; j < ar.length; j++) {
            ar[j] = rlist.get(i--);
        }
        return ar;

    }
}

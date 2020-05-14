/*
LC#66
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    // break early by checking carry. only possibility of a different length is like 100=99+1
    public int[] plusOne(int[] d) {
        int n = d.length;
        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (carry == 0) {
                break;
            }
            int raw = d[i] + carry;
            d[i] = raw % 10;
            carry = raw / 10;
        }
        // only possibility is 99+1=100
        if (carry == 1) {
            int[] r = new int[n + 1];
            r[0] = 1;
            return r;
        } else {
            return d;
        }
    }
}

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
    // find first non 9 digit on the right, +1. all we pass by should now be 0
    public int[] plusOne(int[] a) {
        if (a == null) {
            return new int[0];
        }
        int i = a.length - 1;
        // right most non nine digit at i
        while (i >= 0 && a[i] == 9) {
            a[i] = 0;
            i--;
        }
        if (i == -1) {
            // all 9, new an array
            int[] r = new int[a.length + 1];
            r[0] = 1;
            return r;
        } else {
            a[i]++;
            return a;
        }
    }
}


/*
LC#29
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
public class DivideInteger {
    // a = x*b +y => a = x*(b+b) + y` and then transform the 2nd x to the 1st x
    // somewhat similar to binary lifting concept
    public int divide(int a, int b) {
        // b not zero. a,b signed 32 bit integer
        if (a == 0) {
            return 0;
        }
        if (b == -1 && a == Integer.MIN_VALUE) { // only overflow possible
            return Integer.MAX_VALUE;
        }
        int res = doDiv(a > 0 ? -a : a, b > 0 ? -b : b)[0];
        if (a > 0 && b < 0 || a < 0 && b > 0) {
            res = -res;
        }
        return res;
    }

    // neg value. quotient/ mod value
    private int[] doDiv(int a, int b) {
        if (a > b) {  // -2 vs -3
            return new int[]{0, a};
        }
        if (a == b) {
            return new int[]{1, 0};
        }
        if (b < Integer.MIN_VALUE - b) {
            return new int[]{1, a - b};
        }
        int[] res = doDiv(a, b + b);
        if (res[1] <= b) { // -5 vs -3
            res[0] = res[0] + res[0] + 1;
            res[1] = res[1] - b;
        } else {
            res[0] = res[0] + res[0];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DivideInteger().divide(-7, -2));
    }
}

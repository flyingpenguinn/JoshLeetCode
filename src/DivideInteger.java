
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

        long rt = dodiv(a, b)[0];
        return (int) Math.min(rt, Integer.MAX_VALUE);
    }

    long[] dodiv(long a, long b) {
        if (a < 0) {
            long[] res = dodiv(-a, b);
            res[0] = -res[0];
            return res;
        }
        if (b < 0) {
            long[] res = dodiv(a, -b);
            res[0] = -res[0];
            return res;
        }
        if (b == 1) {
            return new long[]{a, 0};
        }
        if (a == 0 || a < b) {
            return new long[]{0, a};
        }
        if (b == 0) {
            return new long[]{Integer.MAX_VALUE, 0};
        }
        long[] res = dodiv(a, b + b);
        long mod = res[1];
        long quo = res[0] + res[0];
        if (mod >= b) {
            quo += 1;
            mod -= b;
        }
        return new long[]{quo, mod};
    }

    public static void main(String[] args) {
        System.out.println(new DivideInteger().divide(-7, -2));
    }
}

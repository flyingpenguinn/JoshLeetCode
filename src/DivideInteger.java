public class DivideInteger {

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
    // a = x*b +y => a = x*(b+b) + y and then transform the 2nd x to the 1st x

    public int divide(long dividend, long divisor) {
        long res = dod(dividend, divisor)[0];
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) res;
        }
    }

    private long[] dod(long dividend, long divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }
        if (divisor < 0 && dividend > 0) {
            // 7 = -2*-3 +1
            long[] r = dod(dividend, -divisor);
            r[0] = -r[0];
            return r;
        }
        if (divisor > 0 && dividend < 0) {
            long[] r = dod(-dividend, divisor);
            // -7 = -2*3 -1;
            r[0] = -r[0];
            r[1] = -r[1];
            return r;
        }
        if (dividend < 0 && divisor < 0) {
            return dod(-dividend, -divisor);
        }
        // both >0

        // end condition
        if (dividend < divisor) {
            return new long[]{0, dividend};
        }
        long[] res = dod(dividend, 2 * divisor);
        res[0] *= 2;
        if (res[1] >= divisor) {
            res[1] -= divisor;
            res[0]++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DivideInteger().divide(-7, -2));
    }
}

class DivideIntegerBinarySearch {

    public int divide(long dividend, long divisor) {
        if (dividend == divisor) {
            return 1;
        }
        if (dividend == -divisor) {
            return -1;
        }
        if (divisor == 1) {
            return (int) dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return (int) -dividend;
            }
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend < 0) {
            return -divide(-dividend, divisor);
        }
        if (divisor < 0) {
            return -divide(dividend, -divisor);
        }
        if (dividend < divisor) {
            return 0;
        }
        double loga = Math.log10(dividend);
        double logb = Math.log10(divisor);
        double diff = loga - logb;
        double raw = Math.pow(10, diff);
        return (int) Math.floor(raw + 0.00001);
    }
}

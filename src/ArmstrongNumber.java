/*
LC#1134
The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.

Given a positive integer N, return true if and only if it is an Armstrong number.



Example 1:

Input: 153
Output: true
Explanation:
153 is a 3-digit number, and 153 = 1^3 + 5^3 + 3^3.
Example 2:

Input: 123
Output: false
Explanation:
123 is a 3-digit number, and 123 != 1^3 + 2^3 + 3^3 = 36.


Note:

1 <= N <= 10^8
 */
public class ArmstrongNumber {
    public boolean isArmstrong(int n) {
        int k = dig(n);
        int arm = arm(n, k);
        return arm == n;
    }

    // n>=1
    private int arm(int n, int k) {
        int r = 0;
        while (n > 0) {
            r += (int) Math.pow(n % 10, k);
            n /= 10;
        }
        return r;
    }

    // n>=1
    private int dig(int n) {
        int r = 0;
        while (n > 0) {
            r++;
            n /= 10;
        }
        return r;
    }
}

/*
LC#233
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {
    // count 1s on 1, 10, 100th digit, etc
    public int countDigitOne(int n) {
        int t = 1; // can expand to any digit
        long res = 0;
        for (long base = 10; base / 10 <= n; base *= 10) {
            long stub = base / 10;
            long mybase = n / base * base + stub*t;
            long curdigit = ((n / stub) % 10);
            if (curdigit == t) {
                res += n - mybase + 1;
                // floor means we don't count this 1 but instead only count 1s below it. for example if n is 11, then we only count one 1 on tenth digit
                res += Math.floor(n * 1.0 / base) * stub;
            }
            else if (curdigit <t ) {
                // if it's a 0 same treatment on previous 1s
                res += Math.floor(n * 1.0 / base) * stub;
            }
            else {
                // if it's a 2 or above we know we can include corresponding 1s
                res += Math.ceil(n * 1.0 / base) * stub;
            }
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfDigitOne().countDigitOne(312));
    }
}

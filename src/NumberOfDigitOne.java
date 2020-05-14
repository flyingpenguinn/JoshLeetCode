/*
LC#233
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {
    public int countDigitOne(int n) {
        long r = 0;
        long base = 1;
        long stub = 0;
        while (n >= base) {
            int dig = (int) (n / base) % 10;
            stub = dig * base + stub;
            long ones = 0;
            if (dig != 1) {
                ones = ((n - base) / (base * 10) + 1) * base;
                // 232->( 232-10)/100+1 = 3 counts of ten's digit:10,110,210
                // note we -base so that 10->0,12->1 at one's digit
            } else {
                ones = ((n - base) / (base * 10)) * base;
                // no +1 ! 111-> (111-10)/100= 1 counts of ten's digit. as if we are treating 101 at ten's digit
                ones += stub - base + 1;
                // plus 11-10+1=2 counts at ten's digit
            }
            r += ones;
            base *= 10;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfDigitOne().countDigitOne(312));
    }
}

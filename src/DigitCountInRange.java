/*
LC#1067

Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds, respectively. Return the number of times that d occurs as a digit in all integers between low and high, including the bounds low and high.


Example 1:

Input: d = 1, low = 1, high = 13
Output: 6
Explanation:
The digit d=1 occurs 6 times in 1,10,11,12,13. Note that the digit d=1 occurs twice in the number 11.
Example 2:

Input: d = 3, low = 100, high = 250
Output: 35
Explanation:
The digit d=3 occurs 35 times in 103,113,123,130,131,...,238,239,243.


Note:

0 <= d <= 9
1 <= low <= high <= 2×10^8
 */
public class DigitCountInRange {
    // c>d, ==, or <d different formulas. take care of 0 by removing its impact
    public int digitsCount(int d, int low, int high) {
        int hc = count(high, d);
        int lc = count(low - 1, d);
        return hc - lc;
    }

    private int count(int n, int d) {
        if (n < 0) {
            return 0;
        }
        String sn = String.valueOf(n);
        int base = 1;
        int r = 0;
        int last = sn.length() - 1;
        for (int i = last; i >= 0; i--) {
            int b10 = base * 10;
            int cind = sn.charAt(i) - '0';
            int higher = n / b10;
            int cur = 0;
            if (cind < d) {
                //d==1, base = 100, 12013--> 12 *100 times
                cur = higher * base;
            } else if (cind > d) {
                // d==1, base = 100, 12213--> 13*100 times. here it's n/tb+1
                cur = (higher + 1) * base;
            } else {
                // cind==d
                // d==1, base = 100, 12113-> 12 *100 times, +113+1
                int level = (n / base) * base;
                cur = higher * base;
                cur += (n - level + 1);
            }
            // if d is 0 and we are not at the last digit, -base to remove the impact of the extra 00xxxx calculation
            if (d == 0 && i != last) {
                cur -= base;
            }
            r += cur;
            base *= 10;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new DigitCountInRange().digitsCount(0, 625, 1250));
        System.out.println(new DigitCountInRange().digitsCount(0, 1, 10));
        System.out.println(new DigitCountInRange().digitsCount(0, 1, 100));
        System.out.println(new DigitCountInRange().digitsCount(1, 1, 13));


    }
}

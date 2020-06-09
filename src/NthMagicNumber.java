/*
LC#878
A positive integer is magical if it is divisible by either A or B.

Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.



Example 1:

Input: N = 1, A = 2, B = 3
Output: 2
Example 2:

Input: N = 4, A = 2, B = 3
Output: 6
Example 3:

Input: N = 5, A = 2, B = 4
Output: 10
Example 4:

Input: N = 3, A = 6, B = 4
Output: 8


Note:

1 <= N <= 10^9
2 <= A <= 40000
2 <= B <= 40000
 */
public class NthMagicNumber {
    // solution is in long range
    // use principle of inclusion/exclusion
    public int nthMagicalNumber(int n, int a, int b) {
        long l = 1;
        long u = Long.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long nth = nth(a, b, mid);
            if (nth >= n) {
                // can't return when nth==n: we could be at some number > the real solution. need u=mid-1 to squeeze out the solution
                // like kth in sorted matrix or multiplication table
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) (l % 1000000007);
    }

    long nth(long a, long b, long m) {
        return m / a + m / b - m / lcm(a, b);
    }

    long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static void main(String[] args) {
        System.out.println(new NthMagicNumber().nthMagicalNumber(4, 2, 3));

        System.out.println(new NthMagicNumber().nthMagicalNumber(7, 6, 4));
        System.out.println(new NthMagicNumber().nthMagicalNumber(1, 2, 3));


        System.out.println(new NthMagicNumber().nthMagicalNumber(5, 2, 4));
        System.out.println(new NthMagicNumber().nthMagicalNumber(3, 6, 4));

        System.out.println(new NthMagicNumber().nthMagicalNumber(1000000000, 39999, 40000));
    }
}

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
    int Mod = 1000000007;

    // first use lcm to eat n in chunks, then deal with the numbers between lcms. lcm is an int so it becomes binary searchable
    public int nthMagicalNumber(long n, long a, long b) {
        if (a > b) {
            return nthMagicalNumber(n, b, a);
        }
        // a<=b
        if (b % a == 0) {
            return (int) ((n * a) % Mod);
        }
        long gcd = gcd(a, b);
        long lcm = a * b / gcd;
        long ad = lcm / a;
        long bd = lcm / b;
        long ld = ad + bd - 1;
        long chunks = n / ld; // lcm in int range, can binary search
        long rem = (n % ld);// ld i int range
        long p1 = lcm * chunks % Mod;
        long p2 = get(rem, a, b, lcm);
        return (int) ((p1 + p2) % Mod);

    }

    private long get(long t, long a, long b, long lcm) {
        if (t == 0) {
            return 0;
        }
        long l = a;
        long u = lcm;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long th = mid / a + mid / b - mid / lcm;
            if (th < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
LC#479
Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.



Example:

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987



Note:

The range of n is [1,8].
 */
public class LargestPalindromeProduct {
    // for palindrome questions going from the stem half string is usually a good strategy. here we concat the palin string from stem and
    // check if it can be factored
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        // when n>=2 the largest palin must be of len 2n and is concatenated by two halves
        long base = (long) Math.pow(10, n);
        long start = base - 1L;
        for (long i = start; i >= base / 10L; i--) {
            long ri = revese(i);
            long num = i * base + ri;
            if (isproduct(num, start)) {
                return (int) (num % 1337);
            }
        }
        return -1;
    }

    private long revese(long n) {
        long r = 0;
        while (n > 0) {
            r = r * 10 + n % 10;
            n /= 10;
        }
        return r;
    }

    private boolean isproduct(long num, long upperbound) {
        long sqrt = (long) Math.sqrt(num);
        for (long j = sqrt; num / j <= upperbound; j--) {
            long mod = num % j;
            if (mod == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new LargestPalindromeProduct().largestPalindrome(8));
    }
}

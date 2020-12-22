/*
LC#400
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class NthDigit {
    // find len
    // find the number x
    // find the digit we need
    public int findNthDigit(int n) {
        if (n <= 0) {
            return -1;
        }
        long base = 1;
        int len = 1;
        long walked = 0;
        while (walked + 9 * base * len < n) {
            walked += 9 * base * len;
            base *= 10;
            len++;
        }
        n -= walked;
        // length equals len
        long passed = (n - 1) / len;
        long num = base + passed;
        n -= passed * len;
        String sn = String.valueOf(num);
        return sn.charAt(n - 1) - '0';
    }
}

class NthDigitBinarySearch {
    // find the first u that is having digits count <n. next number u+1 is >-n
    public int findNthDigit(long n) {
        long l = 1;
        long u = Integer.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long count = count(mid);
            //    System.out.println(mid+" "+count);
            if (count >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
// u is the number that is the first <, l is the first >=
        long cu = count(u);
        int rem = (int) (n - cu);
        return String.valueOf(u + 1).charAt(rem - 1) - '0';
    }

    private long count(long n) {
        long base = 9;
        long basemax = 9;
        int digits = 1;
        long res = 0;
        // base: 9, 90, 900, ....
        // basemax; 9, 99, 999...
        while (basemax <= n) {
            res += base * digits;
            base = base * 10;
            basemax = basemax * 10 + 9;
            digits++;
        }
        // basemax >n. go back to previous base, but digits = current one. we are < current one's limit
        basemax = (basemax - 9) / 10;
        long diff = (n - basemax) * digits;
        res += diff;
        return res;
    }
}
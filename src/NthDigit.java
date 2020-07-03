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
        int len = 1;
        long base = 1;
        long count = 0;
        while (count + len * 9 * base < n) {
            // 1*9*1, 2*9*10, 3*9*100, etc
            // looking for the first >=, this is the len we are looking for
            count += len * 9 * base;
            len++;
            base *= 10;
        }
        n -= count;
        // must be of length = len, using base = base
        int x = (int) ((n - 1) / len + base);
        String sx = String.valueOf(x);
        return sx.charAt((n - 1) % len) - '0';
    }
}

class NthDigitBinarySearch {
    public int findNthDigit(int n) {
        // n positive
        int l = 1;
        int u = Integer.MAX_VALUE;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            // number of digits till mid, inclusive
            long nth = nth(mid);
            if (nth >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // u is the first number that including it, we have <n digits
        int countu = (int) nth(u);
        String str = String.valueOf(u + 1);
        return (str.charAt(n - countu - 1) - '0');
    }

    private long nth(int num) {
        long r = 0;
        long base = 1;
        long len = 1;
        while (base <= num) {
            if (num / base > 9) {
                r += len * 9 * base;
            } else {
                r += len * (num - base + 1);
            }
            base *= 10;
            len++;
        }
        return r;
    }
}

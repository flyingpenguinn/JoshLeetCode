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
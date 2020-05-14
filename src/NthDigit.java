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
    public int findNthDigit(int n) {
        int l = 1;
        int u = Integer.MAX_VALUE;
        while (l <= u) {
            int m = l + (u - l) / 2;
            long count = count(m);
            if (count <= n) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        // u is the last num whose first dig is the <= nth
        long count = count(u);
        String sn = String.valueOf(u);
        return sn.charAt((int) (n - count)) - '0';
    }

    long count(int n) {
        long base = 1;
        long bc = 1;
        long r = 0;
        while (base * 10 <= n) {
            r += (base * 9) * bc;
            bc++;
            base *= 10;
        }
        r += (n - base) * bc + 1;
        //  System.out.println(n+".."+r);
        return r;
    }
}

/*
LC#1318
Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.



Example 1:



Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
Example 2:

Input: a = 4, b = 2, c = 7
Output: 1
Example 3:

Input: a = 1, b = 2, c = 3
Output: 0


Constraints:

1 <= a <= 10^9
1 <= b <= 10^9
1 <= c <= 10^9
 */
public class MinFlipToMakeAorBEqualC {

    public int minFlips(int a, int b, int c) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            int ia = (a >> i) & 1;
            int ib = (b >> i) & 1;
            int ic = (c >> i) & 1;
            if (ic == (ia | ib)) {
                continue;
            } else if (ic == 1) {
                // must be 0 | 0; flip any of them
                r++;
            } else {
                // c==0
                // either 1|1 or 1|0
                if (ia == 1 && ib == 1) {
                    r += 2;
                } else {
                    r++;
                }
            }
        }
        return r;
    }
}

/*
LC#201
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 */
public class BitwiseAndInRange {
    // cant just do m and n check:it could be 1->0->1 like in 5->7
    public int rangeBitwiseAnd(int m, int n) {
        int r = 0;
        int rem = 0; // m from 0...i-1
        for (int i = 0; i < 32; i++) {
            int bm = (m >> i) & 1;
            int bn = (n >> i) & 1;
            if (bm == 0 || bn == 0) {
                continue;
            }
            // xx[1]00 to xx[1]11 is ok
            // 100 to 1111 is not ok
            if (n - m < (1 << i)) {
                r |= (1 << i);
            }
        }
        return r;
    }


    public static void main(String[] args) {
        System.out.println(new BitwiseAndInRange().rangeBitwiseAnd(5, 5));
        System.out.println(new BitwiseAndInRange().rangeBitwiseAnd(5, 7));
        System.out.println(new BitwiseAndInRange().rangeBitwiseAnd(0, 0));
    }
}

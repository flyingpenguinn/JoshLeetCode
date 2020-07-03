/*
LC#441
You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */
public class ArrangeCoins {
    /*
    Math sol:
     public int arrangeCoins(int n) {
        double sol = (Math.sqrt(1.0+8.0*n) -1)/2.0;
        return (int) sol;
    }

     */
    public int arrangeCoins(int n) {
        long l = 1L;
        long u = n;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long steps = mid * (mid + 1) / 2;
            if (steps <= n) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return (int) u;
    }

    boolean good(long st, int n) {
        return (st + 1) * st / 2 <= n;
    }
}

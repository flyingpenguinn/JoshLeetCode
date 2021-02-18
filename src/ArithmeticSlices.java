/*
LC#413
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] a) {
        int n = a.length;
        int lcount = 0;
        // last count. how many len>=3 arith seq ended at i-1. could be 0 if no good one formed
        int res = 0;
        for (int i = 2; i < n; i++) {
            int ncount = 0;
            if (a[i] - a[i - 1] == a[i - 1] - a[i - 2]) {
                // a new 3 elem one.
                // if a[i-1] can't form anything it's 0 anyway
                ncount = 1 + lcount;
            }
            res += ncount;
            lcount = ncount;
        }
        return res;
    }
}

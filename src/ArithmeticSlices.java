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
        int last = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            int d = a[i] - a[i - 1];
            int cur = 0;
            if (i - 2 >= 0 && a[i] - a[i - 1] == a[i - 1] - a[i - 2]) {
                cur = last + 1;
            } else {
                cur = 2; // if not equal then this starts a 2-len slice
            }
            r += cur - 2;// if cur=3, +1. if cur==4, +2
            last = cur;
        }
        return r;
    }
}

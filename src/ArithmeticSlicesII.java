import java.util.HashMap;
import java.util.Map;

/*
LC#446
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
 */
public class ArithmeticSlicesII {
    //dp array has all the len=2s. so we need that +1 in dp array
    // when we add res we take the -1 at each j because we are counting an extra len =2 in dp, and we need to deduct that
    public int numberOfArithmeticSlices(int[] a) {
        int n = a.length;
        Map<Integer, Map<Long, Long>> dp = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                long diff = 0L + a[i] - a[j];
                long dpv = 1;
                if (dp.containsKey(j)) {
                    dpv = dp.get(j).getOrDefault(diff, 0L) + 1;
                }
                Map<Long, Long> cm = dp.getOrDefault(i, new HashMap<>());
                cm.put(diff, cm.getOrDefault(diff, 0L) + dpv);
                dp.put(i, cm);
                res += dpv - 1;
            }
        }
        return (int) res;
    }
}

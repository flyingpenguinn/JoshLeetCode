/*
LC#634
In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.

There's originally an array consisting of n integers from 1 to n in ascending order, you need to find the number of derangement it can generate.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
Note:
n is in the range of [1, 106].
 */

public class DearrangementOfArray {

    /* use swapping concept
    For each of the situation above, let's say value n is put on index i, then we need to discuss about where we put value i:
1.if value i is put on index n (looks like value i and value n swapped their positions),
then we can just ignore value i, value n, index i, index n, what's left are n-2 different values and n-2 different indexes,
the problem becomes D(n-2).
2.if value i is not put on index n, then what's left are n-1 different values and n-1 different indexes,
each value has an index that it can not be put on. (value i can not be put on index n here) So the problem becomes D(n-1).

Therefore, D(n) = (n-1) [D(n-2) + D(n-1)].

similar to airplane seat assignment problem
     */
    long Mod = 1000000007;

    public int findDerangement(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        long n1 = 0;
        long n2 = 1;
        for (int j = 3; j <= n; j++) {
            long psum = (n1 + n2) % Mod;
            long rj = ((j - 1) * psum) % Mod;
            n1 = n2;
            n2 = rj;
        }
        return (int) n2;
    }
}

class DerangementFormula {
    // n!/2! - n!/3+ n!/4!...
    long Mod = 1000000007;

    public int findDerangement(int n) {
        long r = 0;
        long base = 1;
        for (int i = n; i >= 2; i--) {
            int sign = i % 2 == 0 ? 1 : -1;
            r += sign * base;
            r = (r + Mod) % Mod;
            base *= i;
            base %= Mod;
        }
        return (int) r;
    }
}

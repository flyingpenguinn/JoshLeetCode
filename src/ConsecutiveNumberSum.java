/*
LC#829
Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
Example 3:

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
Note: 1 <= N <= 10 ^ 9.
 */
public class ConsecutiveNumberSum {
    // given how many items are there
    // (a1+a1+x)*(x+1)/2 = n, so 2*n/(x+1) - x = 2a1 => 2n/(x+1) - (x+1) +1 = 2a1
    // only need to enumerate x+1 up to sqrt(2n)
    // all int solutions are counted
    public int consecutiveNumbersSum(int n) {
        int n2 = 2 * n;
        int res = 0;
        for (int x = 0; (x + 1) * (x + 1) <= n2; x++) {
            if (n2 % (x + 1) == 0) {
                if (good(x + 1, n2)) {
                    res++;
                }
                // dont really need to worry the other half because if x+1 > sqrt then the left hand will be <=0
            }
        }
        return res;
    }

    private boolean good(int a, int n2) {
        return (n2 / a - a + 1) % 2 == 0;
    }
}

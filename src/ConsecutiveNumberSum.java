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
    // given n we know a1
    // a1= (2*t-n(n+1))/2(n+1)
    // each int solution is counted.find it solution by %
    public int consecutiveNumbersSum(int s) {
        int r = 0;
        for (int i = 2; i * (i - 1) / 2 < s; i++) {
            int diff = s - i * (i - 1) / 2;
            if (diff % i == 0) {
                r++;
            }
        }
        return r + 1;
    }
}

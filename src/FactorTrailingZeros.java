/*
LC#172
Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
 */
public class FactorTrailingZeros {
    // each 5 gives one 0. there are way more 2s so we just need 5 contributor
    // each 25 contributes 2 0s, one more than 5
    public int trailingZeroes(int n) {
        int r = 0;
        long base = 5L;
        while (base <= n) {
            r += n / base; // 25 is +1 on top of 5
            base *= 5;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new FactorTrailingZeros().trailingZeroes(1808548329));
    }
}

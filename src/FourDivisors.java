/*
LC#1390
Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.

If there is no such integer in the array, return 0.



Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.


Constraints:

1 <= nums.length <= 10^4
1 <= nums[i] <= 10^5
 */
public class FourDivisors {

    public int sumFourDivisors(int[] a) {
        int n = a.length;
        int r = 0;
        for (int i = 0; i < n; i++) {
            int cur = div(a[i]);
            r += cur;
        }
        return r;
    }

    private int div(int n) {
        int count = 0;
        int sum = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
                sum += i;
                if (i * i < n) {
                    count++;
                    sum += n / i;
                }
                if (count >= 5) {
                    return 0;
                }
            }
        }
        return count == 4 ? sum : 0;
    }
}

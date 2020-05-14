/*

LC#1362
Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.

Return the two integers in any order.



Example 1:

Input: num = 8
Output: [3,3]
Explanation: For num + 1 = 9, the closest divisors are 3 & 3, for num + 2 = 10, the closest divisors are 2 & 5, hence 3 & 3 is chosen.
Example 2:

Input: num = 123
Output: [5,25]
Example 3:

Input: num = 999
Output: [40,25]


Constraints:

1 <= num <= 10^9
 */
public class ClosestDivisors {
    public int[] closestDivisors(int num) {
        int[] c1 = c(num + 1);
        int[] c2 = c(num + 2);
        if (Math.abs(c1[0] - c1[1]) < Math.abs(c2[0] - c2[1])) {
            return c1;
        } else {
            return c2;
        }
    }

    private int[] c(int n) {
        int a = 0;
        int b = n;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if ((n / i) - i < (b - a)) {
                    a = i;
                    b = n / i;
                }
            }
        }
        return new int[]{a, b};
    }
}

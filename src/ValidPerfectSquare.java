/*
LC#367
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int n) {
        long l = 1;
        long u = n;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long sq = mid * mid;
            if (sq == n) {
                return true;
            } else if (sq < n) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return false;
    }
}

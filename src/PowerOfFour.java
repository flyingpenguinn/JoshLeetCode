/*
LC#342
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true
Example 2:

Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {
    // equally good
    // return n>0 && ((n&(n-1))==0) && ((n-1)%3==0);
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        return (n & (n - 1)) == 0 && sqrt * sqrt == n;
    }
}

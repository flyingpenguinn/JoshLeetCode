/*
LC#326
Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        // can use this to do any prime number
        if (n <= 0) {
            return false;
        }
        return 1162261467 % n == 0;

    }
}

class PowerOfThreeWithLog {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        double log3 = Math.log(n) / Math.log(3);
        int cur = (int) Math.round(log3); // need to round it to avoid 4.999999
        long curpow = (long) Math.pow(3, cur);
        return curpow == n;
    }
}

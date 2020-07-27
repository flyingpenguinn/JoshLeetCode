/*
LC#258
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:

Input: 38
Output: 2
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 */
public class AddDigits {

    // Digital_root#Congruence_formula
    // number %9 == digit sum %9
    public int addDigits(int n) {
        if (n == 0) {
            return 0;
        }
        if (n % 9 == 0) {
            // 9,18...
            return 9;
        }
        return n % 9;
    }
}

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
    public int addDigits(int n) {
        return 1 + (n - 1) % 9;
    }

    // better than this...
    /*
   public int addDigits(int n) {
        while(n>=10){

        int r=0;
        while(n>0){
            r+= n%10;
            n/=10;
        }
            n=r;
        }
        return n;
    }
     */
}

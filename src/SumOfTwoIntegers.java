/*
LC#371
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1
 */
public class SumOfTwoIntegers {
    // a^b is all the plain + results. a&b is the carries. carry <<1 will move it to the position to add with xor result again
    public int getSum(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = a & b;
            b = (carry << 1);
            a = sum;
        }
        return a;
    }
}

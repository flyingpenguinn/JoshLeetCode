
/*
LC#29
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
public class DivideTwoIntegers {
    // a = x*b +y => a = x*(b+b) + y` and then transform the 2nd x to the 1st x
    // somewhat similar to binary lifting concept
    public int divide(int a, int b) {
        // b != 0
        if(a==0){
            return 0;
        }
        if(b==-1 && a==Integer.MIN_VALUE){
            return Integer.MAX_VALUE; // only possible overflow
        }
        if(a<0 && b>0){
            return -div(a, -b)[0];
        }
        if(a>0 && b<0){
            return -div(-a, b)[0];
        }
        if(a>0 && b>0){
            return div(-a, -b)[0];
        }
        return div(a,b)[0];
    }

    private int[] div(int a, int b){
        // a and b both <0 to avoid overflow
        if(a>b){
            // -2 vs -3, return 0, -2
            return new int[]{0, a};
        }
        if(a==b){
            return new int[]{1,0};
        }
        if(b<Integer.MIN_VALUE-b){
            // -1028 vs -1000 and -1024 is the limit, return 1, -28
            return new int[]{1, a-b};
        }
        int[] later = div(a, b+b);
        int mod = later[1];
        int quot = later[0];
        if(mod <=b){ 
            return new int[]{quot+quot+1, mod-b};
        }else{
            return new int[]{quot+quot, mod};
        }
    }

    public static void main(String[] args) {
        System.out.println(new DivideTwoIntegers().divide(-7, -2));
    }
}

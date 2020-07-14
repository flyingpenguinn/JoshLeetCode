/*
LC#50
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
 */
public class PowerXn {
    public double myPow(double x, int n) {
        return doPow(x, n);
    }

    // long in case -n overflows....
    private double doPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        // must do after ==0 because 1/0 is nan
        if (n < 0) {
            return 1.0 / doPow(x, -n);
        }
        double half = doPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }

    public static void main(String[] args) {
        PowerXn pxn = new PowerXn();
        System.out.println(pxn.myPow(2.0, 8));
        System.out.println(pxn.myPow(2.0, -8));
        System.out.println(pxn.myPow(-2.0, -8));
        System.out.println(pxn.myPow(-2.0, 8));
        System.out.println(pxn.myPow(2.0, 7));
        System.out.println(pxn.myPow(2.0, -7));
        System.out.println(pxn.myPow(-2.0, -7));
        System.out.println(pxn.myPow(-2.0, 7));
    }

}

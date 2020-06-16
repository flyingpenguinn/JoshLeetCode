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

    // typical divide and conquer
    public double myPow(double x, int n) {
        return dopow(x, n);
    }

    double dopow(double x, long n){
        if(x==0){
            return 0;
        }
        else if(n==1){
            return x;
        }
        else if(n==0){
            return 1;
        }
        else if(n<0){
            return 1/dopow(x, -n);
        }else{
            double half = dopow(x, n/2);
            if(n%2==0){
                return half*half;
            }else{
                return half*half*x;
            }
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

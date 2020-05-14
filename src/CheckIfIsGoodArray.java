import java.io.IOException;

/*
LC#1250
Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.

Return True if the array is good otherwise return False.



Example 1:

Input: nums = [12,5,7,23]
Output: true
Explanation: Pick numbers 5 and 7.
5*3 + 7*(-2) = 1
Example 2:

Input: nums = [29,6,10]
Output: true
Explanation: Pick numbers 29, 6 and 10.
29*1 + 6*(-3) + 10*(-1) = 1
Example 3:

Input: nums = [3,6]
Output: false


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */
public class CheckIfIsGoodArray {
    // Beazout identity: ax+by=gcd
    public boolean isGoodArray(int[] a) {
        if (a.length == 1) {
            return a[0] == 1;
        }
        int gcd = gcd(a[0], a[1]);
        for (int i = 0; i < a.length; i++) {
            gcd = gcd(a[i], gcd);
            if (gcd == 1) {
                return true;
            }
        }
        return false;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public static void main(String[] args) throws IOException {

    }

    void test(int a, int b) {
        for (int i = 1; i < 100000; i++) {
            if ((1 - i * a) % b == 0) {
                System.out.println(i + " " + (1 - i * a) / b);
                break;
            }
        }

    }
}

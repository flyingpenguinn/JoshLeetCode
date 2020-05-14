/*
LC#1015
Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.

Return the length of N.  If there is no such N, return -1.



Example 1:

Input: 1
Output: 1
Explanation: The smallest answer is N = 1, which has length 1.
Example 2:

Input: 2
Output: -1
Explanation: There is no such positive integer N divisible by 2.
Example 3:

Input: 3
Output: 3
Explanation: The smallest answer is N = 111, which has length 3.


Note:

1 <= K <= 10^5
 */
public class SmallestIntDivisibleByK {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0) {
            return -1;
        }

        int len = 1;
        int b = 1 % k;
        int a = 1 / k;
        // if all not 0 then could only be 1...k-1 different remainders.
        // if we start to see a duplicated remainder then because next remainder is set purely by current reminder we are doom to duplicate again
        while (len <= k) {
            if (b == 0) {
                return len;
            }
            int nrb = 10 * b + 1;
            b = nrb % k;
            a += nrb / k;
            len++;
        }
        return -1;
    }
}

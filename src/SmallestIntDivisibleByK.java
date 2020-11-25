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
    // from 11 to 111 we know how to transform the mod
    public int smallestRepunitDivByK(int k) {
        int mod = 0;
        int len = 0;
        while (len <= k) {
            // can also use a hashset, but there can be at most k different mods
            mod = (mod * 10 + 1) % k;
            len++;
            if (mod == 0) {
                return len;
            }
        }
        return -1;
    }
}

import java.util.Arrays;

/*
LC#1416
A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.

Given the string s and the integer k. There can be multiple ways to restore the array.

Return the number of possible array that can be printed as a string s using the mentioned program.

The number of ways could be very large so return it modulo 10^9 + 7



Example 1:

Input: s = "1000", k = 10000
Output: 1
Explanation: The only possible array is [1000]
Example 2:

Input: s = "1000", k = 10
Output: 0
Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.
Example 3:

Input: s = "1317", k = 2000
Output: 8
Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
Example 4:

Input: s = "2020", k = 30
Output: 1
Explanation: The only possible array is [20,20]. [2020] is invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros.
Example 5:

Input: s = "1234567890", k = 90
Output: 34


Constraints:

1 <= s.length <= 10^5.
s consists of only digits and doesn't contain leading zeros.
1 <= k <= 10^9.
 */
public class RestoreTheArray {
    // remember to break out!

    long[] dp;
    long Mod = 1000000007;

    public int numberOfArrays(String s, int k) {
        dp = new long[s.length()];
        Arrays.fill(dp, -1);
        int res = (int) don(0, s.toCharArray(), k);
        return res;
    }

    private long don(int i, char[] s, int k) {
        if (i == s.length) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        if (s[i] == '0') {
            dp[i] = 0;
        } else {
            long cur = 0;
            long ways = 0;
            int j = i;
            while (j < s.length && (cur * 10L + (s[j] - '0')) <= k) {
                cur = cur * 10L + (s[j] - '0');
                ways += don(j + 1, s, k);
                ways %= Mod;
                j++;
            }
            dp[i] = ways;
        }
        dp[i] %= Mod;
        return dp[i];
    }
}

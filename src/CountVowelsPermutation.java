/*
LC#1220
Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3:

Input: n = 5
Output: 68


Constraints:

1 <= n <= 2 * 10^4
 */


public class CountVowelsPermutation {
    int Mod = 1000000007;
    int[][] dp;

    // recursive dp may meet stack overflow locally
    // similar to knightdialer
    public int countVowelPermutation(int n) {
        long ways = 0;
        dp = new int[n][6];
        for (int i = 1; i <= 5; i++) {
            ways += doc(n, 0, i);
            ways %= Mod;
        }
        return (int) ways;
    }

    // a==1
    // e == 2;
    // i ==3;
    // o == 4;
    // u == 5

    private long doc(int n, int i, int type) {
        // ==1 at the last valid step
        if (i == n - 1) {
            return 1;
        }
        if (dp[i][type] != 0) {
            return dp[i][type];
        }
        long ways = 0;
        if (type == 1) {
            // a by e
            ways = doc(n, i + 1, 2);
        }
        if (type == 2) {
            // e by a or i
            ways = doc(n, i + 1, 1) + doc(n, i + 1, 3);
        }
        if (type == 3) {
            for (int k = 1; k <= 5; k++) {
                // anything non i
                if (k == 3) {
                    continue;
                }
                ways += doc(n, i + 1, k);
            }
        }
        if (type == 4) {
            // o by i or u
            ways = doc(n, i + 1, 3) + doc(n, i + 1, 5);
        }
        if (type == 5) {
            ways = doc(n, i + 1, 1);
        }
        ways = ways % Mod;
        dp[i][type] = (int) ways;
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(new CountVowelsPermutation().countVowelPermutation(20000));
    }
}

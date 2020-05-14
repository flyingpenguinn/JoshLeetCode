import java.util.Arrays;

/*
LC#1259
You are given an even number of people num_people that stand around a circle and each person shakes hands with someone else, so that there are num_people / 2 handshakes total.

Return the number of ways these handshakes could occur such that none of the handshakes cross.

Since this number could be very big, return the answer mod 10^9 + 7



Example 1:

Input: num_people = 2
Output: 1
Example 2:



Input: num_people = 4
Output: 2
Explanation: There are two ways to do it, the first way is [(1,2),(3,4)] and the second one is [(2,3),(4,1)].
Example 3:



Input: num_people = 6
Output: 5
Example 4:

Input: num_people = 8
Output: 14


Constraints:

2 <= num_people <= 1000
num_people % 2 == 0
 */
public class HandshakeThatDontCross {

    // similar to polygon cut problems...
    int[] dp;
    int Mod = 1000000007;

    public int numberOfWays(int n) {
        dp = new int[n + 2];
        Arrays.fill(dp, -1);
        return don(n);
    }

    int don(int n) {
        if (n % 2 == 1) {
            return 0;
        }
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        long ws = 0L;
        for (int j = 2; j <= n; j += 2) {
            int w1 = don(j - 2);
            int w2 = don(n - j);
            ws += 1L * (w1 == 0 ? 1 : w1) * (w2 == 0 ? 1 : w2);
            ws %= Mod;
        }
        dp[n] = (int) ws;
        return dp[n];
    }
}

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/*
LC#464
In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
 */
public class CanIwin {
    // t included in st,no need to cache
    int[] dp;

    public boolean canIWin(int n, int t) {
        if (t == 0) {
            return true;
        }
        if (n * (n + 1) / 2 < t) {
            return false;
        }
        dp = new int[1 << n];
        return doc(0, n, t) == 1;
    }

    int doc(int st, int n, int t) {
        if (t <= 0) {
            return 2;
        }
        if (st == (1 << n) - 1) {
            return 2;
        }
        if (dp[st] != 0) {
            return dp[st];
        }
        for (int i = 1; i <= n; i++) {
            if (((st >> (i - 1)) & 1) == 1) {
                continue;
            }
            int nst = st | (1 << (i - 1));
            int later = doc(nst, n, t - i);
            if (later == 2) {
                dp[st] = 1;
                return 1;
            }
        }
        dp[st] = 2;
        return 2;
    }

    public static void main(String[] args) {
        System.out.println(new CanIwin().canIWin(10, 11));
    }
}

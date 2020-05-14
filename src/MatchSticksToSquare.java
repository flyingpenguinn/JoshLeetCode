import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/*
LC#473
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
 */
public class MatchSticksToSquare {
    // classical set based dp to convert permutatoin to subset problem
    // for subset sum and other pseudo poly algo if the number range is large but set is small, we dp on set. the number range is hidden in set
    // almost the same as LC#698!
    int[][] dp;

    public boolean makesquare(int[] a) {
        int r = 0;
        int n = a.length;
        if (n < 4) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            r += a[i];
        }
        if (r % 4 != 0) {
            return false;
        }
        int t = r / 4;
        dp = new int[n][(1 << n)];
        return dom(a, 0, 0, 4, t, t) == 1;
    }

    // re, rl hidden in st. for pseudo poly problems if the number range is high, we dp on the set itself
    int dom(int[] a, int i, int st, int re, int rl, int t) {
        int n = a.length;
        if (rl == 0) {
            // must find from the start when we look for a new edge
            return dom(a, 0, st, re - 1, t, t);
        }
        if (re == 0) {
            // we are done and used all edges
            return 1;
        }
        if (i == n) {
            // not used up all edges but we used up all sticks
            return 2;
        }
        if (dp[i][st] != 0) {
            return dp[i][st];
        }
        int rt = 0;
        int nouse = dom(a, i + 1, st, re, rl, t);
        if (nouse == 1) {
            rt = 1;
        } else if (((st >> i) & 1) == 0 && rl - a[i] >= 0) {
            int nst = (st | (1 << i));
            rt = dom(a, i + 1, nst, re, rl - a[i], t);
        }
        dp[i][st] = rt;
        return rt;
    }
}

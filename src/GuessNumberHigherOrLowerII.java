/*
LC#375
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 */
public class GuessNumberHigherOrLowerII {

    // actually similar to super egg drop
    // can't simplify to dp(n) because money spent is related to how many steps we spent in the range too
    int[][] dp;

    public int getMoneyAmount(int n) {
        dp = new int[n + 1][n + 1];
        int rt = dog(1, n);
        return rt;
    }

    int dog(int l, int u) {
        if (l >= u) {
            return 0;
        }
        if (dp[l][u] != 0) {
            return dp[l][u];
        }
        int min = Integer.MAX_VALUE;
        // best one must be in the upper half because this will make the more expensive part shorter
        for (int i = l + (u - l) / 2; i <= u; i++) {
            int left = dog(l, i - 1);
            if (left >= min) {
                // left is only increasing...
                break;
            }
            int right = dog(i + 1, u);
            int cur = Math.max(left, right) + i;
            min = Math.min(min, cur);
        }
        dp[l][u] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherOrLowerII().getMoneyAmount(10));
    }
}

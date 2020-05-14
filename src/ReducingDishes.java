import java.util.Arrays;

/*
LC#1402
A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]

Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.



Example 1:

Input: satisfaction = [-1,-8,0,5,-9]
Output: 14
Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.
Example 2:

Input: satisfaction = [4,3,2]
Output: 20
Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
Example 3:

Input: satisfaction = [-1,-4,-5]
Output: 0
Explanation: People don't like the dishes. No dish is prepared.
Example 4:

Input: satisfaction = [-2,5,-1,0,3,-3]
Output: 35


Constraints:

n == satisfaction.length
1 <= n <= 500
-10^3 <= satisfaction[i] <= 10^3
 */
public class ReducingDishes {
    // typical knapsack...
    int[][] dp;

    public int maxSatisfaction(int[] a) {
        Arrays.sort(a);
        dp = new int[a.length + 1][a.length + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int rt = dom(a, 0, 1);
        return Math.max(rt, 0);
    }

    private int dom(int[] a, int i, int t) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][t] != -1) {
            return dp[i][t];
        }
        int with = a[i] * t + dom(a, i + 1, t + 1);
        int without = dom(a, i + 1, t);
        int rt = Math.max(with, without);
        dp[i][t] = rt;
        return rt;
    }
}

class ReducingDishesGreedy {
    // for every round we - the first item then - (sum-first item)
    public int maxSatisfaction(int[] a) {
        Arrays.sort(a);
        int sum = 0;
        int r = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            r += a[i] * (i + 1);
        }
        int max = Math.max(0, r);
        for (int i = 1; i < n; i++) {
            sum -= a[i - 1];
            r = r - sum - a[i - 1];
            max = Math.max(max, r);
        }
        return max;
    }
}
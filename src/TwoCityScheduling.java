/*
LC#1029
There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.



Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation:
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.


Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000
 */
public class TwoCityScheduling {
    int Max = 10000000;
    int[][] dp;

    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        dp = new int[n][n / 2 + 1];
        return dot(costs, 0, 0);
    }

    private int dot(int[][] costs, int i, int j) {
        int n = costs.length;
        if (i == n) {
            if (j == n / 2) {
                return 0;
            } else {
                return Max;
            }
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (j == n / 2) {
            dp[i][j] = costs[i][1] + dot(costs, i + 1, j);
        } else {

            int picka = costs[i][0] + dot(costs, i + 1, j + 1);
            int pickb = costs[i][1] + dot(costs, i + 1, j);
            dp[i][j] = Math.min(picka, pickb);
        }
        return dp[i][j];
    }
}

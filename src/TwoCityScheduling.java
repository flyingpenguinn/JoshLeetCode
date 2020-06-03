import java.util.Arrays;

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
    public int twoCitySchedCost(int[][] a) {
        // take a-b diff. those with the samllest diff (hence their b costs way more than a) go to a
        Arrays.sort(a, (x, y) -> Integer.compare(x[0] - x[1], y[0] - y[1]));
        int n = a.length;
        int r = 0;
        for (int i = 0; i < n / 2; i++) {
            r += a[i][0];
        }
        for (int i = n / 2; i < n; i++) {
            r += a[i][1];
        }
        return r;
    }
}

class TwoCitySchedulingDp {
    int Max = 10000000;
    int[][] dp;

    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        dp = new int[n][n + 1];
        return dot(costs, 0, 0);
    }

    private int dot(int[][] costs, int i, int j) {
        int n = costs.length;
        if (j > n / 2) {
            return Max;
        }
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

        int picka = costs[i][0] + dot(costs, i + 1, j + 1);
        int pickb = costs[i][1] + dot(costs, i + 1, j);
        dp[i][j] = Math.min(picka, pickb);

        return dp[i][j];
    }
}

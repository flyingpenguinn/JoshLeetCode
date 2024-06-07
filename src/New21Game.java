import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#837
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278
 */
public class New21Game {
    // basiclaly convertin the dp to iterative way
    // dp[i] = (dp[i+1]... dp[i+mp]) / mp
    // apply subarray sum on dp
    public double new21Game(int n, int k, int mp) {
        int limit = Math.max(k, n) + 1;
        double[] dp = new double[limit + 1];
        double[] sum = new double[limit + 1];

        for (int i = limit - 1; i >= k; --i) {
            dp[i] = i <= n ? 1.0 : 0.0;
            sum[i] = sum[i + 1] + dp[i];
        }

        for (int i = k - 1; i >= 0; --i) {
            int end = Math.min(i + mp + 1, limit);
            double csum = sum[i + 1] - sum[end];
            dp[i] = csum * 1.0 / mp;
            sum[i] = sum[i + 1] + dp[i];
        }

        return dp[0];
    }
}



class New21GameTle {
    // TLE but is the basis of the above approach
    private double[] dp;

    public double new21Game(int n, int k, int mp) {
        dp = new double[n + 1];
        Arrays.fill(dp, -1);
        return solve(0, mp, k, n);
    }

    private double solve(int cur, int mp, int k, int n) {
        if (cur >= k) {
            return cur <= n ? 1.0 : 0.0;
        }
        if (dp[cur] != -1) {
            return dp[cur];
        }
        double res = 0;
        for (int i = 1; i <= mp; ++i) {
            double cres = solve(cur + i, mp, k, n) * 1.0 / mp;
            res += cres;
        }
        dp[cur] = res;
        return res;
    }
}

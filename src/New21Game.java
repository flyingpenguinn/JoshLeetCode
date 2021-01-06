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
    // dp with a twist to get accumulated dp values in O1
    // note the definition of dp[i]: it is the prob of getting i at some point.
    // note when
    public double new21Game(int n, int k, int w) {
        double[] dp = new double[n + 1];
        double res = 0;
        double sum = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                dp[i] = 1;
            } else {
                dp[i] = sum / w;
            }
            if (i >= k) {
                // if >= it won't leave this number
                res += dp[i];
            } else {
                // only draw number when i <k
                sum += dp[i];
            }
            if (i - w >= 0) {
                // i-w will be out of range for i+1 so drop it
                sum -= dp[i - w];
            }
        }
        return res;
    }

}

class New21GameTLE {
    // raw form without optimization to on. note the way we filter bad i-j data and res+= dp[i]. also dp[0]=1. these are crucial
    public double new21Game(int n, int k, int w) {
        double[] dp = new double[n + 1];
        // dp[i] is the prob of getting i at some point. note when we get >=k we stop so these becomes the final prob too..
        double res = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                dp[i] = 1.0;  // !
            } else {
                for (int j = 1; j <= w; j++) {
                    if (i - j >= 0 && i - j < k) {  // !only <k can we try to get here. if i-j is already >k we stop
                        dp[i] += dp[i - j] / w;
                    }
                }
            }
            if (i >= k) {
                res += dp[i];
            }
        }
        return res;
    }
}

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
    double[] dp;

    public double new21Game(int n, int k, int w) {
        if (n < k) {
            return 0.0;
        }
        int lmt = Math.max(k + w, n);
        double[] dp = new double[lmt + 2];
        double[] psum = new double[lmt + 2]; // or can use a sliding window

        for (int i = k + w - 1; i > n; i--) {
            dp[i] = 0.0;
            psum[i] = psum[i + 1] + dp[i];// p[lmt]=0 anyway we made the buffer big enough
        }
        for (int i = n; i >= k; i--) {
            dp[i] = 1.0;
            psum[i] = psum[i + 1] + dp[i];
        }
        for (int i = k - 1; i >= 0; i--) {
            double cur = psum[i + 1] - psum[i + w + 1];
            // i+1...i+w
            dp[i] = cur / w;
            psum[i] = psum[i + 1] + dp[i];
        }
        return dp[0];
    }

}

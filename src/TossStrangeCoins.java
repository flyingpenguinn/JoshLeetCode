import java.util.Arrays;

/*

You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.

Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.



Example 1:

Input: prob = [0.4], target = 1
Output: 0.40000
Example 2:

Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
Output: 0.03125
 */
public class TossStrangeCoins {
    double[][] dp;

    public double probabilityOfHeads(double[] prob, int target) {
        dp = new double[prob.length][target + 1];
        for (int i = 0; i < prob.length; i++) {
            Arrays.fill(dp[i], -1.0);
        }
        return dop(0, prob, target);
    }

    // prob for target coins face head starting from i
    private double dop(int i, double[] prob, int target) {
        if (target < 0) {
            return 0;
        }
        if (i == prob.length) {
            return target == 0 ? 1.0 : 0.0;
        }
        if (dp[i][target] != -1.0) {
            return dp[i][target];
        }
        double head = prob[i] * dop(i + 1, prob, target - 1);
        double tail = (1 - prob[i]) * dop(i + 1, prob, target);
        double rt = head + tail;
        dp[i][target] = rt;
        return rt;
    }
}

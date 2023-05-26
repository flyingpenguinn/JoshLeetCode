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
    public double new21Game(int n, int k, int mp) {
// each i is contributed by max(1, i-mp)...min(i-1, k-1)
        if(n==0){
            return k==0? 1: 0;
        }
        if(k==0){
            return 1;
        }
        // dp [i] is the chance of hitting i
        // stop at k, so the most we can get is k-1+mp = k+mp-1
        double[] dp = new double[k+mp];
        double res = 0;
        double sum = 0;
        for(int i=1; i<=k+mp-1; i++){
            // for i it's controlled by i-mp...i-1
            dp[i] = sum*1.0/mp;
            if(i<=mp){
                // i <= mp then the number itself can be achieved
                dp[i] += 1.0/mp;
            }
            if(i>=k && i<=n){
                // if >=k we stop drawing so it will stay there
                res += dp[i];
            }
            if(i<k){
                //if i==k already we can't contribute
                sum += dp[i];
            }
            if(i-mp>=1){
                // take out the too old i-mp
                sum -= dp[i-mp];
            }
        }
        return res;
    }
}

/*
LC#754
You are standing at position 0 on an infinite number line. There is a goal at position target.

On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.
Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.
Note:
target will be a non-zero integer in the range [-10^9, 10^9].
 */
public class ReachANumber {
    // if we overshoot we can always only flip one number in the middle if the diff is even
    public int reachNumber(int t) {
        t = Math.abs(t);
        int step = 1;  // the step we are stepping next
        int sum = 0;
        while (sum < t) {
            sum += step++;
        }
        // now we have sum and next step is step
        if (sum == t) {
            return step - 1;
        } else if ((sum - t) % 2 == 0) {
            //(sum-t)/2 must be within 1...step because 1+2...n-1<t, 1+2+...n=sum >t. sum-t must be <=n
            return step - 1;
        } else if (step % 2 == 1) {
            // diff is odd. we add step, now diff would be even
            return step;
        } else {
            // otherwise we add step, then substract step-1. diff becomes even
            return step + 1;
        }
    }

}

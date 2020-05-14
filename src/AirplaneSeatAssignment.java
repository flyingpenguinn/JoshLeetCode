/*
LC#1227
n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of passengers will:

Take their own seat if it is still available,
Pick other seats randomly when they find their seat occupied
What is the probability that the n-th person can get his own seat?



Example 1:

Input: n = 1
Output: 1.00000
Explanation: The first person can only get the first seat.
Example 2:

Input: n = 2
Output: 0.50000
Explanation: The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).


Constraints:

1 <= n <= 10^5
 */

public class AirplaneSeatAssignment {
    // dp[i] means ith person gets their own seat. i.e. if either of 1...n-1 persons take their own seat we are good
    /*
    Let f(n) be the probability that the n-th passenger will get his own seat.
If the 1st passenger get the 1st seat, then everyone will get their own seats, so the n-th passenger gets his own seat with probability: 1/n
otherwise if he picks ith person's then that person can't sit on i. if he sits on 1, then no more swapping. otherwise keep going. so this is turned to f(n-1)
i is within 2...n-1 so overall n-2 numbers to pick from
hence f(n) = 1/n+(n-2)/n*f(n-1)  f(1)=1
     */
    public double nthPersonGetsNthSeat(int n) {
        double r = 1;
        for (int i = 2; i <= n; i++) {
            r = 1.0 / i + (i - 2.0) / i * r;
        }
        return r;
    }
}

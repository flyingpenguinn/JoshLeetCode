import base.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
LC#871
A car travels from a starting position to a destination which is target miles east of the starting position.

Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.

When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.



Example 1:

Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.
Example 2:

Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can't reach the target (or even the first gas station).
Example 3:

Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2
Explanation:
We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.


Note:

1 <= target, startFuel, stations[i][1] <= 10^9
0 <= stations.length <= 500
0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 */
public class MinimumNumberOfStopsToRefuel {
    // as if we can save previous stops for later credits...
    public int minRefuelStops(int t, int f, int[][] s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int p = 0;
        int ps = 0;
        int r = 0;
        while (ps < s.length) {
            if (p + f >= s[ps][0]) {
                f -= (s[ps][0] - p);
                p = s[ps][0];
                pq.offer(s[ps][1]);
                ps++;
            } else {
                if (pq.isEmpty()) {
                    return -1;
                } else {
                    int add = pq.poll();
                    f += add;
                    r++;
                }
            }
        }
        while (p + f < t && !pq.isEmpty()) {
            f += pq.poll();
            r++;
        }
        return p + f >= t ? r : -1;
    }

    public static void main(String[] args) {
        int target = 1000;
        int startFuel = 83;
        int[][] stations = ArrayUtils.read(" [[15,457],[156,194],[160,156],[230,314],[390,159],[621,20],[642,123],[679,301],[739,229],[751,174]]");
        System.out.println(new MinimumNumberOfStops2DDp().minRefuelStops(target, startFuel, stations));
    }
}

class MinNumRefuelDp {
    public int minRefuelStops(int t, int f, int[][] s) {
        int sn = s.length;
        // max dist can get with i stops
        int[] dp = new int[sn + 1];
        dp[0] = f;
        for (int i = 0; i < sn; i++) {
            // each station as the last station on top of j
            for (int j = i; j >= 0; j--) {
                // previously there were j refuels. must go from i to 0: we don't want to step o nthe j we just set!
                if (dp[j] >= s[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + s[i][1]);
                }
            }
        }
        for (int i = 0; i <= sn; i++) {
            if (dp[i] >= t) {
                return i;
            }
        }
        return -1;

    }
}

// a more intuitive dp version. note we used a max array to turn a 3-d dp into a 2d one
// @silu when we have t that is not enumerable, use fuel steps i to make it discrete
class MinimumNumberOfStops2DDp {
    public int minRefuelStops(int t, int f, int[][] s) {
        if (f >= t) {
            return 0;
        }
        int sn = s.length;
        // fuel at most sn times, ending at stop j-1, how far we can get
        int[][] dp = new int[sn + 1][sn + 1];
        for (int i = 0; i <= sn; i++) {
            int last = f;
            for (int j = 1; j <= sn; j++) {
                if (i == 0) {
                    // fuel 0 times we have f
                    dp[i][j] = f;
                } else if (j == 0) {
                    // using up to stop -1 means no refuel too...
                    dp[i][j] = f;
                } else {
                    // similar to stock buysell IV, we can avoid repeated 0...j-1 max calculation by cache the last max
                    if (last >= s[j - 1][0]) {
                        dp[i][j] = Math.max(dp[i][j], last + s[j - 1][1]);
                    }
                    last = Math.max(last, dp[i - 1][j]);
                    if (dp[i][j] >= t) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}

class MinNumberOfRefuelRawDp {
    public int minRefuelStops(int t, int f, int[][] s) {
        int sn = s.length;
        int[][] dp = new int[sn + 1][sn + 1];
        // fuel at most sn times, ending at stop j-1, how far we can get
        for (int i = 0; i <= sn; i++) {
            dp[i][0] = f;
        }
        for (int i = 1; i <= sn; i++) {
            for (int j = 1; j <= sn; j++) {
                for (int k = 0; k < j; k++) {
                    int last = dp[i - 1][k];
                    if (last >= s[j - 1][0]) {
                        dp[i][j] = Math.max(dp[i][j], last + s[j - 1][1]);
                    }
                }
                if (dp[i][j] >= t) {
                    return i;
                }
            }
        }
        return -1;
    }
}
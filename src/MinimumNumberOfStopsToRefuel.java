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
    public int minRefuelStops(int t, int start, int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int f = start;
        int n = a.length;
        int pos = 0;
        int j = 0;
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        while (pos < t) {
            int next = j == n ? t : a[j][0];
            int dist = next - pos;
            while (!pq.isEmpty() && f < dist) {
                res++;
                f += pq.poll();
            }
            if (f < dist) {
                return -1;
            }
            f -= dist;
            if (j < n) {
                pq.offer(a[j++][1]);
            }
            pos = next;
        }
        return res;
    }

    public static void main(String[] args) {


        System.out.println(new MinNumRefuelDp().minRefuelStops(1000, 83, ArrayUtils.read(" [[25,27],[36,187],[140,186],[378,6],[492,202],[517,89],[579,234],[673,86],[808,53],[954,49]]")));
    }
}

class MinNumRefuelDp {
    // shua biao fa....
    public int minRefuelStops(int t, int sf, int[][] a) {
        int n = a.length;
        int[] dp = new int[n + 1];
        // fuel i times, how far we can go
        dp[0] = sf;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // from the back because when we figure out j for this i we rely on i-1's dp[j]
                // if we do from 0 to i-1 we are using i's data to populate from j to j=1 which is incorrect
                // when we go from i-1 to 0, we use i-1's data to populate i
                if (dp[j] >= a[i - 1][0]) {
                    int nlen = dp[j] + a[i - 1][1];
                    dp[j + 1] = Math.max(dp[j + 1], nlen);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= t) {
                return i;
            }
        }
        return -1;
    }
}
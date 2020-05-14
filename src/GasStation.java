import base.ArrayUtils;

/*
LC#134
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input:
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
 */
public class GasStation {
    // consider max subarray and circular max. we can prove by contradiction that there won't be a more negative segment
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] d = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            d[i] = gas[i] - cost[i];
            sum += d[i];
        }
        if (sum < 0) {
            return -1;
        }
        // max subarray sum
        int segsum = 0;
        int maxsum = Integer.MIN_VALUE;

        int start = 0;
        int maxstart = 0;
        for (int i = 0; i < n; i++) {
            if (segsum < 0) {
                segsum = d[i];
                start = i;
            } else {
                segsum += d[i];
            }
            if (segsum > maxsum) {
                maxsum = segsum;
                maxstart = start;
            }
        }
        // max circular subarray sum
        int[] r = new int[n];
        r[n - 1] = d[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            r[i] = r[i + 1] + d[i];
        }
        int lsum = 0;
        int lmax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            lmax = Math.max(lsum, lmax);
            int csum = lmax + r[i];
            if (csum > maxsum) {
                maxsum = csum;
                maxstart = i;
            }
            lsum += d[i];
        }
        return maxstart;
    }

    public static void main(String[] args) {
        System.out.println(new GasStation().canCompleteCircuit(ArrayUtils.read1d("5,8,2,8"), ArrayUtils.read1d("6,5,6,6")));
    }
}

class GasStationSimplified {
    // if cur<0 can exclude from start to i:if a[start]<0,bad. if>0,it cant counter other negs. note when we were at i, cur was >=0
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = cost.length;
        int cur = gas[0] - cost[0];
        int all = cur;
        int start = 0;
        for (int i = 1; i < n; i++) {
            int diff = gas[i] - cost[i];
            all += diff;
            if (cur < 0) {
                cur = diff;
                start = i;
            } else {
                cur += diff;
            }
        }
        if (all < 0) {
            return -1;
        }
        return start;
    }
}

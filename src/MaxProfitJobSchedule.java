import base.ArrayUtils;

import java.util.*;

/*
LC#1235
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.



Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:




Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4
 */
public class MaxProfitJobSchedule {
    // dp[i] means max profit from i as starting point, may or may not pick i
    int[] dp;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        // sort by start
        Arrays.sort(jobs, (x, y) -> Integer.compare(x[0], y[0]));
        dp = new int[n];
        Arrays.fill(dp, -1);
        return doj(0, jobs);
    }

    int doj(int i, int[][] jobs) {
        int n = jobs.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int nodo = doj(i + 1, jobs);
        int next = binarysearch(jobs, i + 1, n - 1, jobs[i][1]);
        int dojob = jobs[i][2] + doj(next, jobs);
        int rt = Math.max(nodo, dojob);
        dp[i] = rt;
        return rt;
    }

    // first >= t. l could be n
    int binarysearch(int[][] jobs, int l, int u, int t) {
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (jobs[mid][0] >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {

        System.out.println(new MaxProfitJobSchedule().jobScheduling(ArrayUtils.read1d("[1,2,3,3]"),
                ArrayUtils.read1d("[3,4,5,6]"),
                ArrayUtils.read1d("[50,10,40,70]")));

        System.out.println(new MaxProfitJobSchedule().jobScheduling(ArrayUtils.read1d("[1,2,3,4,6]"),
                ArrayUtils.read1d("[3,5,10,6,9]"),
                ArrayUtils.read1d("[20,20,100,70,60]")));

        System.out.println(new MaxProfitJobSchedule().jobScheduling(ArrayUtils.read1d("[1,1,1]"),
                ArrayUtils.read1d("[2,3,4]"),
                ArrayUtils.read1d("[5,6,4]")));

        System.out.println(new MaxProfitJobSchedule().jobScheduling(ArrayUtils.read1d("[1,2,2,3]"),
                ArrayUtils.read1d("[2,5,3,4]"),
                ArrayUtils.read1d("[3,4,1,2]")));


        System.out.println(new MaxProfitJobSchedule().jobScheduling(ArrayUtils.read1d("[43,13,36,31,40,5,47,13,28,16,2,11]"),
                ArrayUtils.read1d("[44,22,41,41,47,13,48,35,48,26,21,39]"),
                ArrayUtils.read1d("[8,20,3,19,16,8,11,13,2,15,1,1]")));


        System.out.println(new MaxProfitJobSchedule().jobScheduling(ArrayUtils.read1d("[15,44,15,47,11,18,5,41,38,25,19,25]"),
                ArrayUtils.read1d("[33,48,20,49,37,22,32,48,39,37,38,40]"),
                ArrayUtils.read1d("[18,19,16,1,5,12,17,7,19,9,18,9]")));

    }
}

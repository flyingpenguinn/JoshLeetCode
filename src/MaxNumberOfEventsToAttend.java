import base.ArrayUtils;

import java.util.*;

/*
LC#1353

User Accepted:670
User Tried:2374
Total Accepted:705
Total Submissions:6609
Difficulty:Medium
Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.

Return the maximum number of events you can attend.



Example 1:


Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
Example 2:

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4
Example 3:

Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
Output: 4
Example 4:

Input: events = [[1,100000]]
Output: 1
Example 5:

Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
Output: 7


Constraints:

1 <= events.length <= 10^5
events[i].length == 2
1 <= events[i][0] <= events[i][1] <= 10^5
 */
public class MaxNumberOfEventsToAttend {
    // iterate on d, always select the event that ends the earliest
    public int maxEvents(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int j = 0;
        int r = 0;
        // this pq contains events that intersect with d
        PriorityQueue<int[]> dq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        for (int i = 1; i <= 100000; i++) {
            // events too early are skipped
            while (!dq.isEmpty() && dq.peek()[1] < i) {
                dq.poll();
            }
            // events that are good are enlisted
            while (j < a.length && a[j][0] <= i && a[j][1] >= i) {
                dq.offer(a[j++]);
            }
            if (!dq.isEmpty()) {
                r++;  // if we have events to visit
                dq.poll();
                // we attended, now this one can go away. note we attend the event that CLOSES the first. this id different from visit sequence
            }
        }
        return r;
    }
}

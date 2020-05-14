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
    public int maxEvents(int[][] e) {
        Arrays.sort(e, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        int r = 0;
        // pq contains those intersect with d
        for (int d = 1; d <= 100000; d++) {
            while (!pq.isEmpty() && d > pq.peek()) {
                // old events that ends before d fall out
                pq.poll();
            }
            while (i < e.length && e[i][0] <= d && e[i][1] >= d) {
                // what's in pq are those having intersection with d
                pq.offer(e[i][1]);
                i++;
            }
            // take the one that ends the earliest
            if (!pq.isEmpty()) {
                r++;
                pq.poll();
            }
        }
        return r;
    }
}

class MaxNumToAttendBruteForce {
    public int maxEvents(int[][] e) {
        Arrays.sort(e, (a, b) -> Integer.compare(b[1], a[1]));
        Set<Integer> taken = new HashSet<>();
        int r = 0;
        for (int i = 0; i < e.length; i++) {
            for (int j = e[i][0]; j <= e[i][1]; j++) {
                if (!taken.contains(i)) {
                    r++;
                    taken.add(i);
                }
            }
        }
        return r;
    }
}

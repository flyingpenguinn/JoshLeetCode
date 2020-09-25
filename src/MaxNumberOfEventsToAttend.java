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
    // on each day, we attend the event with the least end date
    // while we work on day, we "enable" more events. this makes this problem similar to max performance of team problem
    public int maxEvents(int[][] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i][1]);
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        // pq are end points of eligible events. small items first
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0;
        int j = 0;
        for (int i = 1; i <= max; i++) {
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            while (j < a.length && a[j][0] == i) {
                pq.offer(a[j++][1]);
            }
            if (!pq.isEmpty()) {
                res++;
                pq.poll(); // earlier end date comes out
            } else if (j == a.length) {
                break; // early break if q is empty and no more items to add...
            }
        }
        return res;
    }
}

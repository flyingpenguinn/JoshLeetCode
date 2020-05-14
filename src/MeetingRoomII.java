import base.ArrayUtils;
import base.Interval;

import java.util.*;

/*
LC#253
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRoomII {
    // use a tree map to count the start and end numbers. start +1, end -1, then count from small to big...
    public int minMeetingRooms(int[][] a) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] ai : a) {
            tm.put(ai[0], tm.getOrDefault(ai[0], 0) + 1);

            tm.put(ai[1], tm.getOrDefault(ai[1], 0) - 1);
        }
        int max = 0;
        int cur = 0;
        for (int v : tm.values()) {
            cur += v;
            max = Math.max(max, cur);
        }
        return max;
    }
}
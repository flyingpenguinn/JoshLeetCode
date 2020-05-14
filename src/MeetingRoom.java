import java.util.Arrays;
import java.util.TreeMap;

/*
LC#252
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true
 */
public class MeetingRoom {

    public boolean canAttendMeetings(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        for (int i = 1; i < a.length; i++) {
            if (a[i][0] < a[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}

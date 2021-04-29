import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 */
public class MeetingScheduler {
    // similar to IntervalListIntersection. note we are moving the one with smaller ending if there is an intersection
    public List<Integer> minAvailableDuration(int[][] a, int[][] b, int d) {
        int i = 0;
        int j = 0;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        Arrays.sort(b, (x, y) -> Integer.compare(x[0], y[0]));
        while (i < a.length && j < b.length) {
            if (a[i][0] > b[j][1]) {
                j++;
            } else if (b[j][0] > a[i][1]) {
                i++;
            } else {
                int start = Math.max(a[i][0], b[j][0]);
                int end = Math.min(a[i][1], b[j][1]);
                if (end - start >= d) {
                    return List.of(start, start + d);
                } else {
                    if (a[i][1] > b[j][1]) {
                        // move the slot with smaller end
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}

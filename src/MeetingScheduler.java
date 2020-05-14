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
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> r = new ArrayList<>();
        Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0;
        int j = 0;
        while (i < slots1.length && j < slots2.length) {
            int[] s1 = slots1[i];
            int[] s2 = slots2[j];
            if (s1[0] < s2[1] || s2[0] < s1[1]) {
                // trick to use for intersections
                int start = Math.max(s1[0], s2[0]);
                int end = Math.min(s1[1], s2[1]);
                if (end - start >= duration) {
                    r.add(start);
                    r.add(start + duration);
                    return r;
                }
            }
            if (s1[0] < s2[0]) {
                i++;
            } else {
                j++;
            }
        }
        return r;
    }
}

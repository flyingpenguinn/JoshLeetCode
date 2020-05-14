import base.ArrayUtils;

import java.io.*;
import java.util.*;

/*
LC#630
There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

Example:

Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3
Explanation:
There're totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.


Note:

The integer 1 <= d, t, n <= 10,000.
You can't take two courses simultaneously.

 */
public class CourseScheduleIII {
    // lots of false starts: dp will TLE due to O(n*t) complexity
    // cant just pick the shortest from all available ones, counter example: [5,100], [6,6],[6,12]. 5, 100 could have been started later
    // can't just pick the earliest must-start ones and use this algo. counter example: [[10,12],[6,15],[1,12],[3,20],[10,19]]. it throws away long periods too quickly
    // can't just pick the earliest end course: later ones maybe shorter. counter example: [10,11], [9,12],[8,17]. but we can swap 8,17 and 10,11
    // so have to sort by end time then swap longer periods out when we hit a snag like below. it's a variation of the standard max number interval selection problem that we apply swapping
    public int scheduleCourse(int[][] a) {
        // serve the ones that will close earlier, just like the classical interval problem
        Arrays.sort(a, (x, y) -> x[1] != y[1] ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
        int n = a.length;
        int time = 1;
        // longer ones first. we can swap out previous longer ones for this one, if this one is shorter. since it's shorter we can shorten the "time" hence allowing more courses
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        for (int i = 0; i < n; i++) {
            if (time + a[i][0] - 1 <= a[i][1]) {
                pq.offer(a[i]);
                time += a[i][0] - 1;
            } else if (!pq.isEmpty()) {
                int[] top = pq.peek(); // not poll!
                if (top[0] < a[i][0]) {
                    pq.poll();
                    pq.offer(a[i]);
                    time -= top[0] - a[i][0];
                }
            }
        }
        return pq.size();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new CourseScheduleIII().scheduleCourse(ArrayUtils.read("[[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]")));
        //   System.out.println(new CourseScheduleIII().scheduleCourse(ArrayUtils.read("[[10,12],[6,15],[1,12],[3,20],[10,19]]")));
    }
}

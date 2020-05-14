import base.Interval;

import java.util.*;

/*
LC#759
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]


Constraints:

1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTime {

    // just flatten to find holes. holes are global.
    // instead if we find individual holes first then we have to intersect them one by one...
    public List<Interval> employeeFreeTime(List<List<Interval>> a) {
        List<Interval> ints = new ArrayList<>();
        for (List<Interval> ai : a) {
            for (Interval aii : ai) {
                ints.add(aii);
            }
        }
        // no need to break tie
        Collections.sort(ints, (x, y) -> Integer.compare(x.start, y.start));
        List<Interval> r = new ArrayList<>();
        int maxend = ints.get(0).end;
        for (int i = 1; i < ints.size(); i++) {
            if (ints.get(i).start > maxend) {
                r.add(new Interval(maxend, ints.get(i).start));
            }
            maxend = Math.max(maxend, ints.get(i).end);

        }
        return r;
    }

    public static void main(String[] args) {
        List<Interval> p1 = List.of(new Interval(1, 3), new Interval(6, 7));
        List<Interval> p2 = List.of(new Interval(2, 4));
        List<Interval> p3 = List.of(new Interval(2, 5), new Interval(9, 12));
        List<List<Interval>> input = List.of(p1, p2, p3);
        List<Interval> r = new EmployeeFreeTime().employeeFreeTime(input);
        for (Interval ri : r) {
            System.out.println(ri.start + "," + ri.end);
        }
    }
}

// dont need to keep all intervals in the pq. just need the top one from each person
// similar to merging k sorted lists!
class EmployeeFreeTimeHeap {
    public List<Interval> employeeFreeTime(List<List<Interval>> a) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(a.get(x[0]).get(x[1]).start, a.get(y[0]).get(y[1]).start));
        // we store person/ith meeting in pq
        int n = a.size();
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{i, 0});
        }
        List<Interval> r = new ArrayList<>();
        int maxend = -1;

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            List<Interval> curlist = a.get(top[0]);
            Interval cur = curlist.get(top[1]);
            if (cur.start > maxend && maxend >= 0) {
                r.add(new Interval(maxend, cur.start));
            }
            maxend = Math.max(maxend, cur.end);
            if (top[1] + 1 < curlist.size()) {
                pq.offer(new int[]{top[0], top[1] + 1});
            }
        }
        return r;
    }
}
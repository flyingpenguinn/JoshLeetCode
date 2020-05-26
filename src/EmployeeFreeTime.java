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

class EmployeeFreeTimeAlternative {
    // find individual holes then use idea similar to merge sort to merge them
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<List<Interval>> list = new ArrayList<>();
        for (int i = 0; i < schedule.size(); i++) {
            list.add(free(schedule.get(i)));
        }
        List<Interval> r = domerge(0, list.size() - 1, list);
        List<Interval> rr = new ArrayList<>();
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).start == MIN || r.get(i).end == MAX || r.get(i).start == r.get(i).end) {
                continue;
            }
            rr.add(r.get(i));
        }
        return rr;
    }

    private List<Interval> domerge(int l, int u, List<List<Interval>> list) {
        if (l > u) {
            return new ArrayList<>();
        }
        if (l == u) {
            return list.get(l);
        }
        int mid = l + (u - l) / 2;
        List<Interval> p1 = domerge(l, mid, list);
        List<Interval> p2 = domerge(mid + 1, u, list);
        return merge(p1, p2);
    }

    private List<Interval> merge(List<Interval> p1, List<Interval> p2) {
        List<Interval> r = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < p1.size() && j < p2.size()) {
            Interval v1 = p1.get(i);
            Interval v2 = p2.get(j);
            if (v2.start > v1.end) {
                i++;
            } else if (v1.start > v2.end) {
                j++;
            } else {
                Interval toadd = new Interval(Math.max(v1.start, v2.start), Math.min(v1.end, v2.end));
                r.add(toadd);
                if (v1.end < v2.end) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return r;
    }

    int MIN = -1000000000;
    int MAX = 1000000000;

    private List<Interval> free(List<Interval> intervals) {
        List<Interval> r = new ArrayList<>();
        r.add(new Interval(MIN, intervals.get(0).start));
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start > last.end) {
                r.add(new Interval(last.end, intervals.get(i).start));
            }
            last = intervals.get(i);
        }
        r.add(new Interval(intervals.get(intervals.size() - 1).end, MAX));
        return r;
    }
}
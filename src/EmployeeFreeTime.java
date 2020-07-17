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

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = new ArrayList<>();
        for(List<Interval> list: schedule){
            for(Interval intv: list){
                intervals.add(intv);
            }
        }
        Collections.sort(intervals, (x,y) -> Integer.compare(x.start, y.start));
        int end = intervals.get(0).end;
        List<Interval> r = new ArrayList<>();
        for(int i=1; i<intervals.size(); i++){
            int curStart = intervals.get(i).start;
            int curEnd = intervals.get(i).end;
            if(curStart > end){
                r.add(new Interval(end, curStart));
                end = curEnd;
            }else{
                end = Math.max(curEnd, end);
            }
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
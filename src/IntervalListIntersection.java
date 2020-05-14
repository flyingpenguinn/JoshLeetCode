import base.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO rewrite to be more concise...
public class IntervalListIntersection {

    public Interval[] intervalIntersection(Interval[] a, Interval[] b) {
        if (a.length == 0 || b.length == 0) {
            return new Interval[0];
        }
        int pa = 0;
        int pb = 0;
        List<Interval> result = new ArrayList<>();
        while (pa < a.length && pb < b.length) {
            Interval inta = a[pa];
            Interval intb = b[pb];
            if (inta.start <= intb.start) {
                if (inta.end < intb.start) {
                    // totally disconnected
                    pa++;

                } else if (intb.end <= inta.end) {
                    // b totally eaten...
                    result.add(intb);
                    pb++;

                } else {
                    Interval intersect = new Interval(intb.start, inta.end);
                    result.add(intersect);
                    pa++;
                    intb.start = inta.end + 1;
                    if (intb.start > intb.end) {
                        pb++;
                    }
                }
            } else {
                // this is a replay of the above...
                if (intb.end < inta.start) {
                    // totally disconnected
                    pb++;
                } else if (inta.end <= intb.end) {
                    // a totally eaten...
                    result.add(inta);
                    pa++;
                } else {
                    Interval intersect = new Interval(inta.start, intb.end);
                    result.add(intersect);
                    pb++;
                    inta.start = intb.end + 1;
                    if (inta.start > inta.end) {
                        pa++;
                    }
                }
            }
        }
        return result.toArray(new Interval[0]);
    }

    public static void main(String[] args) {
        IntervalListIntersctionSimpler lil = new IntervalListIntersctionSimpler();
        Interval[] a = {new Interval(0, 2), new Interval(5, 10), new Interval(13, 23), new Interval(24, 25)};
        Interval[] b = {new Interval(3, 5), new Interval(8, 12), new Interval(15, 24), new Interval(25, 26)};
        System.out.println(Arrays.toString(lil.intervalIntersection(a, b)));
    }
}

class IntervalListIntersctionSimpler{
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new Interval[] {};
        }

        int m = A.length, n = B.length;
        int i = 0, j = 0;
        List<Interval> res = new ArrayList<>();
        while (i < m && j < n) {
            Interval a = A[i];
            Interval b = B[j];

            // find the overlap... if there is any...
            int startMax = Math.max(a.start, b.start);
            int endMin = Math.min(a.end, b.end);

            if (endMin >= startMax) {
                res.add(new Interval(startMax, endMin));
            }

            //update the pointer with smaller end value...
            if (a.end == endMin) { i++; }
            if (b.end == endMin) { j++; }
        }

        //thanks EOAndersson for the concice expression of converting a list to an array
        //thanks Sithis for the explaination of using toArray (new T[0]) intead fo toArray newT[size])
        return res.toArray(new Interval[0]);
    }
}

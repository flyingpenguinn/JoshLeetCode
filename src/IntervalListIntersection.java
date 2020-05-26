import base.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
LC#986
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)



Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]


Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 */

public class IntervalListIntersection {
    // if disjoint, move the smaller
    // if intersect, get the intersection, and move the one that is ending earlier. adjust the remaining one
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        int i = 0;
        int j = 0;
        int an = a.length;
        int bn = b.length;
        List<int[]> list = new ArrayList<>();
        while (i < an && j < bn) {
            if (b[j][0] > a[i][1]) {
                i++;
            } else if (a[i][0] > b[j][1]) {
                j++;
            } else {
                int[] inter = new int[]{Math.max(a[i][0], b[j][0]), Math.min(a[i][1], b[j][1])};
                list.add(inter);
                if (a[i][1] < b[j][1]) {
                    // dont need to change interval: it's all non overlapping so will be eaten by later intervals anyway
                    i++;
                } else {
                    j++;
                }
            }
        }
        int[][] r = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            r[k][0] = list.get(k)[0];
            r[k][1] = list.get(k)[1];
        }
        return r;
    }

}

import base.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
                    b[j][0] = a[i][1] + 1;
                    i++;
                } else if (a[i][1] > b[j][1]) {
                    a[i][0] = b[j][1] + 1;
                    j++;
                } else {
                    i++;
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

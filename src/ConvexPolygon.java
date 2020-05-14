import base.ArrayUtils;

import java.util.*;

/*
LC#469
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).



Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.


Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:
Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False

Explanation:
 */
public class ConvexPolygon {
    // cross product sign is the same (either >=0 or <=0
    // sign could be ==0! so mind the tricky logic. also mind the first and last point
    public boolean isConvex(List<List<Integer>> p) {
        int[][] rp = new int[p.size()][2];
        for (int i = 0; i < p.size(); i++) {
            rp[i][0] = p.get(i).get(0);
            rp[i][1] = p.get(i).get(1);
        }
        return doi(rp);
    }

    private boolean doi(int[][] p) {
        long last = 0;
        int n = p.length;
        for (int i = 0; i < n; i++) {
            int[] p0 = p[(i - 1 + n) % n];
            int[] p1 = p[i];
            int[] p2 = p[(i + 1) % n];
            int[] a = new int[]{p2[0] - p1[0], p2[1] - p1[1]};
            int[] b = new int[]{p1[0] - p0[0], p1[1] - p0[1]};
            // a1b2- b1a2 is the value of the cross product
            int cur = a[0] * b[1] - b[0] * a[1];
            if (last == 0) {
                last = cur;
            } else if (last * cur < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[1,0],[1,1],[0,1]]")));
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[0,1],[1,1]]")));
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[0,10],[5,5],[10,10],[10,0]]")));
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[0,10],[10,10],[10,0],[5,5]]")));
    }

}

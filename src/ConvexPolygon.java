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
    public boolean isConvex(List<List<Integer>> a) {
        int n = a.size();
        boolean seenNeg = false;
        boolean seenPos = false;
        List<Integer> vec1 = vec(a.get(0), a.get(1));
        for (int i = 1; i <= n; i++) {
            int j = i % n;
            int k = (i + 1) % n;
            List<Integer> vec2 = vec(a.get(j), a.get(k));
            int cross = crossProduct(vec1, vec2);
            if (cross < 0) {
                seenNeg = true;
            } else if (cross > 0) {
                seenPos = true;
            }
            if (seenNeg && seenPos) {
                return false;
            }
            vec1 = vec2;
        }
        return true;
    }

    private List<Integer> vec(List<Integer> x, List<Integer> y) {
        return List.of(y.get(0) - x.get(0), y.get(1) - x.get(1));
    }

    private int crossProduct(List<Integer> x, List<Integer> y) {
        return x.get(0) * y.get(1) - x.get(1) * y.get(0);
    }

    public static void main(String[] args) {
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[1,0],[1,1],[0,1]]")));
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[0,1],[1,1]]")));
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[0,10],[5,5],[10,10],[10,0]]")));
        System.out.println(new ConvexPolygon().isConvex(ArrayUtils.readAsList("[[0,0],[0,10],[10,10],[10,0],[5,5]]")));
    }

}

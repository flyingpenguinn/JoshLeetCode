import base.ArrayUtils;


import java.util.*;

/*
LC#391
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.




Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.




Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.




Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
 */
public class PerfectRectangle {
    // checking overlaps efficiently...
    public boolean isRectangleCover(int[][] a) {

        int blx = Integer.MAX_VALUE;
        int rtx = Integer.MIN_VALUE;
        int bly = Integer.MAX_VALUE;
        int rty = Integer.MIN_VALUE;
        int sumarea = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            blx = Math.min(blx, a[i][0]);
            bly = Math.min(bly, a[i][1]);
            rtx = Math.max(rtx, a[i][2]);
            rty = Math.max(rty, a[i][3]);
            sumarea += (a[i][3] - a[i][1]) * (a[i][2] - a[i][0]);
        }
        int area = (rty - bly) * (rtx - blx);
        if (area != sumarea) {
            return false;
        }
        Arrays.sort(a, (x, y) -> x[1] != y[1] ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
        return !overlap(a);
    }

    private boolean overlap(int[][] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j][1] >= a[i][3]) {
                    break;
                } else if (a[j][0] >= a[i][2]) {
                    continue;
                } else if (a[j][2] <= a[i][0]) {
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[0,0,1,1],[0,0,2,1],[1,0,2,1],[0,2,2,3]]")));
        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[0,0,4,1],[7,0,8,2],[6,2,8,3],[5,1,6,3],[4,0,5,1],[6,0,7,2],[4,2,5,3],[2,1,4,3],[0,1,2,2],[0,2,2,3],[4,1,5,2],[5,0,6,1]]")));

        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[0,0,2,2],[1,1,3,3],[2,0,3,1],[0,3,3,4]]")));
        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[0,0,1,1],[0,1,3,2],[1,0,2,2]]")));

        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]")));

        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]")));

        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]")));

        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]")));

        System.out.println(new PerfectRectanglePointCount().isRectangleCover(ArrayUtils.read("[[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]")));


    }
}

class PerfectRectanglePointCount {
    /*
    the large rectangle area should be equal to the sum of small rectangles
count of all the points should be even, and that of all the four corner points should be one
     */
    private String toString(int a, int b) {
        return a + "," + b;
    }

    public boolean isRectangleCover(int[][] rects) {
        Map<String, Integer> m = new HashMap<>();
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        int maxy = Integer.MIN_VALUE;
        int area = 0;
        for (int[] rect : rects) {
            String bottomLeft = toString(rect[0], rect[1]);
            m.put(bottomLeft, m.getOrDefault(bottomLeft, 0) + 1);
            String topRight = toString(rect[2], rect[3]);
            m.put(topRight, m.getOrDefault(topRight, 0) + 1);
            String bottomRight = toString(rect[2], rect[1]);
            m.put(bottomRight, m.getOrDefault(bottomRight, 0) + 1);
            String topLeft = toString(rect[0], rect[3]);
            m.put(topLeft, m.getOrDefault(topLeft, 0) + 1);
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
            minx = Math.min(rect[0], minx);
            miny = Math.min(rect[1], miny);
            maxx = Math.max(rect[2], maxx);
            maxy = Math.max(rect[3], maxy);
        }
        if (area != (maxx - minx) * (maxy - miny)) {
            return false;
        }
        Set<String> corners = new HashSet<>();
        corners.add(toString(minx, miny));
        corners.add(toString(minx, maxy));
        corners.add(toString(maxx, miny));
        corners.add(toString(maxx, maxy));

        for (String k : m.keySet()) {
            if (corners.contains(k)) {
                if (m.get(k) != 1) {
                    return false;
                }
                corners.remove(k);
            } else {
                if (m.get(k) % 2 != 0) {
                    return false;
                }
            }
        }
        return corners.isEmpty();
    }
}
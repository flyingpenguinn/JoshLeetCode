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
    // line sweep. given each x, check if ys overlap
    private class Ends {
        int y1;
        int y2;
        int index;

        public Ends(int y1, int y2, int index) {
            this.y1 = y1;
            this.y2 = y2;
            this.index = index;

        }
    }

    private class Line {
        int x;
        Ends y;
        int type;

        public Line(int x, Ends y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    private int Max = 1000000000;

    public boolean isRectangleCover(int[][] rectangles) {
        List<Line> lines = new ArrayList<>();
        int lowy = Max;
        int highy = -Max;
        for (int i = 0; i < rectangles.length; ++i) {
            int[] rect = rectangles[i];
            int x = rect[0];
            int y = rect[1];
            int a = rect[2];
            int b = rect[3];
            lines.add(new Line(x, new Ends(y, b, i), 0));
            lines.add(new Line(a, new Ends(y, b, i), 1));
            lowy = Math.min(lowy, y);
            highy = Math.max(highy, b);
        }
        Collections.sort(lines, (l1, l2) -> l1.x != l2.x ? Integer.compare(l1.x, l2.x) : Integer.compare(l2.type, l1.type)); // remove first
        int i = 0;
        int n = lines.size();
        TreeSet<Ends> lineset = new TreeSet<>((l1, l2) -> {
            if (l1.y1 != l2.y1) {
                return Integer.compare(l1.y1, l2.y1);
            } else if (l1.y2 != l2.y2) {
                return Integer.compare(l1.y2, l2.y2);
            } else {
                return Integer.compare(l1.index, l2.index);
            }
        });
        while (i < n) {
            int j = i;
            while (j < n && lines.get(j).x == lines.get(i).x) {
                if (lines.get(j).type == 1) {
                    lineset.remove(lines.get(j).y);
                } else {
                    lineset.add(lines.get(j).y);
                }
                ++j;
            }
            if (j == n) {
                // last line...must be empty
                return lineset.isEmpty();
            }else if(lineset.isEmpty()){
                return false;
            }
            Iterator<Ends> it = lineset.iterator();
            int lastend = lineset.first().y1;
            if (lastend != lowy) {
                return false;
            }
            while (it.hasNext()) {
                Ends cur = it.next();
                if (lastend != cur.y1) {
                    return false;
                }
                lastend = cur.y2;
            }
            if (lastend != highy) {
                return false;
            }
            i = j;
        }
        return true;
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
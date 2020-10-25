import base.ArrayUtils;

import java.util.*;


public class ErectTheFence {
    // gift wrapping algo
    // cross product >0 means ac on ab's left, so use c instead of b
    // handling colinear by trying to extend to the farthest and incluce all points on the line
    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int[][] outerTrees(int[][] points) {
        Set<Point> result = new HashSet<>();

        // Find the leftmost point
        Point first = new Point(points[0][0], points[0][1]);
        int firstIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] < first.x) {
                first = new Point(points[i][0], points[i][1]);
                firstIndex = i;
            }
        }
        result.add(first);

        Point cur = first;
        int curIndex = firstIndex;
        do {
            Point next = new Point(points[0][0], points[0][1]);
            int nextIndex = 0;
            for (int i = 1; i < points.length; i++) {
                if (i == curIndex) {
                    continue;
                }
                Point pi = new Point(points[i][0], points[i][1]);
                int cross = crossProductLength(cur, next, pi);
                if (nextIndex == curIndex || cross > 0 ||
                        // Handle collinear points
                        (cross == 0 && distance(pi, cur) > distance(next, cur))) {
                    next = pi;
                    nextIndex = i;
                }
            }
            // Handle collinear points
            for (int i = 0; i < points.length; i++) {
                if (i == curIndex) continue;
                Point pi = new Point(points[i][0], points[i][1]);
                int cross = crossProductLength(cur, pi, next);
                if (cross == 0) {
                    result.add(pi);
                }
            }
            cur = next;
            curIndex = nextIndex;

        } while (curIndex != firstIndex);
        int[][] r = new int[result.size()][2];
        int rp = 0;
        for (Point k : result) {
            r[rp][0] = k.x;
            r[rp][1] = k.y;
            rp++;
        }
        return r;
    }

    private int crossProductLength(Point A, Point B, Point C) {
        // Get the vectors' coordinates.
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int ACx = C.x - A.x;
        int ACy = C.y - A.y;

        // Calculate the Z coordinate of the cross product.
        return (ABx * ACy - ABy * ACx);
    }

    private int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] args) {
        System.out.println(new ErectTheFence().outerTrees(ArrayUtils.read("[[1,1],[0,0], [0,1], [1,0]]")));
    }
}

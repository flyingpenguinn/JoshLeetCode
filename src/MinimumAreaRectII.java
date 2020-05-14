import base.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class MinimumAreaRectII {
    public double minAreaFreeRect(int[][] points) {
        Set<Long> ps = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            ps.add(toHashCode(points[i][0], points[i][1]));
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }
                    // p1p2p3p4 form a parallelogram:
                    // p2-p1 + p2-p3 = p2-p4
                    // => p3+p1-p2 = p4
                    int px = points[k][0] + points[i][0] - points[j][0];
                    int py = points[k][1] + points[i][1] - points[j][1];
                    if (ps.contains(toHashCode(px, py))) {
                        // if parallelogram has one right angle it's a rectangle
                        if (rightAngle(points, i, j, k)) {
                            long area = len(points, i, j) * len(points, j, k);
                            min = Math.min(min, area);
                        }
                    }

                }
            }
        }
        return min == Long.MAX_VALUE ? 0 : Math.sqrt(min);

    }

    private Long toHashCode(int px, int py) {
        return Long.valueOf(px * 40000L + py);
    }

    private boolean rightAngle(int[][] points, int i, int j, int k) {
        int x1 = points[i][0] - points[j][0];
        int y1 = points[i][1] - points[j][1];
        int x2 = points[j][0] - points[k][0];
        int y2 = points[j][1] - points[k][1];
        int cp = x1 * x2 + y1 * y2;
        return cp == 0;
    }

    private long len(int[][] points, int i, int j) {
        int x = points[i][0] - points[j][0];
        int y = points[i][1] - points[j][1];
        return x * x + y * y;
    }

    public static void main(String[] args) {
        int[][] points = ArrayUtils.read("[[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]");
        System.out.println(new MinimumAreaRectII().minAreaFreeRect(points));
    }
}

// worst case n4, but super fast on leetcode...
class MinAreaRectIIMine {
    public double minAreaFreeRect(int[][] points) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }
                    if (rightAngle(points, i, j, k)) {
                        for (int l = 0; l < points.length; l++) {
                            if (i == l || j == l || k == l) {
                                continue;
                            }
                            boolean c1 = rightAngle(points, i, l, k) && rightAngle(points, l, k, j);
                            if (c1) {
                                long area = len(points, i, j) * len(points, j, k);
                                min = Math.min(min, area);
                            }
                        }
                    }
                }
            }
        }
        return min == Long.MAX_VALUE ? 0 : Math.sqrt(min);

    }

    private boolean rightAngle(int[][] points, int i, int j, int k) {
        int x1 = points[i][0] - points[j][0];
        int y1 = points[i][1] - points[j][1];
        int x2 = points[j][0] - points[k][0];
        int y2 = points[j][1] - points[k][1];
        int cp = x1 * x2 + y1 * y2;
        return cp == 0;
    }

    private long len(int[][] points, int i, int j) {
        int x = points[i][0] - points[j][0];
        int y = points[i][1] - points[j][1];
        return x * x + y * y;
    }
}

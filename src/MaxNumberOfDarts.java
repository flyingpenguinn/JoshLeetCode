import java.util.ArrayList;
import java.util.List;

/*
LC#1453
You have a very large square wall and a circular dartboard placed on the wall. You have been challenged to throw darts into the board blindfolded. Darts thrown at the wall are represented as an array of points on a 2D plane.

Return the maximum number of points that are within or lie on any circular dartboard of radius r.



Example 1:



Input: points = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
Output: 4
Explanation: Circle dartboard with center in (0,0) and radius = 2 contain all points.
Example 2:



Input: points = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
Output: 5
Explanation: Circle dartboard with center in (0,4) and radius = 5 contain all points except the point (7,8).
Example 3:

Input: points = [[-2,0],[2,0],[0,2],[0,-2]], r = 1
Output: 1
Example 4:

Input: points = [[1,2],[3,5],[1,-1],[2,3],[4,1],[1,3]], r = 2
Output: 4


Constraints:

1 <= points.length <= 100
points[i].length == 2
-10^4 <= points[i][0], points[i][1] <= 10^4
1 <= r <= 5000
 */
public class MaxNumberOfDarts {
    // given 2 points and a radius can find at most 2 circles. then check each circle
    public int numPoints(int[][] points, int r) {
        List<double[]> centers = new ArrayList<>();
        int n = points.length;
        double[][] p = new double[n][2];
        for (int i = 0; i < n; i++) {
            p[i][0] = points[i][0];
            p[i][1] = points[i][1];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                List<double[]> cs = getcs(points[i][0], points[i][1], points[j][0], points[j][1], r);
                centers.addAll(cs);
            }
        }
        int res = 1;
        for (double[] c : centers) {
            int cur = 0;
            for (int i = 0; i < n; i++) {
                if (within(c, p[i], r)) {
                    cur++;
                }
            }
            res = Math.max(res, cur);
        }
        return res;
    }

    private boolean within(double[] c, double[] p, int r) {
        double dx = c[0] - p[0];
        double dy = c[1] - p[1];
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist <= r + 0.00001) {
            // double <= trick
            return true;
        }
        return false;
    }


    private List<double[]> getcs(double x1, double y1, double x2, double y2, double r) {
        double q = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double x3 = (x1 + x2) / 2.0;
        double y3 = (y1 + y2) / 2.0;
        double cx = Math.sqrt(r * r - (q / 2.0) * (q / 2.0)) * (y1 - y2) / q;
        double rx1 = x3 + cx;
        double cy = Math.sqrt(r * r - (q / 2.0) * (q / 2.0)) * (x2 - x1) / q;
        double ry1 = y3 + cy;
        double rx2 = x3 - cx;
        double ry2 = y3 - cy;
        double[] r1 = new double[]{rx1, ry1};
        double[] r2 = new double[]{rx2, ry2};
        List<double[]> res = new ArrayList<>();
        res.add(r1);
        res.add(r2);
        return res;
    }
}

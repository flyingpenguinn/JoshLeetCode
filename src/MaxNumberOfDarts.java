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
    // find all circles that pass two points, and check how many points are there in the circle defined by them
    // if no such circle found, then at least we can cover 1 point
    public int numPoints(int[][] intps, int r) {
        int max = 1;
        double[][] p = new double[intps.length][2];
        for (int i = 0; i < p.length; i++) {
            p[i][0] = intps[i][0];
            p[i][1] = intps[i][1];
        }
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double[][] circ = findCircle(p[i], p[j], r);
                for (int t = 0; t < circ.length; t++) {
                    int cur = 0;
                    for (int k = 0; k < p.length; k++) {
                        if (inside(p[k], circ[t], r)) {
                            cur++;
                        }
                    }
                    max = Math.max(max, cur);
                }
            }
        }
        return max;
    }

    private double eps = 0.00001;
    private double[] origin = {0, 0};

    private boolean inside(double[] p, double[] circ, int r) {
        return dist(p, circ) <= r + eps;
    }

    private double dist(double[] p1, double[] p2) {
        double dx = p1[0] - p2[0];
        double dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double[][] findCircle(double[] p1, double[] p2, double r) {
        // two circle's x, y
        double[] m = new double[]{(p1[0] + p2[0]) / 2.0, (p1[1] + p2[1]) / 2.0};
        double half = dist(p1, p2) / 2.0;
        double len = Math.sqrt(r * r - half * half);
        if (len == 0) {
            // if len ==0, mid is the point we want. such as -2,0 and 2,0, 0,0 is the point
            double[][] res = new double[1][2];
            res[0] = m;
            return res;
        }
        // otherwise, we find the vector that is perpendicular to the vector defined by the two points.
        // then unit it, times the dist from center to mid point
        double[][] res = new double[2][2];
        double[] vector = {p2[0] - p1[0], p2[1] - p1[1]};
        // note the perpendicular vector can go both ways
        double[] pv = {1, -vector[0] / vector[1]};
        double pvlen = dist(origin, pv);
        pv[0] /= pvlen;
        pv[1] /= pvlen;
        res[0][0] = m[0] + pv[0] * len;
        res[0][1] = m[1] + pv[1] * len;

        res[1][0] = m[0] - pv[0] * len;
        res[1][1] = m[1] - pv[1] * len;
        return res;
    }
}

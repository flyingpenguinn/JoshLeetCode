/*
LC#812
You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.

Example:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2
Explanation:
The five points are show in the figure below. The red triangle is the largest.


Notes:

3 <= points.length <= 50.
No points will be duplicated.
 -50 <= points[i][j] <= 50.
Answers within 10^-6 of the true value will be accepted as correct.
 */
public class LargestTriangleArea {
    public double largestTriangleArea(int[][] a) {
        int n = a.length;
        double max = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 1; k < n; k++) {
                    double area = area(a, i, j, k);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private double area(int[][] a, int i, int j, int k) {
        int x1 = a[i][0];
        int y1 = a[i][1];
        int x2 = a[j][0];
        int y2 = a[j][1];
        int x3 = a[k][0];
        int y3 = a[k][1];
        // remember this formula...called shoelace formula
        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
    }

}
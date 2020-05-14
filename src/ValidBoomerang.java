/*
LC#1037
A boomerang is a set of 3 points that are all distinct and not in a straight line.

Given a list of three points in the plane, return whether these points are a boomerang.



Example 1:

Input: [[1,1],[2,3],[3,2]]
Output: true
Example 2:

Input: [[1,1],[2,2],[3,3]]
Output: false


Note:

points.length == 3
points[i].length == 2
0 <= points[i][j] <= 100
 */
public class ValidBoomerang {
    public boolean isBoomerang(int[][] a) {

        int x1 = a[0][0];
        int y1 = a[0][1];
        int x2 = a[1][0];
        int y2 = a[1][1];
        int x3 = a[2][0];
        int y3 = a[2][1];
        // area is this /2.0
        return x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) != 0;
    }
}

class ValidBoomerangSlopeCheck {

    public boolean isBoomerang(int[][] a) {
        if (same(a[0], a[1]) || same(a[1], a[2]) || same(a[0], a[2])) {
            return false;
        }
        if (a[0][0] == a[1][0] && a[1][0] == a[2][0]) {
            return false;
        }
        if (a[0][0] == a[1][0] || a[0][0] == a[2][0]) {
            return true;
        }
        // a/b == c/d => ad == bc, no need to do double math
        int ad = (a[1][1] - a[0][1]) * (a[2][0] - a[0][0]);
        int bc = (a[2][1] - a[0][1]) * (a[1][0] - a[0][0]);
        return ad != bc;
    }

    boolean same(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }
}

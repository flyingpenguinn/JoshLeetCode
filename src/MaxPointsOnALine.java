import base.ArrayUtils;

import java.util.*;

/*
LC#149
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

public class MaxPointsOnALine {

    // group by starting point and k. k is a fraction and gcded to avoid double precision problem
    // for each starting point calculate and group k


    private class Frac {
        int x;
        int y;
        int INF = (int) 2e9;

        public Frac(int x, int y) {

            if (x != 0 && y != 0) {
                int gcd = gcd(Math.abs(x), Math.abs(y)); // gcd must be abs
                x /= gcd;
                y /= gcd;
            }
            // deal with sign so that neg sign is only on x
            if (x < 0 && y < 0) {
                x = -x;
                y = -y;
            } else if (x > 0 && y < 0) {
                x = -x;
                y = -y;
            }
            this.x = x;
            this.y = y;
        }

        private int gcd(int a, int b) {
            if (a < b) {
                return gcd(b, a);
            }
            //a>b
            return b == 0 ? a : gcd(b, a % b);
        }

        @Override
        public int hashCode() {
            if (x == 0) {
                return INF;
            }
            if (y == 0) {
                return 0;
            }
            return (int) (x * 1e5 + y);
        }

        @Override
        public boolean equals(Object other) {
            Frac o = (Frac) other;
            return x * o.y == y * o.x;
        }

        @Override
        public String toString() {
            return "Frac [x=" + x + " y=" + y + "]";
        }
    }

    public int maxPoints(int[][] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cur = 0;
            Map<Frac, Integer> m = new HashMap<>();
            for (int j = i + 1; j < n; ++j) {
                int dy = a[i][1] - a[j][1];
                int dx = a[i][0] - a[j][0];
                Frac cd = new Frac(dx, dy);
                int nv = m.getOrDefault(cd, 0) + 1;
                cur = Math.max(cur, nv);
                m.put(cd, nv);
            }
            res = Math.max(res, cur + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        //  System.out.println(new MaxPointsOnALine().maxPoints(ArrayUtils.read("[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]")));
        // System.out.println(new MaxPointsOnALine().maxPoints(ArrayUtils.read("[[2,2],[1,1],[3,3]]")));
        System.out.println(new MaxPointsOnALine().maxPoints(ArrayUtils.read("[[1,1],[1,1],[2,2],[2,2]]")));
    }
}

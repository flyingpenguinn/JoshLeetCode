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

    // group by starting point and k. k is gcded to avoid double precision problem
    // handle the case of multi occurrence of points by counting them and adding to the final result in one shot
    // note map may not contain accurate data for each point but that's fine

    public int maxPoints(int[][] points) {
        int n = points.length;
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            map.clear();
            int ic = 1;
            int maxi = 0;
            for (int j = i + 1; j < n; j++) {
                if (same(points[i], points[j])) {
                    ic++;
                    continue;
                }
                int[] ks = getk(points[i], points[j]);
                String ck = tokey(points[i][0], points[i][1], ks[0], ks[1]);
                int cc = map.getOrDefault(ck, 0) + 1;
                map.put(ck, cc);
                maxi = Math.max(maxi, cc);
                // it's ok to just store
            }
            maxi += ic;
            max = Math.max(max, maxi);
        }
        return max;
    }

    private boolean same(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }

    private String tokey(int i, int j, int k, int m) {
        return i + "," + j + "," + k + "," + m;
    }

    private int[] getk(int[] p1, int[] p2) {
        int dy = p2[1] - p1[1];
        int dx = p2[0] - p1[0];
        if (dx == 0) {
            return new int[]{0, 0};
        } else {
            int gcd = gcd(dy, dx);
            return new int[]{dx / gcd, dy / gcd};
        }
    }

    private int gcd(int dy, int dx) {
        return dx == 0 ? dy : gcd(dx, dy % dx);
    }

    public static void main(String[] args) {
        //  System.out.println(new MaxPointsOnALine().maxPoints(ArrayUtils.read("[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]")));
        // System.out.println(new MaxPointsOnALine().maxPoints(ArrayUtils.read("[[2,2],[1,1],[3,3]]")));
        System.out.println(new MaxPointsOnALine().maxPoints(ArrayUtils.read("[[1,1],[1,1],[2,2],[2,2]]")));
    }
}

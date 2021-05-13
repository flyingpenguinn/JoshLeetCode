import base.ArrayUtils;

import java.util.*;

/*
LC#850
We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
Note:

1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 10^9
The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
 */
public class RectangleAreaII {
    private int mod = 1000000007;

    public int rectangleArea(int[][] a) {
        // keep a sorted list of currently live pending edges
        // key insigt: till next x, all live edges generate areas because there is nothing in between.
        // hence for each edge, first calc what we have last time till this line by last len* diff on x. note it's actually lastx...x-1 so len = x-lastx
        // then if it's entering edge, add and merge with current edges. the merge is similar to interval merging problem
        // if it's leaving edge, remove it from live edge list
        int n = a.length;
        // y start, end, index
        List<int[]> es = new ArrayList<>();
        // x, y start, yend, type, index
        for (int i = 0; i < n; i++) {
            int[] start = new int[]{a[i][0], a[i][1], a[i][3], 0, i};
            int[] end = new int[]{a[i][2], a[i][1], a[i][3], 1, i};
            es.add(start);
            es.add(end);
        }
        // ordered in x
        Collections.sort(es, (x, y) -> Integer.compare(x[0], y[0]));
        //  print(es);
        long res = 0;
        long lastlen = 0;
        int lastx = 0;
        int i = 0;
        List<int[]> ys = new ArrayList<>();
        // ystart, yend, index
        while (i < es.size()) {

            int[] e = es.get(i);
            int x = e[0];
            long added = lastlen * (x - lastx);
            res += added;
            res %= mod;
            while (i < es.size() && es.get(i)[0] == x) {
                e = es.get(i);
                if (e[3] == 0) {
                    // entering edge, add and then sort and merge
                    ys.add(new int[]{e[1], e[2], e[4]});
                } else {
                    // delete that entering edge
                    for (int j = 0; j < ys.size(); j++) {
                        if (ys.get(j)[2] == e[4]) {
                            ys.remove(j);
                            break;
                        }
                    }
                }
                i++;
            }
            // sort by start
            Collections.sort(ys, (p, q) -> Integer.compare(p[0], q[0]));
            lastlen = merge(ys);
            lastx = x;
        }
        return (int) res;
    }

    private int merge(List<int[]> ys) {
        if (ys.isEmpty()) {
            return 0;
        }
        int start = -1;
        int end = -1;
        int res = 0;
        for (int[] y : ys) {
            if (y[0] <= end) {
                end = Math.max(end, y[1]);
            } else {
                res += end - start;
                start = y[0];
                end = y[1];
            }
        }
        res += end - start;
        return res;
    }


    public static void main(String[] args) {
        int[][] rects = ArrayUtils.read("[[0,0,2,2],[1,0,2,3],[1,0,3,1]]");
        System.out.println(new RectangleAreaII().rectangleArea(rects));
    }
}

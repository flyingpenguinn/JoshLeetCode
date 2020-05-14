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
    //sweepling lines
    // make x discrete to be all xs of rectangles,similar to skyline
    //use ways simialar to meeting rooms to merge segments between xs
    int Mod = 1000000007;


    public int rectangleArea(int[][] rects) {
        TreeSet<Integer> xs = new TreeSet<>();
        for (int[] r : rects) {
            xs.add(r[0]);
            xs.add(r[2]);
        }
        int lastx = xs.first();
        xs.remove(lastx);
        long r = 0L;
        Arrays.sort(rects, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return Integer.compare(o1[1], o2[1]);
                } else {
                    return Integer.compare(o1[3], o2[3]);
                }
            }
        });

        for (int x : xs) {
            int[] last = new int[2];
            // we sorted rects according to their starting point and height
            for (int[] rect : rects) {
                if (rect[2] <= lastx || rect[0] >= x) {
                    // note also filter out ==
                    continue;
                }
                int[] curs = {rect[1], rect[3]};
                curs[0] = Math.max(curs[0], last[1]);
                long ydiff = curs[1] - curs[0];
                if (ydiff < 0) {
                    // totally consumed in previous seg
                    continue;
                }
                long xdiff = x - lastx;

                long curr = ydiff * xdiff;
                // System.out.println(lastx+"=>"+x+" xdiff="+xdiff+" ydiff="+ydiff);
                r += curr;
                last = curs;
            }
            lastx = x;
        }
        return (int) (r % Mod);
    }

    public static void main(String[] args) {
        // int[][] rects = ArrayUtils.read("[[0,0,2,2],[1,1,3,3]]");
        //    int[][] rects = ArrayUtils.read("[[0,0,2,2],[1,0,2,3],[1,0,3,1]]");
        int[][] rects = ArrayUtils.read("[[0,0,1000000000,1000000000]]");
        System.out.println(new RectangleAreaII().rectangleArea(rects));
    }
}

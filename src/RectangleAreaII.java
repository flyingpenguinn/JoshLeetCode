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
    // for each x that is the edge, check its intersection with ALL rectangles and use interval merge to calc the y length
    // area is (curx- prex)*cury
    int Mod = 1000000007;

    public int rectangleArea(int[][] a) {
        // sort all rects by x we will later scan on x
        int n = a.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(a[i][0]);
            set.add(a[i][2]);
        }
        Arrays.sort(a, (s, t) -> Integer.compare(s[1], t[1]));
        long r = 0;
        int lastx = -1;
        for (int x : set) {
            if (lastx == -1) {
                lastx = x;
                continue;
            }
            long lasty1 = -1;
            long lasty2 = -1;
            long diffy = 0;
            for (int i = 0; i < n; i++) {
                if (a[i][2] <= lastx || a[i][0] >= x) {
                    continue;  // too left or too right. we either handled already last time, or will handle it
                }
                // this x cuts all rectangles to form intervals. now merge these intervals
                int y1 = a[i][1];
                int y2 = a[i][3];
                if (lasty1 == -1) {
                    lasty1 = y1;
                    lasty2 = y2;
                } else if (y1 > lasty2) {
                    diffy += lasty2 - lasty1;
                    lasty1 = y1;
                    lasty2 = y2;
                } else {
                    lasty2 = Math.max(lasty2, y2);
                }
            }
            diffy += lasty2 - lasty1;
            int diffx = x - lastx;
            // key: between x and lastx there is nothing. so if x gets diffy on y direction, we have diffx*diffy area cut out
            r += diffx * diffy;
            lastx = x;
        }
        return (int) (r % Mod);
    }


    public static void main(String[] args) {
        int[][] rects = ArrayUtils.read("[[0,0,2,2],[1,0,2,3],[1,0,3,1]]");
        System.out.println(new RectangleAreaII().rectangleArea(rects));
    }
}

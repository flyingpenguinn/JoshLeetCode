import base.ArrayUtils;
import crap.Crap;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

/*

LC#497
Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.

Note:

An integer point is a point that has integer coordinates.
A point on the perimeter of a rectangle is included in the space covered by the rectangles.
ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
length and width of each rectangle does not exceed 2000.
1 <= rects.length <= 100
pick return a point as an array of integer coordinates [p_x, p_y]
pick is called at most 10000 times.
Example 1:

Input:
["Solution","pick","pick","pick"]
[[[[1,1,5,5]]],[],[],[]]
Output:
[null,[4,1],[4,1],[3,3]]
Example 2:

Input:
["Solution","pick","pick","pick","pick","pick"]
[[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
Output:
[null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.

 */
public class RandomPointNonOverlappingRect {

    static class Solution {
        // add areas together say 3,4,5=> 3,7,12 then pick a number between 1 and 12. then find first that is >= the number we picked
        private int[] a;
        private int sum = 0;
        private int n;
        private int[][] rs;

        public Solution(int[][] rs) {
            this.rs = rs;
            n = rs.length;
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = (i == 0 ? 0 : a[i - 1]) + (rs[i][3] - rs[i][1] + 1) * (rs[i][2] - rs[i][0] + 1);
            }
            sum = a[n - 1];
        }

        private Random ran = new Random();

        public int[] pick() {
            int picked = ran.nextInt(sum) + 1;
            // 1... sum
            int l = 0;
            int u = n - 1;
            // find first a[i]>=picked
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (a[mid] < picked) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            // l is first >=
            // randomly pick in l
            int dy = rs[l][3] - rs[l][1] + 1;
            int dx = rs[l][2] - rs[l][0] + 1;
            int randy = ran.nextInt(dy) + rs[l][1];
            int randx = ran.nextInt(dx) + rs[l][0];
            return new int[]{randx, randy};
        }
    }
}

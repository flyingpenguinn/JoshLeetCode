import java.util.Random;

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
        private int[] sum;
        private int[][] rts;
        private int n;
        private Random rand = new Random();

        public Solution(int[][] rects) {
            n = rects.length;
            rts = rects;
            sum = new int[n];
            for (int i = 0; i < n; i++) {
                // note the area covered needs +1
                int dx = rects[i][2] - rects[i][0] + 1;
                int dy = rects[i][3] - rects[i][1] + 1;
                sum[i] = dx * dy;
            }
            for (int i = 1; i < n; i++) {
                sum[i] += sum[i - 1];
            }
        }

        public int[] pick() {
            int all = sum[n - 1];
            int cand = rand.nextInt(all) + 1;
            // 1..all
            int l = 0;
            int u = n - 1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (sum[mid] >= cand) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // first rs[i]>=cand
            int dx = rts[l][2] - rts[l][0] + 1;
            int dy = rts[l][3] - rts[l][1] + 1;
            int candx = rand.nextInt(dx);
            int candy = rand.nextInt(dy);
            return new int[]{rts[l][0] + candx, rts[l][1] + candy};
        }
    }
}

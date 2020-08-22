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

    // can't pick rect first then pick point because that makes small rect more likely to be picked.
    // need to pick proportional to area (weight) then pick a point inside

    // pick according to area proportionally. similar to random pick with weight but this is 2d
    static class Solution {
        private Random rand = new Random();
        private int[][] rects;
        // same trick as random pick with weight: sum[i] = sum[i-1]+area[i-1], then pick an area, find the eq or < index
        private int[] sums;
        private int allAreas = 0;

        public Solution(int[][] rects) {
            this.rects = rects;
            sums = new int[rects.length];
            allAreas = getArea(rects[0]);
            // sum[0] alreays == 0
            for (int i = 1; i < rects.length; i++) {
                sums[i] = sums[i - 1] + getArea(rects[i - 1]);
                allAreas += getArea(rects[i]);
            }
        }

        private int getArea(int[] rect) {
            return (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
        }


        public int[] pick() {
            // this is similar setting as random pick with weight
            int picked = rand.nextInt(allAreas);
            int found = binarySearch(sums, picked);
            int[] rect = rects[found];
            int randx = rect[0] + rand.nextInt(rect[2] - rect[0] + 1);
            int randy = rect[1] + rand.nextInt(rect[3] - rect[1] + 1);
            return new int[]{randx, randy};
        }

        // if eq find it otherwise find the biggest smaller
        private int binarySearch(int[] a, int t) {
            int l = 0;
            int u = a.length - 1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (a[mid] == t) {
                    return mid;
                } else if (a[mid] < t) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            return u;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution(ArrayUtils.read("[[82918473, -57180867, 82918476, -57180863], [83793579, 18088559, 83793580, 18088560], [66574245, 26243152, 66574246, 26243153], [72983930, 11921716, 72983934, 11921720]]"));
        int[] score = new int[sol.rects.length];
        for (int i = 0; i < 1000000; i++) {
            int[] p = sol.pick();
            int found = -1;
            for (int j = 0; j < sol.rects.length; j++) {
                if (p[0] >= sol.rects[j][0] && p[0] <= sol.rects[j][2] && p[1] >= sol.rects[j][1] && p[1] <= sol.rects[j][3]) {
                    found = j;
                    break;
                }
            }
            if (found == -1) {
                System.out.println("!!!bad..." + Arrays.toString(p));
            } else {
                score[found]++;
            }
        }
        System.out.println(Arrays.toString(score));
    }

}

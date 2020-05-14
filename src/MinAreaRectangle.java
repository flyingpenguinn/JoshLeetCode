
import java.util.*;

/*
LC#939
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
 */
public class MinAreaRectangle {
    long Max = Integer.MAX_VALUE + 1L;

    public int minAreaRect(int[][] a) {
        int n = a.length;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(code(a[i][0], a[i][1]));
        }

        long min = Max;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || a[i][0] >= a[j][0] || a[i][1] >= a[j][1]) {
                    continue;
                }
                long topleft = code(a[i][0], a[j][1]);
                long bottomright = code(a[j][0], a[i][1]);
                if (set.contains(topleft) && set.contains(bottomright)) {
                    int area = (a[j][0] - a[i][0]) * (a[j][1] - a[i][1]);
                    min = Math.min(min, area);
                }
            }
        }
        return min >= Max ? 0 : (int) min;
    }

    private long code(int i, int j) {
        return 1000000 * i + j;
    }
}

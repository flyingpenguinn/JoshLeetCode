/*
LC#1240
Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.



Example 1:



Input: n = 2, m = 3
Output: 3
Explanation: 3 squares are necessary to cover the rectangle.
2 (squares of 1x1)
1 (square of 2x2)
Example 2:



Input: n = 5, m = 8
Output: 5
Example 3:



Input: n = 11, m = 13
Output: 6


Constraints:

1 <= n <= 13
1 <= m <= 13
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TileRectangleFewestSquare {
    // use sweeping line to store current heights of cols
    // typical sweeping line dp to fill out rectangles
    private Map<Long, Integer> dp = new HashMap<>();

    public int tilingRectangle(int n, int m) {
        if (m == n) {
            return 1;
        }
        if (m < n) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        return dfs(m, n, new int[n]);
    }

    private int dfs(int m, int n, int[] st) {
        // m rows, n cols, m>n
        long status = 0;
        int mh = m;
        int ms = -1;
        int base = n + 1;
        // st is the height of all columns after the last tiling. here we compute a status for the heights.
        for (int i = 0; i < n; i++) {
            status = status * base + st[i];
            if (st[i] < mh) {
                mh = st[i];
                ms = i;
            }
        }
        if (mh == m) { // all full
            return 0;
        }
        if (dp.containsKey(status)) {
            return dp.get(status);
        }
        // from the above we have got the column with the smallest height. now we calc how wide we can go. note the max side len of the square is limited
        // by m-mh the height
        int min = Integer.MAX_VALUE;
        int me = ms;
        int maxfit = m - mh;
        while (me < n && st[me] == st[ms] && me - ms + 1 <= maxfit) {
            me++;
        }
        // ms...me-1 can place a square of j-ms+1, we iterate on the possible squares we can put. note we must put square in the smallest height because
        // we would have to fill this hole. but we may only need a smaller square
        for (int j = ms; j < me; j++) {
            // we fill from minstart to j
            int[] nst = Arrays.copyOf(st, n);
            int edge = j - ms + 1;
            for (int k = ms; k <= j; k++) {
                nst[k] += edge;
            }
            int cur = dfs(m, n, nst) + 1;
            min = Math.min(min, cur);
        }
        dp.put(status, min);
        return min;
    }


    public static void main(String[] args) {
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(9, 7));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(1, 8));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(7, 6));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(2, 3));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(5, 8));
    }
}

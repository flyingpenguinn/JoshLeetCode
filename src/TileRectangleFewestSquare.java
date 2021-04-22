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
        int[] st = new int[m];
        return solve(st, n, m);
    }

    // just the states, no row number. we put one box a time
    // optimization: each time we start from the tile with the biggest gap from the top
    private int solve(int[] st, int n, int m) {
        boolean bad = false;
        int maxgap = 0;
        for (int j = 0; j < st.length; j++) {
            if (st[j] < n) {
                bad = true;
                maxgap = Math.max(maxgap, n - st[j]);
            }
        }
        if (!bad) {
            return 0;
        }
        long encoded = encocde(st, n + 1);
        Integer cached = dp.get(encoded);
        if (cached != null) {
            return cached;
        }
        int j = 0;
        int min = 1000000;
        while (j < m) {
            if (st[j] == n || n - st[j] < maxgap) {
                j++;
                continue;
            }
            int k = j;
            while (k < m && st[j] == st[k]) {
                k++;
            }
            // j...k-1 are the same
            int maxlen = Math.min(k - j, n - st[j]);
            // len within k-j and n-current height at j. try all length options
            for (int len = 1; len <= maxlen; len++) {
                int[] nst = Arrays.copyOf(st, m);
                for (int l = j; l < j + len; l++) {
                    // change from j to j+len-1 to be of height old len + len
                    nst[l] += len;
                }
                int cur = 1 + solve(nst, n, m);
                min = Math.min(cur, min);
            }
            break;
        }
        dp.put(encoded, min);
        return min;
    }

    private long encocde(int[] list, int base) {
        long status = 0;
        for (int li : list) {
            status = status * base + li;
        }
        return status;
    }


    public static void main(String[] args) {
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(9, 7));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(1, 8));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(7, 6));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(2, 3));
        System.out.println(new TileRectangleFewestSquare().tilingRectangle(5, 8));
    }
}

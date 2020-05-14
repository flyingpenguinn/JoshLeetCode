import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#221
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
 */
public class MaximalSquare {

    // dpij: edge length of max square using i,j as right bottom point
    // note this is not maximal rectangle!
    public int maximalSquare(char[][] a) {
        int m = a.length;
        if (m == 0) {
            return 0;
        }
        int n = a[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == '0') {
                    continue;
                }
                int el = j == 0 ? 0 : dp[i][j - 1];
                int eu = i == 0 ? 0 : dp[i - 1][j];
                int elu = i == 0 || j == 0 ? 0 : dp[i - 1][j - 1];
                int em = Math.min(el, Math.min(eu, elu)) + 1;
                dp[i][j] = em;
                max = Math.max(max, em);
            }
        }
        return max * max;
    }


    public static void main(String[] args) {
        char[][] input = ArrayUtils.readAsChar("[[1,0,1,1,1],[1,0,1,1,1],[1,1,1,1,1],[1,0,0,1,0]]");
        System.out.println(new MaximalSquare().maximalSquare(input));
    }

}

class MaximalSquareConvertToHisto {
    private class Status {
        int x;
        int h;

        public Status(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }

    public int maximalSquare(char[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int[] h = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == '1') {
                    h[j] += 1;
                } else {
                    h[j] = 0;
                }
            }
            max = Math.max(max, getMaxHist(h));
        }
        return max;
    }

    private int getMaxHist(int[] h) {
        if (h == null || h.length == 0) {
            return 0;
        }
        Deque<Status> stack = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i <= h.length; i++) {
            int ch = i == h.length ? 0 : h[i];
            if (stack.isEmpty()) {
                stack.push(new Status(i, ch));
            } else if (stack.peek().h < ch) {
                stack.push(new Status(i, ch));
            } else {
                Status lastBigger = null;
                while (!stack.isEmpty() && stack.peek().h >= ch) {
                    lastBigger = stack.pop();
                    int prow = i - lastBigger.x;
                    int pcol = lastBigger.h;
                    // when we find biggest rectangle we get the biggest square in it
                    int pmin = Math.min(prow, pcol);
                    int area = pmin * pmin;
                    max = Math.max(area, max);
                }
                stack.push(new Status(lastBigger.x, ch));


            }
        }
        stack.clear();
        return max;
    }
}
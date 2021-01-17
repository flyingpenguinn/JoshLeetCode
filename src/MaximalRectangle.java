import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
LC#85
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */
public class MaximalRectangle {
    // convert to maximal rectangle from largest histo. note this is not maximal square!
    public int maximalRectangle(char[][] a) {
        int m = a.length;
        if (m == 0) {
            return 0;
        }
        int n = a[0].length;
        int[] dp = new int[n];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == '0') {
                    dp[j] = 0;
                } else {
                    dp[j]++;
                }

            }
            int cur = largesthisto(dp);
            res = Math.max(res, cur);
        }
        return res;

    }

    // using a more templated way: find nearest smaller element on left and right
    private int largesthisto(int[] a) {
        // mono increase stack
        Deque<int[]> st = new ArrayDeque<>();
        int n = a.length;
        int res = 0;
        for (int i = 0; i <= n; i++) {
            int ch = i == n ? 0 : a[i];
            int last = -1;
            while (!st.isEmpty() && st.peek()[0] >= ch) {
                int before = st.peek()[1];
                int blen = st.peek()[0];
                int cur = (i - before) * blen;
                res = Math.max(res, cur);
                last = st.peek()[1];
                st.pop();
            }
            if (last != -1) {
                st.push(new int[]{ch, last});
            } else {
                st.push(new int[]{ch, i});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] input = ArrayUtils.readAsChar("[[0,1]]");
        System.out.println(new MaximalRectangle().maximalRectangle(input));
    }
}

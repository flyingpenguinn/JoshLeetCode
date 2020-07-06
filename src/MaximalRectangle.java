import base.ArrayUtils;

import java.util.ArrayDeque;
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
    public int maximalRectangle(char[][] m) {
        int rows = m.length;
        if (rows == 0) {
            return 0;
        }
        int cols = m[0].length;
        if (cols == 0) {
            return 0;
        }
        int[] a = new int[cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a[j] = i == 0 ? m[i][j] - '0' : (m[i][j] == '0' ? 0 : a[j] + 1);
            }
            int cur = largesthisto(a);
            // System.out.println(Arrays.toString(a));
            //System.out.println(cur);

            max = Math.max(max, cur);
        }
        return max;
    }

    // using a more templated way: find nearest smaller element on left and right
    int largesthisto(int[] a) {
        // mono increase stack
        Deque<Integer> stack = new ArrayDeque<>();
        int n = a.length;
        int[] right = new int[n];
        for (int i = 0; i <= n; i++) {
            int val = i == n ? -1 : a[i];
            while (!stack.isEmpty() && a[stack.peek()] > val) {
                int pos = stack.pop();
                right[pos] = i;
            }
            stack.push(i);
        }
        stack.clear();
        int[] left = new int[n];
        for (int i = n - 1; i >= -1; i--) {
            int val = i == -1 ? -1 : a[i];
            while (!stack.isEmpty() && a[stack.peek()] > val) {
                int pos = stack.pop();
                left[pos] = i;
            }
            stack.push(i);

        }
        stack.clear();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = a[i] * (right[i] - left[i] - 1);
            max = Math.max(max, cur);
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] input = ArrayUtils.readAsChar("[[0,1]]");
        System.out.println(new MaximalRectangle().maximalRectangle(input));
    }
}

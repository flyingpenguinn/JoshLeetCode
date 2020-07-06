import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#84
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.



Example:

Input: [2,1,5,6,2,3]
Output: 10
 */
public class LargestRectangleInHistogram {
    // find cliff. anything bigger we can recon them now. replace the last non sm one
    public int largestRectangleArea(int[] a) {
        // h,pos
        Deque<int[]> stack = new ArrayDeque<>();
        int n = a.length;
        int max = 0;
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : a[i];
            int lastnonsm = i;
            while (!stack.isEmpty() && stack.peek()[0] >= h) {
                int[] p = stack.pop();
                int cur = (i - p[1]) * p[0];
                max = Math.max(max, cur);
                lastnonsm = p[1];
            }

            stack.push(new int[]{h, lastnonsm});
        }
        stack.pop();
        return max;

    }
}

// find nearest smaller on left and right
class LargestRectangleInHistogramTemplatedStack {

    // using a more templated way: find nearest smaller element on left and right
    int largestRectangleArea(int[] a) {
        // mono increase stack
        Deque<Integer> stack = new ArrayDeque<>();
        int n = a.length;
        int[] right = new int[n];
        for (int i = 0; i <= n; i++) {
            int val = i == n ? 0 : a[i];
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
}
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
    public int largestRectangleArea(int[] a) {
        // when we meet a cliff we know what's in stack is ascending.
        // then we know for each in stack it can be a starting point for a rect with its own height. nothing on the left is capable to be in that rect
        // note we move the smallest height to the left
        int n = a.length;
        Deque<int[]> st = new ArrayDeque<>();
        // index, height
        int res = 0;
        for (int i = 0; i <= n; i++) {
            int ch = i == n ? 0 : a[i];
            int lasti = i;
            while (!st.isEmpty() && st.peek()[1] >= ch) {
                lasti = st.peek()[0];
                int lasth = st.peek()[1];
                int cur = lasth * (i - lasti);
                // from lasti as starting point to i-1 all >=lasth, can form a rect
                res = Math.max(res, cur);
                st.pop();
            }
            // to later bars it's as if there is a ch at lasti
            st.push(new int[]{lasti, ch});
        }
        st.pop();
        return res;
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
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaximalRangeEachElementIsMaximum {
    public int[] maximumLengthOfRanges(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] left = new int[n];
        Arrays.fill(left, -1);
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[st.peek()] <= a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                left[i] = st.peek();
            }
            st.push(i);
        }
        st.clear();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && a[st.peek()] <= a[i]) {
                st.pop();
            }
            int right = n;
            if (!st.isEmpty()) {
                right = st.peek();
            }
            int len = right - left[i] - 1;
            res[i] = len;

            st.push(i);
        }
        return res;
    }
}

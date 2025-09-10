import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CountBowlSubarrays {
    public long bowlSubarrays(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] right = new int[n];
        Arrays.fill(right, -1);
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[i] > a[st.peek()]) {
                right[st.pop()] = i;

            }
            st.push(i);
        }
        st.clear();
        int[] left = new int[n];
        Arrays.fill(left, -1);
        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && a[i] > a[st.peek()]) {
                left[st.pop()] = i;
            }
            st.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] != -1 && right[i] != -1) {
                ++res;
            }
        }
        return res;
    }
}

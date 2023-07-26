import java.util.ArrayDeque;
import java.util.Deque;

public class LargestElementInArrayAfterOperations {
    public long maxArrayValue(int[] a) {
        int n = a.length;
        Deque<Long> st = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            long cur = a[i];
            while (!st.isEmpty() && st.peek() >= cur) {
                cur += st.pop();
            }
            st.push(cur);
        }
        long res = 0;
        while (!st.isEmpty()) {
            res = Math.max(res, st.pop());
        }
        return res;
    }
}

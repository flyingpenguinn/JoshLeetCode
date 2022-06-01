import java.util.ArrayDeque;
import java.util.Deque;

public class StepsToMakeArrayNonDecreasing {
    public int totalSteps(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                dp[i] = Math.max(dp[i] + 1, dp[st.peek()]);
                st.pop();
            }
            st.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

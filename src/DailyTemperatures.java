import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#739
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {
    // mono stack. can also be used to get next smaller
    public int[] dailyTemperatures(int[] t) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = t.length;
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && t[st.peek()] < t[i]) {
                int j = st.pop();
                r[j] = i - j;
            }
            st.push(i);
        }
        return r;
    }
}

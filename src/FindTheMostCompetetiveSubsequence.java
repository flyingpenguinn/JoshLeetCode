import java.util.ArrayDeque;
import java.util.Deque;

public class FindTheMostCompetetiveSubsequence {
    // use stack and mind the k length restriction!
    public int[] mostCompetitive(int[] a, int k) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int rem = n - i;
            while (!st.isEmpty() && st.peek() > a[i] && st.size() + rem > k) {
                // must be >k because we are popping so need to make sure after pop it's >=k
                st.pop();
            }
            // all in stack <=, we may not want to add more if it's already in size k
            if (st.size() < k) {
                st.push(a[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = st.pop();
        }
        return res;
    }
}

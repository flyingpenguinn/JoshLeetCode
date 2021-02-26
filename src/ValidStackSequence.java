import java.util.ArrayDeque;
import java.util.Deque;

public class ValidStackSequence {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Deque<Integer> st = new ArrayDeque<>();
        int j = 0;
        for (int i = 0; i < n; i++) {

            while (j < n && (st.isEmpty() || st.peek() != popped[i])) {
                st.push(pushed[j++]);
            }
            if (st.isEmpty() || st.peek() != popped[i]) {
                return false;
            }
            st.pop();

        }
        return true;
    }
}

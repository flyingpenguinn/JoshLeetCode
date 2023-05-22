import java.util.ArrayDeque;
import java.util.Deque;

public class MinStringLengthAfterRemovingSubstrings {
    public int minLength(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!st.isEmpty() && st.peek() == 'A' && c == 'B') {
                st.pop();
            } else if (!st.isEmpty() && st.peek() == 'C' && c == 'D') {
                st.pop();
            } else {
                st.push(c);
            }
        }
        return st.size();
    }
}

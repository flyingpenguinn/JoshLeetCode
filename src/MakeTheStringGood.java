import java.util.ArrayDeque;
import java.util.Deque;

public class MakeTheStringGood {
    // keep removing adjacent is a strong indication of stack
    public String makeGood(String s) {
        Deque<Character> st = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && !st.isEmpty() && Math.abs(s.charAt(i) - st.peek()) == 32) {
                st.pop();
                i++;
            }
            if (i < s.length()) {
                st.push(s.charAt(i++));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}

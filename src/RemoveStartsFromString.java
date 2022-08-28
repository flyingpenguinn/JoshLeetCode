import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveStartsFromString {
    public String removeStars(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                if (!dq.isEmpty()) {
                    dq.pop();
                }
            } else {
                dq.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pop());
        }
        return sb.reverse().toString();
    }
}

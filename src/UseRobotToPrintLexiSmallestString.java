import java.util.ArrayDeque;
import java.util.Deque;

public class UseRobotToPrintLexiSmallestString {
    public String robotWithString(String s) {
        int n = s.length();
        int[] suff = new int[n];
        suff[n - 1] = s.charAt(n - 1) - 'a';
        for (int i = n - 2; i >= 0; --i) {
            suff[i] = Math.min(s.charAt(i) - 'a', suff[i + 1]);
        }
        Deque<Integer> st = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && suff[i] >= st.peek()) {
                sb.append((char) ('a' + st.pop()));
            }
            st.push(s.charAt(i) - 'a');
        }
        while (!st.isEmpty()) {
            sb.append((char) ('a' + st.pop()));
        }
        return sb.toString();
    }
}

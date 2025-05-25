import java.util.ArrayDeque;
import java.util.Deque;

public class ResultingStringAfterAdjacentRemovals {
    private boolean good(char a, char b) {
        int ia = a - 'a';
        int ib = b - 'a';
        if (ia > ib) {
            return good(b, a);
        }
        // a<=b

        if ((ia + 1) == ib) {
            return true;
        }
        if (ia == 0 && ib == 25) {
            return true;
        }
        return false;
    }

    public String resultingString(String s) {
        int n = s.length();
        Deque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (st.isEmpty()) {
                st.push(c);
            } else {
                if (good(st.peek(), c)) {
                    st.pop();
                } else {
                    st.push(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}

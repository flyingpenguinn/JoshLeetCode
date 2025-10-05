import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKbalancedSubstrings {
    public String removeSubstring(String s, int k) {
        int n = s.length();
        Deque<int[]> st = new ArrayDeque<>();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int index = c == '(' ? 1 : 2;
            int j = i;
            while (j < n && s.charAt(j) == c) {
                ++j;
            }
            int clen = j - i;
            if (st.isEmpty()) {
                st.push(new int[]{index, clen});
            } else if (st.peek()[0] == index) {
                st.peek()[1] += clen;
            } else if (st.peek()[0] == 2 && index == 1) {
                st.push(new int[]{index, clen});
            } else {
                while (!st.isEmpty() && st.peek()[0] == 1 && st.peek()[1] >= k && clen >= k) {
                    st.peek()[1] -= k;
                    clen -= k;
                    if (st.peek()[1] == 0) {
                        st.pop();
                        while (!st.isEmpty() && st.peek()[0] == 2) {
                            clen += st.peek()[1];
                            st.pop();
                        }
                    }

                }
                if (clen > 0) {
                    st.push(new int[]{index, clen});
                }


            }
            i = j;
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            int[] top = st.poll();
            int cnt = top[1];
            while (cnt > 0) {
                char c = top[0] == 1 ? '(' : ')';
                sb.append(c);
                --cnt;
            }
        }
        sb.reverse();
        return sb.toString();
    }
}

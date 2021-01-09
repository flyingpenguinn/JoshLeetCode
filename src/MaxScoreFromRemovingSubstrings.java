import java.util.ArrayDeque;
import java.util.Deque;

public class MaxScoreFromRemovingSubstrings {
    // reap the bigger reward as much as possible first
    public int maximumGain(String s, int x, int y) {
        char c1 = 'b';
        char c2 = 'a';
        int bigger = y;
        int smaller = x;
        if (y < x) {
            c1 = 'a';
            c2 = 'b';
            bigger = x;
            smaller = y;
        }
        //   System.out.println(c1+" "+c2);
        Deque<Character> st = new ArrayDeque<>();
        int res = 0;
        res += process(s, c1, c2, bigger, st);

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pollFirst());
        }
        //    System.out.println(sb.toString());
        res += process(sb.toString(), c2, c1, smaller, st);
        st.clear();
        return res;
    }

    protected int process(String s, char c1, char c2, int v, Deque<Character> st) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!st.isEmpty() && st.peekLast() == c1 && c == c2) {
                st.pollLast();
                res += v;
            } else {
                st.offerLast(c);
            }
        }
        return res;
    }
}

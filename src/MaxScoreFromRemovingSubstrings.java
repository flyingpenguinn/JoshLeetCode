import java.util.ArrayDeque;
import java.util.Deque;

public class MaxScoreFromRemovingSubstrings {
    // reap the bigger reward as much as possible first
    private int res;
    public int maximumGain(String s, int x, int y) {
        if (y > x) {
            s = solve(s, 'b', 'a', y);
            solve(s, 'a', 'b', x);
        } else {
            s = solve(s, 'a', 'b', x);
            solve(s, 'b', 'a', y);
        }
        return res;
    }

    private String solve(String s, char v1, char v2, int sc) {
        // System.out.println(s);
        int n = s.length();
        Deque<Character> dq = new ArrayDeque<>();
        StringBuilder ns = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == v2 && !dq.isEmpty() && dq.peekLast() == v1) {
                dq.pollLast();
                res += sc;
                continue;
            } else {
                //  System.out.println("in st "+c);
                dq.offerLast(c);
            }
        }

        while (!dq.isEmpty()) {
            ns.append(dq.pollLast());
        }
        String res = ns.reverse().toString();
        return res;
    }
}

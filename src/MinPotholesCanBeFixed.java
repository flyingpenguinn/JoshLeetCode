import java.util.Collections;
import java.util.PriorityQueue;

public class MinPotholesCanBeFixed {
    public int maxPotholes(String s, int b) {
        int n = s.length();
        int i = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        while (i < n) {
            if (s.charAt(i) == '.') {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && s.charAt(j) == 'x') {
                ++j;
            }
            int len = j - i;
            pq.offer(len);
            i = j;
        }

        int res = 0;
        while (!pq.isEmpty() && b > 0) {
            int top = pq.poll();
            int spent = Math.min(top + 1, b);
            b -= spent;
            res += spent - 1;
        }
        return res;
    }
}

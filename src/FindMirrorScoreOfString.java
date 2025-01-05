import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FindMirrorScoreOfString {
    public long calculateScore(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        Map<Integer, ArrayDeque<Integer>> m = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - 'a';
            int mirror = 25 - cind;

            if (m.containsKey(mirror)) {
                Deque<Integer> dq = m.get(mirror);
                int last = dq.peekLast();
                // System.out.println("i= "+i +" cind="+cind+" mirror "+mirror +" last="+last);
                long diff = 0L + i - last;
                res += diff;
                dq.pollLast();
                if (dq.isEmpty()) {
                    m.remove(mirror);
                }
            } else {
                m.computeIfAbsent(cind, p -> new ArrayDeque<>()).offerLast(i);
            }
        }
        return res;
    }
}

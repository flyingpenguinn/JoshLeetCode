import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class LexicographicallySmallestStringAfterOperation {
    // at most 10 transformation 1s
    // at most a.length transformation2s. so in all won't exceed 10*n*n (per transformation n steps)
    public String findLexSmallestString(String s, int a, int b) {
        String res = null;
        int n = s.length();
        Deque<String> q = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        seen.add(s);
        q.offer(s);
        while (!q.isEmpty()) {
            String top = q.poll();
            //    System.out.println(top);
            if (res == null || top.compareTo(res) < 0) {
                res = top;
            }
            StringBuilder sb = new StringBuilder(top);
            for (int i = 1; i < top.length(); i += 2) {
                int cind = top.charAt(i) - '0';
                int mod = (cind + a) % 10;
                sb.setCharAt(i, (char) ('0' + mod));
            }
            String trans1 = sb.toString();
            //   System.out.println("trans1..."+trans1);
            if (!seen.contains(trans1)) {
                seen.add(trans1);
                q.offer(trans1);
            }
            String trans2 = top.substring(n - b, n) + top.substring(0, n - b);
            //  System.out.println("trans2..."+trans2);
            if (!seen.contains(trans2)) {
                seen.add(trans2);
                q.offer(trans2);
            }
        }
        return res;
    }
}

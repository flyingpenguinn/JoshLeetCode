import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class LexicoGraphicallyMinStringAfterRemoveStars {
    public String clearStars(String s) {
        int n = s.length();
        TreeSet<int[]> ts = new TreeSet<>((x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(y[1], x[1]);
            }
        });
        Set<Integer> del = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                int[] top = ts.pollFirst();
                int ind = top[1];
                del.add(ind);
            } else {
                ts.add(new int[]{(c - 'a'), i});
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                continue;
            }
            if (del.contains(i)) {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}

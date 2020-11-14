import java.util.ArrayDeque;
import java.util.Deque;

public class MinDeletionsToMakeStringBalanced {
    // at each positoin, delete all bs before and all as after
    // can also dp on each position having a flag on whether we already got b or not
    public int minimumDeletions(String s) {
        int n = s.length();
        int ca = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') {
                ca++;
            }
        }
        int min = Integer.MAX_VALUE;
        int cb = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                ca--;
            }
            int cur = cb + ca;
            min = Math.min(min, cur);
            if (s.charAt(i) == 'b') {
                cb++;
            }
        }
        return min;
    }
}

import java.util.HashMap;
import java.util.Map;

public class MaxNumEqualLengthRuns {
    public int maxSameLengthRuns(String s) {
        int n = s.length();
        int i = 0;
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(i) == s.charAt(j)) {
                ++j;
            }
            int len = j - i;

            int nc = m.getOrDefault(len, 0) + 1;
            m.put(len, nc);
            res = Math.max(res, nc);
            i = j;
        }
        return res;
    }
}

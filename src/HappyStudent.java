import java.util.Collections;
import java.util.List;

public class HappyStudent {
    public int countWays(List<Integer> a) {
        Collections.sort(a);
        int n = a.size();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a.get(i);
            if (i + 1 > v && (i == n - 1 || a.get(i + 1) > i + 1)) {
                ++res;
            }
        }
        if (a.get(0) > 0) {
            ++res;
        }
        return res;
    }
}

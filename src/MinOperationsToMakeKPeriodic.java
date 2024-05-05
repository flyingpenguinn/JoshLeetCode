import java.util.HashMap;
import java.util.Map;

public class MinOperationsToMakeKPeriodic {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n = word.length();
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i + k - 1 < n; i += k) {
            String str = word.substring(i, i + k);
            m.put(str, m.getOrDefault(str, 0) + 1);
        }
        int all = n / k;
        int res = all;
        for (String sk : m.keySet()) {
            int c = m.get(sk);
            int other = all - c;
            res = Math.min(res, other);
        }
        return res;
    }
}

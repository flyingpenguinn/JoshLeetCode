import java.util.HashMap;
import java.util.Map;

public class RearrangeKSubstringsToFormSubstring {
    public boolean isPossibleToRearrange(String s, String t, int p) {
        int n = s.length();
        int k = n / p;
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i + k - 1 < n; i += k) {
            String cur = s.substring(i, i + k);
            //  System.out.println("s "+cur+" "+i+","+k);
            update(m, cur, 1);
        }
        for (int i = 0; i + k - 1 < n; i += k) {
            String cur = t.substring(i, i + k);

            //System.out.println("t "+cur+" "+i+","+k);
            if (m.containsKey(cur)) {
                update(m, cur, -1);
            } else {
                return false;
            }
        }
        return true;
    }

    private void update(Map<String, Integer> m, String k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}

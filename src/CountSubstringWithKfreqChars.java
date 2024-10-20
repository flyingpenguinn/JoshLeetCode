import java.util.HashMap;
import java.util.Map;

public class CountSubstringWithKfreqChars {
    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = s.charAt(i) - 'a';
        }
        int other = atmost(a, k - 1);
        int all = n * (n + 1) / 2;
        return all - other;
    }


    private int atmost(int[] a, int k) {
        if (k == 0) {
            return 0;
        }
        int n = a.length;
        int start = 0;
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
            while (bad(m, k)) {
                m.put(a[start], m.get(a[start]) - 1);
                if (m.get(a[start]) == 0) {
                    m.remove(a[start]);
                }
                start++;
            }
            // start...i good, have at most k chars
            res += i - start + 1;
        }
        return res;
    }

    private boolean bad(Map<Integer, Integer> m, int k) {
        for (int key : m.keySet()) {
            if (m.get(key) > k) {
                return true;
            }
        }
        return false;
    }
}

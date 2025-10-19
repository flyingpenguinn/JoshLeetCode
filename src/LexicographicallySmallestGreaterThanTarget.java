import java.util.TreeMap;

public class LexicographicallySmallestGreaterThanTarget {
    private void update(Character k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private TreeMap<Character, Integer> m = new TreeMap<>();

    public String lexGreaterPermutation(String s, String t) {
        int n = s.length();
        // star to be bigger at j
        for (int j = n - 1; j >= 0; --j) {
            m.clear();
            for (char c : s.toCharArray()) {
                update(c, 1);
            }
            boolean bad = false;
            for (int i = 0; i < j; ++i) {
                char c = t.charAt(i);
                if (!m.containsKey(c)) {
                    bad = true;
                    break;
                } else {
                    update(c, -1);
                }
            }
            if (bad) {
                continue;
            }
            char tc = t.charAt(j);
            boolean found = false;
            char fc = '*';
            for (char k = (char) (tc + 1); k <= 'z'; ++k) {
                if (m.containsKey(k)) {
                    found = true;
                    fc = k;
                    update(fc, -1);
                    break;
                }
            }
            if (!found) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < j; ++i) {
                char c = t.charAt(i);
                sb.append(c);

            }
            sb.append(fc);
            for (char k : m.keySet()) {
                int times = m.get(k);
                while (times > 0) {
                    sb.append(k);
                    --times;
                }
            }
            return sb.toString();
        }
        return "";
    }
}

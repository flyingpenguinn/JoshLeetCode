import java.util.TreeSet;

public class FindLongestSelfContainingSubstring {
    public int maxSubstringLength(String s) {

        int n = s.length();
        TreeSet<Integer>[] m = new TreeSet[26];
        for (int i = 0; i < 26; ++i) {
            m[i] = new TreeSet<>();
        }
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            m[cind].add(i);
        }
        int res = -1;
        for (int i = 0; i < 26; ++i) {
            if (m[i].isEmpty()) {
                continue;
            }
            for (int j = 0; j < 26; ++j) {
                if (m[j].isEmpty()) {
                    continue;
                }
                int start = Math.min(m[i].first(), m[j].first());
                int end = Math.max(m[i].last(), m[j].last());
                boolean bad = false;
                for (int k = 0; k < 26; ++k) {
                    if (k == i || k == j || m[k].isEmpty()) {
                        continue;
                    }
                    int kstart = m[k].first();
                    int kend = m[k].last();
                    if (kstart >= start && kend <= end) {
                        continue;
                    }
                    Integer bg = m[k].higher(start);
                    if (bg == null) {
                        continue;
                    }
                    if (bg > end) {
                        continue;
                    } else {
                        bad = true;
                        break;
                    }
                }
                if (!bad) {
                    int len = end - start + 1;
                    if (len < n) {
                        res = Math.max(res, len);
                    }
                }
            }
        }
        return res;
    }
}

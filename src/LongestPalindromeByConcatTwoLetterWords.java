import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeByConcatTwoLetterWords {
    private void update(Map<String, Integer> m, String k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int longestPalindrome(String[] words) {
        Map<String, Integer> m = new HashMap<>();
        for (String w : words) {
            update(m, w, 1);
        }
        int res = 0;
        int maxpalin = 0;
        for (String s:m.keySet()){
            int count = m.get(s);
            if (count == 0) {
                continue;
            }
            int scount = m.get(s);
            String rs = new StringBuilder(s).reverse().toString();
            if (s.equals(rs)) {
                res += (scount / 2) * 2 * s.length();
                if (scount % 2 == 1 && ispalin(s)) {
                    maxpalin = Math.max(maxpalin, s.length());
                }
            } else if (m.getOrDefault(rs, 0)>0) {
                int mincounts = Math.min(count, m.get(rs));
                res += mincounts * 2 * s.length();
                m.put(rs, 0);
            } else {
                if (ispalin(s)) {
                    maxpalin = Math.max(maxpalin, s.length());
                }
            }
            m.put(s, 0);
        }
        res += maxpalin;
        return res;
    }

    private boolean ispalin(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}

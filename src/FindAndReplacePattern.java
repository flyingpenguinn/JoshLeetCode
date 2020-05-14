import java.util.ArrayList;
import java.util.List;

public class FindAndReplacePattern {
    // similar to "isomorphic string", must be one one mapping
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> r = new ArrayList<>();
        for (String w : words) {
            if (match(w, pattern)) {
                r.add(w);
            }
        }
        return r;
    }

    private boolean match(String s, String p) {
        if (s.length() != p.length()) {
            return false;
        }
        int n = s.length();
        int[] sm = new int[27];
        int[] pm = new int[27];
        for (int i = 0; i < n; i++) {
            int sc = s.charAt(i) - 'a' + 1;
            int pc = p.charAt(i) - 'a' + 1;
            if (sm[sc] == pc && pm[pc] == sc) {
                continue;
            } else if (sm[sc] == 0 && pm[pc] == 0) {
                sm[sc] = pc;
                pm[pc] = sc;
            } else {
                return false;
            }
        }
        return true;
    }
}

import java.util.HashSet;
import java.util.Set;

public class CountResidualPrefixes {
    public int residuePrefixes(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            set.add(c);
            if (set.size() == (i + 1) % 3) {
                ++res;
            }
        }
        return res;
    }
}

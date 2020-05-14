import java.util.HashSet;
import java.util.Set;

public class BinaryStringSubstring1ToN {
    // wither contains all numbers in a range: generate all the possibilities filter dupe and filter disqualified.
    // count how many are qualified must be == n
    public boolean queryString(String s, int num) {
        Set<Long> seen = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            long cur = 0;
            for (int j = i; j < n && j <= i + 31; j++) {
                cur = cur * 2L + (s.charAt(j) - '0');
                if (cur <= num && cur >= 1) {
                    seen.add(cur);
                } else if (cur > num) {
                    break;
                }
            }
        }
        return seen.size() == num;

    }
}

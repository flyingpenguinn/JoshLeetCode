import java.util.HashSet;
import java.util.Set;

public class MaxSubstringsWithDistinctStart {
    public int maxDistinct(String s) {
        char[] sc = s.toCharArray();
        int n = sc.length;
        Set<Character> seen = new HashSet<>();
        int res = 0;
        for (char c : sc) {
            if (!seen.contains(c)) {
                ++res;
                seen.add(c);
            }
        }
        return res;
    }
}

import java.util.HashSet;
import java.util.Set;

public class OptimalPartitionOfString {
    public int partitionString(String s) {
        int n = s.length();
        Set<Character> seen = new HashSet<>();
        int res = 1;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (seen.contains(c)) {
                ++res;
                seen.clear();
            }
            seen.add(c);
        }
        return res;
    }
}

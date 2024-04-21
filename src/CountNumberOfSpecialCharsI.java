import java.util.HashSet;
import java.util.Set;

public class CountNumberOfSpecialCharsI {
    public int numberOfSpecialChars(String word) {
        Set<Character> set = new HashSet<>();
        for (char c : word.toCharArray()) {
            set.add(c);
        }
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            char u = (char) ('A' + i);
            char l = (char) ('a' + i);
            if (set.contains(u) && set.contains(l)) {
                ++res;
            }
        }
        return res;
    }
}

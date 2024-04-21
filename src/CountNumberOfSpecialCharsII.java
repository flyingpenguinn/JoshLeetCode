import java.util.HashMap;
import java.util.Map;

public class CountNumberOfSpecialCharsII {
    public int numberOfSpecialChars(String s) {
        Map<Character, Integer> lm = new HashMap<>();
        Map<Character, Integer> um = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                lm.put(c, i);
            } else if (Character.isUpperCase(c)) {
                um.putIfAbsent(c, i);
            }
        }
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            char u = (char) ('A' + i);
            char l = (char) ('a' + i);
            if (lm.containsKey(l) && um.containsKey(u)) {
                int lastl = lm.get(l);
                int firstu = um.get(u);
                if (lastl < firstu) {
                    ++res;
                }
            }
        }
        return res;
    }
}

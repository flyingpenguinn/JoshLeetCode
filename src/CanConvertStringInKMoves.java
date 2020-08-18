import java.util.HashMap;
import java.util.Map;

public class CanConvertStringInKMoves {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            int diff = tc - sc;
            if (diff == 0) {
                continue;
            }
            if (diff < 0) {
                diff += 26;
            }
            if (diff > k) {
                return false;
            }
            map.put(diff, map.getOrDefault(diff, 0) + 1);
        }
        for (int key : map.keySet()) {
            int times = map.get(key);
            int allowed = (k - key) / 26 + 1;
            if (times > allowed) {
                return false;
            }
        }
        return true;
    }
}

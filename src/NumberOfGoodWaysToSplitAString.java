import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodWaysToSplitAString {
    public int numSplits(String s) {
        Map<Character, Integer> left = new HashMap<>();
        Map<Character, Integer> right = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            update(right, c, 1);
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            update(left, c, 1);
            update(right, c, -1);
            if (left.keySet().size() == right.keySet().size()) {
                res++;
            }
        }
        return res;
    }

    private void update(Map<Character, Integer> m, char c, int delta) {
        int nv = m.getOrDefault(c, 0) + delta;
        if (nv <= 0) {
            m.remove(c);
        } else {
            m.put(c, nv);
        }
    }
}

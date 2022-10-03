import java.util.HashMap;
import java.util.Map;

public class RemoveLetterToEqualizeFrequency {
    public boolean equalFrequency(String word) {
        int n = word.length();
        int[] count = new int[26];
        for (int i = 0; i < n; ++i) {
            int cind = word.charAt(i) - 'a';
            ++count[cind];
        }
        Map<Integer, Integer> cm = new HashMap<>();
        int max = 0;
        int min = (int) 1e9;
        int chars = 0;
        for (int i = 0; i < 26; ++i) {
            if (count[i] == 0) {
                continue;
            }
            cm.put(count[i], cm.getOrDefault(count[i], 0) + 1);
            max = Math.max(max, count[i]);
            min = Math.min(min, count[i]);
            ++chars;
        }
        if (cm.size() == 1) {
            return chars == 1 || max == 1;
        }
        if (cm.size() == 2) {
            if (min == 1 && cm.get(min) == 1) {
                return true;
            }
            if (cm.get(max) == 1 && cm.get(min) == chars - 1 && max == min + 1) {
                return true;
            }
        }
        return false;

    }
}

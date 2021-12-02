import java.util.HashMap;
import java.util.Map;

public class CountCommonWordsWithOneOccurrance {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        for (String s : words1) {
            m1.put(s, m1.getOrDefault(s, 0) + 1);
        }
        for (String s : words2) {
            m2.put(s, m2.getOrDefault(s, 0) + 1);
        }
        int res = 0;
        for (String p1 : m1.keySet()) {
            if (m1.get(p1) == 1 && m2.getOrDefault(p1, 0) == 1) {
                ++res;
            }
        }
        return res;
    }
}

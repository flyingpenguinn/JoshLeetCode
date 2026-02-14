import java.util.HashMap;
import java.util.Map;

public class NumberOfPrefixConnectedGroups {
    public int prefixConnected(String[] words, int k) {
        int n = words.length;
        Map<String, Integer> m = new HashMap<>();
        for (String w : words) {
            if (w.length() < k) {
                continue;
            }
            String stub = w.substring(0, k);
            m.put(stub, m.getOrDefault(stub, 0) + 1);
        }
        int res = 0;
        for (String key : m.keySet()) {
            if (m.get(key) >= 2) {
                ++res;
            }
        }
        return res;
    }
}

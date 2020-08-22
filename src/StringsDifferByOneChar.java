import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringsDifferByOneChar {
    // O(nm2), need to rewrite as rolling hash
    public boolean differByOne(String[] dict) {
        Map<Integer, Set<String>> map = new HashMap<>();
        for (String w : dict) {
            for (int i = 0; i < w.length(); i++) {
                StringBuilder sb = new StringBuilder(w);
                sb.deleteCharAt(i);
                String str = sb.toString();
                Set<String> set = map.getOrDefault(i, new HashSet<>());
                if (set.contains(str)) {
                    return true;
                }
                map.computeIfAbsent(i, k -> new HashSet<>()).add(str);
            }

        }
        return false;
    }
}

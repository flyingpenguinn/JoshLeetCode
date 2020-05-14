import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbreviation {

}

class ValidWordAbbr {

    Map<String, Set<String>> map = new HashMap<>();

    public ValidWordAbbr(String[] dict) {
        for (String d : dict) {
            String cd = code(d);
            map.computeIfAbsent(cd, k -> new HashSet<>()).add(d);
        }
    }

    public boolean isUnique(String w) {
        Set<String> set = map.getOrDefault(code(w), new HashSet<>());
        // the definition of "no other words"
        if (set.isEmpty()) {
            return true;
        } else if (set.contains(w)) {
            return set.size() == 1;
        } else {
            return false;
        }
    }

    String code(String s) {
        int n = s.length();
        return n <= 2 ? s : s.charAt(0) + String.valueOf(n - 2) + s.charAt(n - 1);
    }
}

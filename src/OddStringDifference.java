import java.util.HashMap;
import java.util.Map;

public class OddStringDifference {
    public String oddString(String[] words) {
        Map<String, Integer> m = new HashMap<>();
        Map<String, String> m2 = new HashMap<>();
        for (String w : words) {
            String diff = diff(w);

            m.put(diff, m.getOrDefault(diff, 0) + 1);
            m2.put(diff, w);
        }
        for (String k : m.keySet()) {
            if (m.get(k) == 1) {
                return m2.get(k);
            }
        }
        return "";
    }

    private String diff(String w) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < w.length(); ++i) {
            sb.append(w.charAt(i) - w.charAt(i - 1));
            sb.append(",");
        }
        return sb.toString();
    }
}

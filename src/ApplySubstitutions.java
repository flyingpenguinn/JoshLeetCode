import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplySubstitutions {
    private Map<String, String> m = new HashMap<>();

    public String applySubstitutions(List<List<String>> replacements, String text) {
        String[] tt = text.split("_");
        for (List<String> r : replacements) {
            String v1 = r.get(0);
            String v2 = r.get(1);
            m.put(v1, v2);
        }
        StringBuilder sb = new StringBuilder();
        for (String t : tt) {
            if (sb.length()>0) {
                sb.append('_');
            }
            sb.append(solve(t));

        }
        return sb.toString();
    }

    String solve(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '%') {
                int end = s.indexOf('%', i + 1);
                String var = s.substring(i + 1, end);
                String later = solve(m.get(var));
                sb.append(later);
                i = end + 1;
            } else {
                sb.append(s.charAt(i));
                ++i;
            }
        }
        return sb.toString();
    }
}

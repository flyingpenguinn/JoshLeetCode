import java.util.HashMap;
import java.util.Map;

public class CountValidWordOcc {
    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String ch : chunks) {
            sb.append(ch);
        }
        int sn = sb.length();
        StringBuilder cur = new StringBuilder();
        Map<String, Integer> cnt = new HashMap<>();
        for (int i = 0; i <= sn; ++i) {
            char c = i == sn ? '*' : sb.charAt(i);
            if (c == '-' && i - 1 >= 0 && i + 1 < sn && Character.isLowerCase(sb.charAt(i - 1)) && Character.isLowerCase(sb.charAt(i + 1))) {
                cur.append(c);

            } else if (Character.isLowerCase(c)) {
                cur.append(c);
            } else {
                String last = cur.toString();
                if (!last.isEmpty()) {
                    cnt.put(last, cnt.getOrDefault(last, 0) + 1);
                }
                cur = new StringBuilder();
            }
        }
        int[] res = new int[queries.length];
        int ri = 0;
        for (String q : queries) {
            res[ri++] = cnt.getOrDefault(q, 0);
        }
        return res;
    }
}

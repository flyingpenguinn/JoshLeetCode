import java.util.*;

public class EvaluateBracketPairsOfString {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> m = new HashMap<>();
        for (List<String> kv : knowledge) {
            m.put(kv.get(0), kv.get(1));
        }
        int n = s.length();
        Deque<Integer> st = new ArrayDeque<>();
        boolean inbra = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
                inbra = true;
            } else if (c == ')') {
                int pre = st.pop();
                String key = s.substring(pre + 1, i);
                if (m.containsKey(key)) {
                    sb.append(m.get(key));
                } else {
                    sb.append("?");
                }
                inbra = false;
            } else {
                if (!inbra) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

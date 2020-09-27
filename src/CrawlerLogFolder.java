import java.util.ArrayDeque;
import java.util.Deque;

public class CrawlerLogFolder {
    public int minOperations(String[] logs) {
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < logs.length; i++) {
            String v = logs[i];
            if ("./".equals(v)) {
                continue;
            } else if ("../".equals(v)) {
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else {
                st.push(1);
            }
        }
        return st.size();
    }
}

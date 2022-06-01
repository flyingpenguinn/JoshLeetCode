import java.util.HashMap;
import java.util.Map;

public class SenderWithLargestWordCount {
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> m = new HashMap<>();
        int n = messages.length;
        for (int i = 0; i < n; ++i) {
            String str = messages[i];
            String[] words = str.split(" ");
            m.put(senders[i], m.getOrDefault(senders[i], 0) + words.length);
        }
        int res = 0;
        String resName = "";
        for (String name : m.keySet()) {
            if (m.get(name) > res) {
                res = m.get(name);
                resName = name;
            } else if (m.get(name) == res && name.compareTo(resName) > 0) {
                resName = name;
            }
        }
        return resName;
    }
}

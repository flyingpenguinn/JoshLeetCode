import java.util.Arrays;

public class DecodeMessage {
    public String decodeMessage(String key, String message) {
        int[] m = new int[26];
        Arrays.fill(m, -1);
        int cur = 0;
        for (char c : key.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            int cind = c - 'a';
            if (m[cind] == -1) {
                m[cind] = cur++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (c == ' ') {
                res.append(" ");
                continue;
            }
            res.append((char) ('a' + m[c - 'a']));
        }
        return res.toString();
    }
}

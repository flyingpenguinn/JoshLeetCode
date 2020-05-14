import java.util.Arrays;

public class CustomSortString {

    // counting sort!
    public String customSortString(String s, String t) {
        int[] map = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            map[i] = s.charAt(i) - 'a';
        }
        int[] count = new int[26];
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int times = count[map[i]];
            for (int j = 0; j < times; j++) {
                sb.append((char) ('a' + map[i]));
            }
            count[map[i]] = -1;
        }
        // for those not in s
        for (int i = 0; i < count.length; i++) {
            int times = count[i];
            for (int j = 0; j < times; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }
}

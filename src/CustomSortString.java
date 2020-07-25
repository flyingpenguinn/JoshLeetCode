import java.util.Arrays;

public class CustomSortString {

    // counting sort! when the char set is limited, consider counting sort
    public String customSortString(String s, String t) {
        // check non null otherwise error out, all lower case
        int[] tm = new int[26];
        for (int i = 0; i < t.length(); i++) {
            tm[t.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            int sind = sc - 'a';
            while (tm[sind] > 0) {
                sb.append(sc);
                tm[sind]--;
            }
        }
        // pick up remaining in t
        for (char c = 'a'; c <= 'z'; c++) {
            int cind = c - 'a';
            while (tm[cind] > 0) {
                sb.append(c);
                tm[cind]--;
            }
        }
        return sb.toString();
    }
}

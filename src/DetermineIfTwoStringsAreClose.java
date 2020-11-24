import java.util.HashMap;
import java.util.Map;

public class DetermineIfTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int n = word1.length();
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int[] m1 = new int[26];
        int[] m2 = new int[26];
        for (int i = 0; i < n; i++) {
            m1[c1[i] - 'a']++;
            m2[c2[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (m1[i] == 0 && m2[i] != 0) {
                return false;
            }
            if (m2[i] == 0 && m1[i] != 0) {
                return false;
            }
        }
        Map<Integer, Integer> cm1 = new HashMap<>();
        Map<Integer, Integer> cm2 = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            cm1.put(m1[i], cm1.getOrDefault(m1[i], 0) + 1);
            cm2.put(m2[i], cm2.getOrDefault(m2[i], 0) + 1);
        }
        return cm1.equals(cm2);
    }
}

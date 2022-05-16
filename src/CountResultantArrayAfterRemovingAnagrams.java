import java.util.ArrayList;
import java.util.List;

public class CountResultantArrayAfterRemovingAnagrams {
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            if (res.isEmpty() || !anagram(res.get(res.size() - 1), w)) {
                res.add(w);
            }
        }
        return res;
    }

    private boolean anagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] m1 = new int[26];
        int[] m2 = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            ++m1[s1.charAt(i) - 'a'];
        }
        for (int i = 0; i < s2.length(); ++i) {
            ++m2[s2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (m1[i] != m2[i]) {
                return false;
            }
        }
        return true;
    }
}

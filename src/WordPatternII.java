import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#291
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
Notes:
You may assume both pattern and str contains only lowercase letters.
 */
public class WordPatternII {
    // no need to dp: no overlapping subproboem...every time a got a different value...

    public boolean wordPatternMatch(String p, String s) {
        String[] ms = new String[26];
        return dow(0, 0, p, s, ms, new HashMap<>());
    }

    boolean dow(int i, int j, String p, String s, String[] ms, Map<String, Character> mc) {
        if (i == p.length() && j == s.length()) {
            return true;
        }
        if (i == p.length() || j == s.length()) {
            return false;
        }
        if (p.length() - i > s.length() - j) {
            // prune early
            return false;
        }
        char c = p.charAt(i);
        String mapped = ms[c - 'a'];
        if (mapped == null) {
            StringBuilder sb = new StringBuilder();
            for (int k = j; k < s.length(); k++) {
                sb.append(s.charAt(k));
                String ns = sb.toString();
                if (mc.get(ns) != null) {
                    continue;
                }
                ms[c - 'a'] = ns;
                mc.put(ns, c);
                boolean later = dow(i + 1, k + 1, p, s, ms, mc);
                ms[c - 'a'] = null;
                mc.remove(ns);
                if (later) {
                    return true;
                }
            }

            return false;

        } else {
            if (s.startsWith(mapped, j)) {
                return dow(i + 1, j + mapped.length(), p, s, ms, mc);
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new WordPatternII().wordPatternMatch("abba", "dogcatcatdog"));
    }
}

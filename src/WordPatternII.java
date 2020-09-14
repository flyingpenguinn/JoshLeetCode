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
        return dopattern(p, s, 0, 0, new HashMap<>(), new HashMap<>());
    }

    private boolean dopattern(String p, String s, int pi, int si, Map<Character, String> pm, Map<String, Character> sm) {
        if (pi == p.length() && si == s.length()) {
            return true;
        } else if (pi == p.length() || si == s.length()) {
            return false;
        }
        char c = p.charAt(pi);
        String mappeds = pm.get(c);
        if (mappeds != null) {
            if (s.startsWith(mappeds, si)) {
                return dopattern(p, s, pi + 1, si + mappeds.length(), pm, sm);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            for (int j = si; j < s.length(); j++) {
                sb.append(s.charAt(j));
                String str = sb.toString();
                Character mappedp = sm.get(str);
                if (mappedp == null) {
                    pm.put(c, str);
                    sm.put(str, c);
                    if (dopattern(p, s, pi + 1, j + 1, pm, sm)) {
                        return true;
                    }
                    pm.remove(c);
                    sm.remove(str);
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new WordPatternII().wordPatternMatch("abba", "dogcatcatdog"));
    }
}

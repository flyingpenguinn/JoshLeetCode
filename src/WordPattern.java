import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#290

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
public class WordPattern {
    // must be two maps to avoid double booking on one side
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> pm = new HashMap<>();
        Map<String, Character> sm = new HashMap<>();
        String[] ss = str.split(" ");
        if (ss.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            char p = pattern.charAt(i);
            if (pm.containsKey(p) && !s.equals(pm.get(p))) {
                return false;
            }
            if (sm.containsKey(s) && p != sm.get(s)) {
                return false;
            }
            pm.put(p, s);
            sm.put(s, p);
        }
        return true;
    }
}

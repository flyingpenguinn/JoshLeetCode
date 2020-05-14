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
    public boolean wordPattern(String p, String str) {
        Map<String, Character> m1 = new HashMap<>();
        Map<Character, String> m2 = new HashMap<>();
        int n = p.length();
        String[] ss = str.split(" ");
        if (ss.length != n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            String s = ss[i];
            char c = p.charAt(i);
            Character m1m = m1.get(s);
            String m2m = m2.get(c);
            if (m1m == null && m2m == null) {
                m1.put(s, c);
                m2.put(c, s);
            } else if (m1m == null || m2m == null) {
                return false;
            } else if (!m2m.equals(s) || m1m != c) {
                return false;
            }
        }
        return true;
    }
}

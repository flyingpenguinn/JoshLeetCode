import java.util.HashMap;
import java.util.Map;

/*
LC#1446
Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.

Return the power of the string.



Example 1:

Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.
Example 2:

Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
Example 3:

Input: s = "triplepillooooow"
Output: 5
Example 4:

Input: s = "hooraaaaaaaaaaay"
Output: 11
Example 5:

Input: s = "tourist"
Output: 1


Constraints:

1 <= s.length <= 500
s contains only lowercase English letters.
 */
public class ConsecutiveCharacters {
    public int maxPower(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j = i + 1;
            while (j < s.length() && s.charAt(j) == c) {
                j++;
            }
            res = Math.max(res, j - i); // i.. j-1
        }
        return res;
    }

}

class ConsecutiveCharsTemplate {
    // templated two pointer...
    public int maxPower(String s) {
        int low = 0;
        int high = 0;
        Map<Character, Integer> m = new HashMap<>();
        updatemap(m, s.charAt(0), 1);
        int r = 0;
        while (true) {
            if (m.keySet().size() == 1) {
                r = Math.max(r, high - low + 1);
                high++;
                if (high == s.length()) {
                    break;
                }
                updatemap(m, s.charAt(high), 1);
            } else {
                updatemap(m, s.charAt(low), -1);
                low++;
            }
        }
        return r;
    }

    private void updatemap(Map<Character, Integer> m, Character k, int d) {
        int newvalue = m.getOrDefault(k, 0) + d;
        if (newvalue == 0) {
            m.remove(k);
        } else {
            m.put(k, newvalue);
        }
    }
}

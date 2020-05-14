import java.util.HashMap;
import java.util.Map;

/*
LC#1358
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.



Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
Example 3:

Input: s = "abc"
Output: 1


Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */
public class NumberOfSubstringsContainingAllThreeChars {
    public int numberOfSubstrings(String s) {
        int high = -1;
        int low = 0;
        Map<Character, Integer> cm = new HashMap<>();
        int n = s.length();
        int r = 0;
        while (true) {
            if (cm.size() < 3) {
                high++;
                if (high == s.length()) {
                    break;
                }
                cm.put(s.charAt(high), cm.getOrDefault(s.charAt(high), 0) + 1);
            } else {
                // low..high...n are good
                r += n - 1 - high + 1;
                int nc = cm.get(s.charAt(low)) - 1;
                if (nc == 0) {
                    cm.remove(s.charAt(low));
                } else {
                    cm.put(s.charAt(low), nc);
                }
                low++;
            }
        }
        return r;
    }
}

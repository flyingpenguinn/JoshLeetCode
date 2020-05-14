import java.util.HashMap;
import java.util.Map;

/*
LC#1297
Given a string s, return the maximum number of ocurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.


Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3
Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0


Constraints:

1 <= s.length <= 10^5
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s only contains lowercase English letters.
 */
public class MaxNumberOccurenceOfSubstring {

    // never need to consider maxsize, just minsize is enough because we want the max occur substring...
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        int max = 0;

        for (int i = 0; i + minSize - 1 < n; i++) {
            int end = i + minSize - 1;
            String str = s.substring(i, end + 1);
            int letters = countletter(str);
            if (letters <= maxLetters) {
                int nc = map.getOrDefault(str, 0) + 1;
                max = Math.max(max, nc);
                map.put(str, nc);
            }
        }

        return max;
    }

    private int countletter(String str) {
        boolean[] count = new boolean[26];
        int uniq = 0;
        for (int i = 0; i < str.length(); i++) {
            boolean oc = count[str.charAt(i) - 'a'];
            if (!oc) {
                uniq++;
            }
            count[str.charAt(i) - 'a'] = true;
        }
        return uniq;
    }

    public static void main(String[] args) {
        System.out.println(new MaxNumberOccurenceOfSubstring().maxFreq("aababcaab", 2, 3, 4));
    }
}

import java.util.*;

/*
LC#1153
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.



Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.


Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.
 */
public class StringTransformIntoAnother {
    // if we need a mapping then we need at least one free char to spare
    private Map<Character, Character> g = new HashMap<>();
    private Set<Character> seen = new HashSet<>();

    public boolean canConvert(String str1, String str2) {
        int n = str1.length();
        boolean needtemp = false;
        for (int i = 0; i < n; ++i) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (g.containsKey(c1) && g.get(c1) != c2) {
                return false;
            }
            g.put(c1, c2);
            if (c1 != c2) {
                needtemp = true;
            }
            seen.add(c2);
        }
        return !needtemp || seen.size() < 26;
    }
}

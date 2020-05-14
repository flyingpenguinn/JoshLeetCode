import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    // must have one unused char....
    boolean cycle = false;
    int[] m = new int[26];
    int[] st = new int[26];

    public boolean canConvert(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Arrays.fill(m, -1);
        for (int i = 0; i < s.length(); i++) {
            int sind = s.charAt(i) - 'a';
            int tind = t.charAt(i) - 'a';
            if (m[sind] != -1 && m[sind] != tind) {
                return false;
            }
            m[sind] = tind;
        }
        Set<Integer> mapped = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (m[i] != -1) {
                mapped.add(m[i]);
            }
        }
        if (mapped.size() < 26) {
            return true;
        }
        for (int i = 0; i < 26; i++) {
            if (st[i] == 0 && m[i] != -1) {
                dfs(i);
            }
        }

        return !cycle;
    }

    private void dfs(int i) {
        if (cycle) {
            return;
        }
        st[i] = 1;
        int j = m[i];
        if (j == -1 || j == i) {
            return;
        }
        if (st[j] == 1) {
            cycle = true;
            return;
        } else if (st[j] == 0) {
            dfs(j);
        }
        st[i] = 2;
    }
}
